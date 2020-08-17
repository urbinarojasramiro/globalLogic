package com.globalExcercise.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.globalExcercise.dto.UserRequestDTO
import com.globalExcercise.entity.RolEntity
import com.globalExcercise.entity.UserEntity
import com.globalExcercise.repository.RolRepository
import com.globalExcercise.repository.UserRepository
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

class UserRestServiceTest extends Specification {

	UserRestService userRestService
    MockMvc mockMvc
    UserRepository userRepository
    RolRepository rolRepository
    ObjectMapper objectMapper = new ObjectMapper();

    def requestUrl = '/userApiRest/getUsers'
    def postRolUrl = '/userApiRest/createRol'
    def postUserUrl = '/userApiRest/saveUser'

    RolEntity rol
    UserEntity userEntity
    String rolJsonString
    String userJsonString

    void setup() {
        userRepository = Mock(UserRepository)
        rolRepository = Mock(RolRepository)
        userRestService = new UserRestService([userRepository: userRepository])
        userRestServiceRol = new UserRestService([rolRepository: rolRepository])
        mockMvc = MockMvcBuilders
                .standaloneSetup(userRestService)
                .alwaysDo(MockMvcResultHandlers.print())
                .build()
        mockMvcRol = MockMvcBuilders
                .standaloneSetup(userRestServiceRol)
                .alwaysDo(MockMvcResultHandlers.print())
                .build()
        userRequestDTO = new UserRequestDTO([name: 'Juan Rodriguez'
                                             , email:'juan@rodriguez.org'
                                             , password:'hunter2'
                                             , phones : [number : '1234567', citycode : 1, contrycode : 57]])
        rolRequestDTO = new RolEntity([id: 2
                                        , rolName: 'ROLE_USER'])
        userJsonString = mapper.writeValueAsString(userRequestDTO)
        rolJsonString = mapper.writeValueAsString(userRequestDTO)
    }

    void 'save rol'() {
        given:
        1 * rolRepository.save(rolRequestDTO) >> null

        expect:
        mockMvc.perform(MockMvcRequestBuilders
                .post(postRolUrl).contentType(MediaType.APPLICATION_JSON).content(rolJsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }

    void 'save user'() {
        given:
        1 * userRepository.save(userRequestDTO) >> null

        expect:
        mockMvc.perform(MockMvcRequestBuilders
                .post(postUserUrl).contentType(MediaType.APPLICATION_JSON).content(userJsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }

    void 'get User return a list of users'() {
        given:
        userRepository.findAll() >> [userRequestDTO]

        and:
        def response = [userJsonString].toString()

        expect:
        mockMvc.perform(MockMvcRequestBuilders
                .get(requestUri))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response))
    }

    void 'get customer returns a single customer'() {
        given:
        1 * userRepository.findByName('Juan Rodriguez') >> Optional.of(userRequestDTO)

        expect:
        mockMvc.perform(MockMvcRequestBuilders
                .get(requestUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userJsonString))
    }

}
