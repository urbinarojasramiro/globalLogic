# globalLogic
El WS se probó en Spring tools suite

El WS consta de 3 métodos
/userApiRest/createRol
/userApiRest/saveUser
/userApiRest/getUsers

Para poder crear usuarios es necesario crear el rol antes con el método:
   /userApiRest/createRol 
utilizando el siguiente json:
  {
      "id": 1
      , "rolName": "ROLE_USER"
  }

Solo el rol "ROLE_USER" está configurado, ya que dentro del código se asigna de manera automática

Para crear el usuario es necesario ejecutar el siguiente método:
  userApiRest/saveUser
Utilizando el siguiente json:
{
    "name": "Juan Rodriguez"
    , "email":"juan@rodriguez.org"
    , "password":"nu123Hhh"
    , "phones" : [
        {
            "number" : "+5691234567",
            "citycode" : "1",
            "contrycode" : "57"
        }
    ]
}

Para obtener los usuarios que se han ido creando se debe utilizar el siguiente método:
/userApiRest/getUsers
Se debe enviar por medio del header el token entregado en el método anterior con el nombre Authorization


