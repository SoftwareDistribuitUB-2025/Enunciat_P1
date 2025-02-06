# Missatges d'error

El protocol permet l'enviament per part del __servidor__ d'un missatge d'error en qualsevol moment, per comunicar situacions sobrevingudes o paràmetres incorrectes entre altres. Els missatges de tipus `ERROR` indicaran un codi d'error i opcionalment una descripció de l'error. Per codificar el missatge d'error, s'enviarà primer la longitud $N$ (on $N$ és el nombre de caràcters) del missatge (pot ser zero), i a continuació el missatge.

`C <------------- ERROR (errorId, message) ------------ S`

| **Paràmetre**  |    **Tipus**     |  **Format**       |
|----------------|------------------|-------------------|
|    errorId     | Numèric          | 1 byte            |
|    message     | Numèric + Cadena | Int32 + $N$ bytes |

Els codis d'error predefinits en el protocol es descriuen en la següent taula:

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

