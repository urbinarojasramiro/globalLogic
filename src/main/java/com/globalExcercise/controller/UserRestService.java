package com.globalExcercise.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globalExcercise.dto.ErrorResponse;
import com.globalExcercise.dto.MensajeResponse;
import com.globalExcercise.dto.UserRequestDTO;
import com.globalExcercise.dto.UserResponseDTO;
import com.globalExcercise.entity.RolEntity;
import com.globalExcercise.entity.UserEntity;
import com.globalExcercise.exception.ErrorCode;
import com.globalExcercise.exception.ErrorMessage;
import com.globalExcercise.exception.GlobalBusinessException;
import com.globalExcercise.jwt.JwtProvider;
import com.globalExcercise.service.RolService;
import com.globalExcercise.service.UserService;
import com.globalExcercise.util.EmailValidator;

@RestController
@RequestMapping (value="/userApiRest")
public class UserRestService {
	
	private static Logger LOG = LoggerFactory.getLogger(UserRestService.class);

	@Autowired
	UserService userService;
	@Autowired
    JwtProvider jwtProvider;
	@Autowired
    AuthenticationManager authenticationManager;
	@Autowired
	RolService rolService;
	@Autowired
	EmailValidator emailValidator;

	@GetMapping(value="getUsers") 
	public List<UserEntity> getUsers() throws GlobalBusinessException
	{
		LOG.info("Obtener lista de usuarios desde el metodo rest getUsers");
		return userService.getUser();
	}
	
	@PostMapping(value="saveUser")
	public ResponseEntity<?> saveUser(@RequestBody UserRequestDTO user) throws GlobalBusinessException
	{
		LOG.info("saveUser - Se recibió objeto user: " + user.toString());
		
		if(userService.existsByEmail(user.getEmail()))
            return new ResponseEntity<Object>(new MensajeResponse("El correo ya registrado"), HttpStatus.BAD_REQUEST);

		if(!emailValidator.validate(user.getEmail()))
			return new ResponseEntity<Object>(new MensajeResponse("El correo tiene formato erróneo"), HttpStatus.BAD_REQUEST);
		
		UserResponseDTO userResponse = userService.saveUser(user);
		
        String token = jwtProvider.generateToken(user.getEmail());
		
		userResponse.setToken(token);
		
		return new ResponseEntity<Object>(userResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(value="createRol")
	public ResponseEntity<?> createRol(@RequestBody RolEntity rol) 
	{
		LOG.info("createRol - Se recibió objeto rol: " + rol.toString());
		
		rolService.save(rol);
		
		return new ResponseEntity<Object>(new MensajeResponse("Rol creado con éxito"), HttpStatus.CREATED);
	}
	
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorResponse handleException(Exception ex)
    {
        LOG.error("Entrando a handleException()");

        ErrorResponse errorResponse = new ErrorResponse();

        String code = (ex instanceof GlobalBusinessException)
            ? ((GlobalBusinessException) ex ).getCode()
            : String.valueOf(ErrorCode.BASE)
            ;
        String message = (ex instanceof GlobalBusinessException)
                ? ex.getMessage()
                : String.valueOf(ErrorMessage.BASE)
                ;

        LOG.error(""+ex);

        errorResponse.setCode(code);
        errorResponse.setMessage(message);

        return errorResponse;
    }
}
