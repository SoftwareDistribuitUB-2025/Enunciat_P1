# Tic-tac-toe

## Resum

Tic-tac-toe és un joc que es juga amb paper i llapis per a dos jugadors que, per torns, indiquen els espais d'una graella de tres per tres amb X o O. El jugador que aconsegueixi col·locar tres de les seves marques en una línia horitzontal, vertical o diagonal és el guanyador. En cas contrari, hi ha un empat.

## Protocol

### [1] Inici de sessió

La partida la inicia el __client__ amb una comanda `HELLO`. Si és la primera vegada que es conecta, enviará com a `idSessio` un zero:

`C ------- HELLO (idSessio, nomJugador) ------> S`

En aquest missatge, també s'especifica el nom del jugador (`nomJugador`).

El __servidor__ contestarà una comanda `READY` i genera el `idSessio` usant la funció `Random()` i serà de cinc dígits. El `idSessio` no pot començar per zero. 

Si per algun motiu no es pogués iniciar la sessió de joc, el __servidor__ enviarà un `ERROR` d'_Inici de Sessió Incorrecte_.

`C <------------- READY (idSessio) ------------ S`

En el cas de que el __client__ no sigui la primera vegada que es conecta, enviará el seu `idSessio` que anteriorment __servidor__ li ha enviat. Per tant, __servidor__ comprovará sempre que aquest id sigui o zero o un id que ja ha guardat prèviament. No hi haurà persistència de dades en __servidor__ (si es tanca i s'obre de nou, no "recordarà" res).

### [2] Procés de joc

A continuació el __client__ pot començar la partida amb un `PLAY`, passant el `idSessio` confirmat anteriorment.

`C ------------- PLAY (idSessio) -------------> S`

El __servidor__ comprovarà que aquest `idSessio` sigui el mateix que s'ha enviat anteriorment i contestarà amb un `ADMIT` juntament amb un `flag` que prendrà valor `1` si és correcte o amb un `0` si no ho és.

`C <--------- ADMIT (idSessio, flag) ---------- S`

A continuació el __client__ enviarà el seu moviment `ACTION` juntament amb quina posició està marcant (on el __client__ posa la seva `X`):

`C -------- ACTION (idSessio, posició) -------> S`
`S -------- ACTION (idSessio, posició) -------> C`

Aquesta posició estarà representada per una cadena de bytes on el primer indica la fila, un guió "-" de separació i seguidament un byte que indica la columna. Aquests indicadors aniran del 0 a 2 de manera que definiran qualsevol cel·la de la graella de 3x3. Un exemple podria ser: "1-0", que indicaria fila 1 i columna 0:

| 0-0 | 0-1 | 0-2 |
|-----|-----|-----|
| 1-0 | 1-1 | 1-2 |
| 2-0 | 2-1 | 2-2 |

El __servidor__ comprovarà que el moviment rebut sigui vàlid. En cas que el moviment no existeixi es retornarà un `ERROR` de _Moviment Desconegut_. En cas que el moviment no sigui vàlid (perquè la cel·la que s'ha seleccionat ja està emplenada), retornarà un `ERROR` de _Moviment Invalid_.

`C <---- ERROR (idSessio, errCodi, errMsg) ---- S`

| errCodi | errMsg              |
|:-------:|:--------------------|
| 0       | Moviment Desconegut |
| 1       | Moviment Invalid    |
| 9       | Sessio Incorrecte   |

En cas contrari, el _servidor_ enviarà o un altre `ACTION` o, en cas d'arribar a un final de partida, un `RESULT`:

`C <----- RESULT (idSessio, result) ------- S`

| Result |
|:-------|
| 0      |
| 1      |
| 2      |

Si el __client__ rep un `END`, la partida ha acabat. Result és un flag on "1" vol dir que guanya el client i un "0" vol dir que guanya el Servidor. Un "2" significa empat. Si el _client_ rep una trama `ACTION`, segueix la partida.

Un cop s'hagi acabat una partida, es podrà tornar a jugar una nova partida en la mateixa sessió, per fer això caldrà enviar el missatge `PLAY`.

`C ------------- PLAY (idSessio) -------------> S`

### [3] Forçar un final

El __client__ pot acabar quan vulgui les partides, només cal que es desconnecti. **Atenció**: la desconnexió d'un client s'ha de gestionar "en tot moment", tant a Servidor com a Client, i no veure cap _stacktrace_ d'error.

## Detalls dels missatges

Tant el __client__ (`C`) com el __servidor__ (`S`) suporten tipus de missatges amb els següents codis d'operacions:

| Missatge | Codi | Direcció      |
|:---------|:-----|:--------------|
| HELLO    | 1    | `C-S`         |
| READY    | 2    | `S-C`         |
| PLAY     | 3    | `C-S`         |
| ADMIT    | 4    | `S-C`         |
| ACTION   | 5    | `C-S` / `S-C` |
| RESULT   | 6    | `S-C`         |
| ERROR    | 8    | `S-C` / `C-S` |

La capçalera d'un missatge conté el codi d'operació associat i els paràmetres necessaris.

Els tipus de dades de les capçaleres es detallen a continuació:

- Els camps de tipus **string variable** representen cadenes de bytes codificats en **un byte** de JAVA. Per saber quan hem de deixar de llegir, posarem dos bytes 0 en format de xarxa (Big Endian)). Aquest tipus de dada **no està implementada en el ComUtils original**.

- Així mateix, els camps amb tipus d'un o diversos bytes, aquests bytes són bytes en format de xarxa (Big Endian).
- Els camps de tipus int32 és un sencer de 32 bits (4 bytes) en format xarxa

Les trames o paquets es detallen a continuació:

- El paquet `HELLO` (codi d'operació `1`) té el format que es mostra en la _Figura 1_, on `idSessio` és un `int32` i on 'name' és el nom del jugador com a `string variable`.

```
     1 byte   int32           n_bytes_var  2 bytes
    +--------+---------------+------------+------+
    | Opcode | idSessio      | nomJugador | 00   |
    +--------+---------------+------------+------+
    Figura 1: Missatge HELLO
```

- El paquet `READY` (codi d'operació `2`) té el format que es mostra a la _Figura 2_, on `idSessio` és un `int32`:

```
     1 byte   int32
    +--------+---------------+
    | Opcode | idSessio      |
    +--------+---------------+
    Figura 2: Missatge READY
```

- El paquet `PLAY` (codi d'operació `3`) té el format que es mostra a la _Figura 3_, on `idSessio` és un `int32`.

```
     1 byte   int32
    +--------+---------------+
    | Opcode | idSessio      |
    +--------+---------------+
    Figura 3: Missatge PLAY
```

- El paquet `ADMIT` (codi d'operació `4`) té el format que es mostra a la _Figura 4_, on _flag_ és un byte en format xarxa on `0` vol dir no admès i `1` vol dir admès.

```
     1 byte   int32           1 byte
    +--------+---------------+------+
    | Opcode | idSessio      | flag |
    +--------+---------------+------+
    Figura 4: Missatge ADMIT
```

- El paquet `ACTION` (codi d'operació `5`) té el format que es mostra a la _Figura 5_, on _posició_ és una paraula com a una cadena de 3 bytes tal com s'ha explicat anteriorment.

```
     1 byte   int32           3 bytes
    +--------+---------------+---------+
    | Opcode | idSessio      | posició |
    +--------+---------------+---------+
    Figura 5: Missatge ACTION
```


- El paquet `RESULT` (codi, operació `6`) té el format que es mostra en la _Figura 6_, on _flag_ és 1 byte amb el resultat de la partida tal qual s'ha explicat anteriorment.

```
     1 byte   int32            1 byte
    +--------+---------------+---------+
    | Opcode | idSessio      | flag    | 
    +--------+---------------+---------+
    Figura 6: Missatge RESULT
```

- El paquet `ERROR` (codi d'operació `8`) té el format que es mostra en la _Figura 8_, on `errCode` és és un byte en format xarxa i on `errMsg` és un codi com a `string variable`.

```
     1 byte   int32           1 byte    n_bytes_var   2 byte
    +--------+---------------+---------+-----------+------+
    | Opcode | idSessio      | errCodi | errMsg    | 00   |
    +--------+---------------+---------+-----------+------+
    Figura 8: Missatge ERROR
```

## Extensions

### Multi-threading

Un cop s'expliqui a teoria el multi-threading, el __servidor__ s'haurà de canviar per a suportar múltiples __clients__ simultàniament, és a dir, jugar diferents partides a la vegada.

### Mode 2 jugadors

També s'haurà de poder fer que juguin dos jugadors (dos __clients__), fent el __servidor__ de proxy entre els dos jugadors.
