# Missatges

La comunicació entre el __client__ i el __servidor__ es farà mitjançant l'intercanvi de missatges. Cada missatge consistirà en una capçalera en la que s'indicarà el tipus de missatge, i un cos la informació del missatge, tal com queda estipulat en [el protocol](./battleship.md).

| **Paràmetre**  |    **Tipus**     |  **Format**       |
|----------------|------------------|-------------------|
|  messageType   | Numèric          | 1 byte            |
|  body          | N bytes          | Segons protocol   |

Els codis de missatge (messageType), així com els errors associats a cada missatge, queden recollits a la següent taula:

| **messageType** | **Missatge** | **[Codis d'error](errors.md) relacionats** |
|-----------------|--------------|--------------------------------------------|
|   0             | ERROR        |                                            |
|   1             | OK           |                                            |
|   2             | CREATE       | 1, 5                                       |
|   3             | JOIN         | 1                                          |
|   4             | REJOIN       | 2, 5                                       |
|   5             | GETCONFIG    | 3, 4                                       |
|   6             | GAMECONFIG   |                                            |
|   7             | ADDVESSEL    | 3, 4, 7, 8, 9, 10                          |
|   8             | GETSTATUS    | 3, 4                                       |
|   9             | GAMESTATUS   |                                            |
|   10            | SHOT         | 3, 4, 9, 10                                |
|   11            | HIT          |                                            |
|   12            | FAIL         |                                            |
|   13            | LEAVE        | 2, 5                                       |

__Nota:__ La taula només mostra els missatges directament relacionats a un missatge determinat, però cal tenir en compte que alguns errors es poden donar en qualsevol moment, ja sigui com a resposta d'un missatge o per situacions sobrevingudes.




| **errorId** |  **Descripció**                          |
|-------------|------------------------------------------|
|   0         | Error desconegut                         |  
|   1         | El nom de jugador ja existeix            |  
|   2         | El nom de jugador és invàlid             |  
|   3         | L'identificador de partida no és invàlid |  
|   4         | L'identificador de jugador no és invàlid |  
|   5         | Partida no disponible                    |  
|   6         | Paràmetres de partida incorrectes        |  
|   7         | Longitud incorrecta                      |  
|   8         | Tipus no disponible                      |  
|   9         | Coordenada incorrecta                    |  
|   10        | Estat incorrecte                         |  
