# Protocol

En aquest apartat es descriu el protocol de communicació que s'estableix entre el client del joc i el servidor. El format genèric dels missatges, així com els diferents codis de missatge estan descrits en [aquest document](./messages.md).

Cal tenir en compte que no hi haurà persistència de dades en el __servidor__, per tant, si es tanca i s'obre un nou servidor, es perdrà tota la informació de partides i jugadors.

## 1. Inici de la partida

La partida la inicia el __client__ amb una comanda `CREATE` si vol crear una nova partida, o amb `JOIN` si es vol unir a una partida ja existent:

`C ------- CREATE (nomJugador, W, H, T1, T2, T3, T4, T5, AI) ------> S`

`C ------- JOIN (nomJugador) ------> S`

En aquest missatge, s'especifica el nom del jugador `nomJugador`, i en cas de voler crear una nova partida, els detalls d'aquesta, essent `W` i `H` la mida del tauler i `TN`el nombre de vaixells de tipus $T_N$ que es poden posar. Veure la descripció de cada paràmetre en la [definició del joc](./battleship.md). A continuació s'especifica el format dels paràmetres:

| **Paràmetre**  |    **Tipus**    |  **Format**       |
|----------------|-----------------|-------------------|
|  nomJugador    | Cadena          | 50 bytes ASCII    |
|         W      | Numèric         | 1 byte            |
|         H      | Numèric         | 1 byte            |
| $T_1\dots T_5$ | Numèric         | 1 byte            |
| AI             | Booleà          | 1 byte            |

El __servidor__ contestarà una comanda `OK`, retornant un valor `playerId` que permetrà a l'usuari jugar en aquesta partida, i un `gameId` que li permetrà identificar la partida. Tant el `playerId` com el `gameId` es generaran per part del servidor usant la funció `Random()`, seran de cinc dígits i no podran començar per zero. 

`C <------------- OK (playerId, gameId) ------------ S`

| **Paràmetre**  |    **Tipus**    |  **Format** |
|----------------|-----------------|-------------|
|    playerId    | Numèric         | Int32       |
|    gameId      | Numèric         | Int32       |

Si per algun motiu no es pogués iniciar la partida, el __servidor__ enviarà un missatge `ERROR`. Consulteu el format dels missatges d'error en [aquest document](./errors.md).

Finalment, en cas de que per algun motiu el __client__ s'hagi desconnectat, podrà sol·licitar unir-se a la partida que estava jugant, mitjançant un missatge `REJOIN`. En aquest cas, el servidor generarà un nou `playerId` per al jugador i contestarà amb el missatge `OK` igual que en el cas anterior. Cal tenir en compte, que el `playerId` assignat anteriorment deixarà de ser vàlid a partir de que s'hagi generat el nou.

`C ------- REJOIN (nomJugador) ------> S`

| **Paràmetre**  |    **Tipus**    |  **Format**       |
|----------------|-----------------|-------------------|
|  nomJugador    | Cadena          | 50 bytes ASCII    |

En cas de que la partida ja no existeixi, el __servidor__ eliminarà el nom del jugador de la llista de jugadors, i per tant aquest podrà tornar a iniciar noves partides sense obtenir error per nom de jugador repetit.

Un cop la partida tingui tots els jugadors assignats, el servidor ho notificarà als __clients__ amb un missatge `GAMESTATUS`. En aquest missatge s'indicarà el `gameState`, l'informació del tauler del pròpi jugador `board1`, l'informació del tauler de l'oponent (sigui humà o no) `board2` i una informació que variarà depenent de l'estat en que estigui el joc. 

`C <------------- GAMESTATUS (gameState, boardSize, board1, board2, info) ------------ S`

| **Paràmetre**  |    **Tipus**      |  **Format**                          |
|----------------|-------------------|--------------------------------------|
|    gameState  | Numèric           | 1 byte                               |
|    boardSize      | Nombre de cel·les del tauler | Int32. Veure [representació taulers](./board.md) |
|    board1      | Cel·les per files | $W\times H$ bytes. Veure [representació taulers](./board.md) |
|    board2      | Cel·les per files | $W\times H$ bytes. Veure [representació taulers](./board.md) |
|    info        | $N$ bytes         | Depenent estat. Veure taula següent  |

