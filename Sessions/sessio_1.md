

En aquesta sessió clarificarem els dubtes sobre la codificació dels missatges i el protocol en si. També començarem la codificació de la pràctica.


### Sessió 1

En aquesta sessió començarem a veure 

```mermaid
---
title: Possibles classes per implementar el joc
---
classDiagram    
    class ComUtils
    class GameHandler:::mandatoryClass
    class BattleshipGame:::mandatoryClass {
        int gameId
    }
    class GamePlayer {        
        int playerId        
    }
    class INetworkObject {
        <<interface>>
        byte[] toBytes()
    }    
    class GameStatus
    class Vessel
    class BattleshipComUtils:::mandatoryClass
    class GameBoard
    class IBattleshipGame:::mandatoryClass {
        <<interface>>        
    }
    ComUtils <|-- BattleshipComUtils     
    GameHandler *-- GamePlayer: manages
    GamePlayer "1..2" -- "1" BattleshipGame:play       
    IBattleshipGame <|.. BattleshipGame
    GamePlayer ..> GameBoard: board1
    GamePlayer ..> GameBoard: board2
    INetworkObject <|.. GameBoard
    INetworkObject <|.. GamePlayer
    INetworkObject <|.. GameStatus
    GamePlayer ..> BattleshipComUtils
    BattleshipGame ..> GameStatus
    GameBoard "1" ..> "*" Vessel

    classDef mandatoryClass fill:#ff00003d

```



#### Deures:
* Realitzar la funcio per llegir el tipus de dada "string variable"
* Fer les primeres trames (Hello, Admit, etc) a Servidor i Client (cadascuna on toqui) i comprovar la comunicació entre ambdós.

