# spring-rest-hateoas

## CAMBIAR MYSQL USER Y CONTRASEÑA EN application.properties
## REST resources

base URL = http://localhost:8989/api

Lista con todos los equipos -> GET /teams

Equipo por id  ->  GET /teams/{id}

Equipo por nombre  ->  GET /teams?name=xxxx

Equipo por estadio  ->  GET /teams?stadium=xxxx

Lista con todos los jugadores -> GET /players

Jugador por id  -> GET /players/{id}

Lista de jugadores que pertenecen a un mismo equipo  -> GET /players?teamId=xxx