Teniu una definició dels diferents estats del joc a [l'informació inicial](./battleship.md) i la representació format del missatge segons l'estat del joc:

| **gameState**      |  **Informació**         | **Tipus**  |  **Format**                     |
|---------------------|-------------------------|------------|---------------------------------|
| WAITING_PLAYERS (1) |                         |            |                                 |
| SETUP (2)           | Jugador1 preparat (0/1) | Booleà     |  1 byte                         |
|                     | Jugador2 preparat (0/1) | Booleà     |  1 byte                         |
|                     | # Vaixells pendents de posar de tipus $T_1$ | Numèric     |  1 byte               |
|                     | # Vaixells pendents de posar de tipus $T_2$ | Numèric     |  1 byte               |
|                     | # Vaixells pendents de posar de tipus $T_3$ | Numèric     |  1 byte               |
|                     | # Vaixells pendents de posar de tipus $T_4$ | Numèric     |  1 byte               |
|                     | # Vaixells pendents de posar de tipus $T_5$ | Numèric     |  1 byte               |
| PLAYING (3)         | Torn del Jugador1 (0/1) | Booleà     |  1 byte                         |
|                     | Torn del Jugador2 (0/1) | Booleà     |  1 byte                         |
| FINISHED (4)        | Ha guanyat el Jugador1 (0/1) | Booleà     |  1 byte                    |
|                     | Ha guanyat el Jugador2 (0/1) | Booleà     |  1 byte                    |

Per exemple, un missatge de tipus `GAMESTATUS` per una partida en la que l'oponent ja ha acabat de posar els seus vaixells, però a nosaltres ens en falta un per posar un de tipus $T_2$ i dos de tipus $T_5$ (estem en la fase `SETUP`), el missatge es representaria com:

| **messageType** | **gameStatus** | **boardSize** | **board1** | **board2** | **P1 ready** | **P2 ready** | $\bf{T_1}$ | $\bf{T_2}$ | $\bf{T_3}$ | $\bf{T_4}$ | $\bf{T_5}$ |
|-----------------|----------------|------------|------------|--------------|--------------|-------|------------|------------|------------|------------|---------------|
| 9               | 2    | $W\times H$   | $v_{1,1},\dots,v_{W,H}$ | $v_{1,1},\dots,v_{W,H}$|0|1|0|1|0|0|2|
| <1 byte> | <1 byte> | <4 bytes> | <${W\times H}$ bytes> | <${W\times H}$ bytes> | <1 byte> | <1 byte> | <1 byte> | <1 byte> | <1 byte> | <1 byte> | <1 byte> |

## 2. Configuració de la partida

Un cop hem iniciat una partida, el primer pas és ubicar els vaixells en la quadrícula. El __client__ pot consultar la configuració de la partida amb una comanda `GETCONFIG`, i el __servidor__ li contestarà amb un missatge `GAMECONFIG` amb els paràmetres de la partida:

`C ------- GETCONFIG (playerId, gameId) ------> S`

| **Paràmetre**  |    **Tipus**    |  **Format** |
|----------------|-----------------|-------------|
|    playerId    | Numèric         | Int32       |
|    gameId      | Numèric         | Int32       |


`C <------------- GAMECONFIG (W, H, T1, T2, T3, T4, T5) ------------ S`

| **Paràmetre**  |    **Tipus**    |  **Format** |
|----------------|-----------------|-------------|
|         W      | Numèric         | 1 byte      |
|         H      | Numèric         | 1 byte      |
| $T_1\dots T_5$ | Numèric         | 1 byte      |

Cal tenir en compte que $T_i$ correspon al nombre de vaixells de tipus $T_i$ que es poden posar. 

El __client__ va afegint els diferents vaixells enviant missatges `ADDVESSEL` al __servidor__. A banda de l'identificador de jugador i de joc, se li passarà el tipus de vaixell **type** (valor de 1 a 5), la fila i columna inicials **(ri, ci)** i finals **(rf, cf)**. Tant la fila com la columna es numeren a partir de $1$.

El qual contestarà amb missatges `OK` si s'ha afegit el vaixell correctament o `ERROR` en cas d'error.

`C ------- ADDVESSEL (playerId, gameId, type, ri, ci, rf, cf) ------> S`

| **Paràmetre**  |    **Tipus**    |  **Format** |
|----------------|-----------------|-------------|
|    playerId    | Numèric         | Int32       |
|    gameId      | Numèric         | Int32       |
|    type        | Numèric         | 1 byte      |
|    ri          | Numèric         | 1 byte      |
|    ci          | Numèric         | 1 byte      |
|    rf          | Numèric         | 1 byte      |
|    cf          | Numèric         | 1 byte      |

## 3. Jugar la partida

Finalitzada la configuració, ja podem començar el joc en si. El __client__ fa una jugada disparant (`SHOT`) a una cel·la $(r, c)$ de l'oponent:

`C ------- SHOT (playerId, gameId, r, c) ------> S`

| **Paràmetre**  |    **Tipus**    |  **Format** |
|----------------|-----------------|-------------|
|    playerId    | Numèric         | Int32       |
|    gameId      | Numèric         | Int32       |
|    r           | Numèric         | 1 byte      |
|    c           | Numèric         | 1 byte      |

Si tot ha anat bé, el __servidor__ ens contestarà amb un missatge de tipus `HIT` o `FAIL` segons si a la cel·la $(r,c)$ hi havia un vaixell o no. A més a més, en el cas de `HIT`, rebrem un paràmetre `Sink` que ens indicarà si era l'última posició viva del vaixell i l'hem enfonsat, o no. En cas d'error, rebrem un missatge de tipus `ERROR`.

`C <------------- HIT (Sink) ------------ S`

`C <------------- FAIL ------------ S`

| **Paràmetre**  |    **Tipus**    |  **Format** |
|----------------|-----------------|-------------|
|      Sink      | Booleà          | 1 byte      |


Un cop processada la jugada, el servidor ens enviarà un missatge de tipus `GAMESTATUS`. Les opcions són que puguem continuar jugant (en cas de `HIT`), que l'oponent hagi fet alguna jugada en cas de `FAIL`, o tant en un cas com en l'altre, que s'hagi finalitzat la partida.

En qualsevol moment, un __client__ pot abandonar una partida. Això ho farà enviant un missatge de tipus `LEAVE`al __servidor__.

`C ------- LEAVE (playerId, gameId) ------> S`

El servidor contestarà amb un missatge `OK` si tot ha anat bé, o amb un `ERROR`. Un cop abandonada la partida, ja no podrem accedir als resultats ni interaccionar amb ella.

Finalment, el client sempre pot demanar l'estat actual de la partida, mitjançant un missatge `GETSTATUS`. 

`C ------- GETSTATUS (playerId, gameId) ------> S`

El servidor contestarà amb un missatge `GAMESTATUS`.