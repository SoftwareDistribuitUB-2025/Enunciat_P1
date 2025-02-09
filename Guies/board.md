# Representació tauler de joc

En aquest apartat es descriu la codificació dels taulers de joc, tal com el __servidor__ els ha d'enviar al __client__. Aquesta representació s'utilitza en els missatges de tipus `GAMESTATUS`. 

Donat un tauler reduït de mida $W\times H = 4\times 3$ com el següent:

|   | 1 | 2 | 3 | 4 | 
|---|---|---|---|---|
| 1 |   |   |   |   |
| 2 |   |   |   |   |
| 3 |   |   |   |   |

El representarem amb **1 byte** per cada cel·la, i per files. Per tant, tindrem una sèrie de $12$ bytes. Si $v_{r,c}$ és el valor de la cel·la amb fila $r$ i columna $c$, el tauler quedarà com:

| $v_{1,1}$ | $v_{1,2}$ | $v_{1,3}$ | $v_{1,4}$ | $v_{2,1}$ | $v_{2,2}$ | $v_{2,3}$ | $v_{2,4}$ | $v_{3,1}$ | $v_{3,2}$ | $v_{3,3}$ | $v_{3,4}$ | 
|---|---|---|---|---|---|---|---|---|---|---|---|

La representació de $v_{r,c}$ dependrà de si estem representant el nostre tauler `board1`, del que sabem la posició dels vaixells i els seus tipus, o del tauler de l'oponent `board2`, on només tenim les caselles que hem disparat i si hem fallat o tocat.

## Tauler pròpi
En el cas del `board1`, utilitzarem una codificació del byte per representar tota la informació necessària. Sabem que en $1 byte$ tenim $256$ valors, que van del $0$ al $255$. Podem veure aquest valor com a $3$ dígits $<d_1,d_2,d_3>$, on agafarem $n=<d_1,d_2> \in [0,25]$ i $t = <d_3>\in [0,9]$. Per tant, si tenim el valor $134$, el descomposarem en $n=13$ i $t=4$. 

El valor $t$ ens indicarà el tipus de vaixell que hi ha en la cel·la, seguint la codificació indicada en la [descripció del joc](./battleship.md), on el valor $0$ serà un valor especial que detallarem després, i els valors $1,\ldots, 5$ els tipus $T_1,\ldots,T_5$ respectivament. El valor $n$ serà la instància de vaixell, que començarà en $0$ i podrà arribar a $25$. En el cas $t=0$, utilitzarem $n$ per codificar l'estat de la cel·la, que podrà ser $0$ si està buida, $1$ si està tocat el vaixell que hi ha, o $2$ si el vaixell corresponent està enfonsat. Podem resumir la codificació en la següent taula:

| **Valor**  |    **Tipus**     |  **Format**       |  **Significat**       |
|------------|------------------|-------------------|-----------------------|
|    000     | Numèric          | 1 byte            | Aigüa                 |
|    001     | Numèric          | 1 byte            | Tocat                 |
|    002     | Numèric          | 1 byte            | Enfonsat              |
|    XY1     | Numèric          | 1 byte            | Instància $n = 10\times X + Y$ de tipus $T_1$ |
|    XY2     | Numèric          | 1 byte            | Instància $n = 10\times X + Y$ de tipus $T_2$ |
|    XY3     | Numèric          | 1 byte            | Instància $n = 10\times X + Y$ de tipus $T_3$ |
|    XY4     | Numèric          | 1 byte            | Instància $n = 10\times X + Y$ de tipus $T_4$ |
|    XY5     | Numèric          | 1 byte            | Instància $n = 10\times X + Y$ de tipus $T_5$ |
|    XY6     | Numèric          | 1 byte            | No vàlid |
|    XY7     | Numèric          | 1 byte            | No vàlid |
|    XY8     | Numèric          | 1 byte            | No vàlid |
|    XY9     | Numèric          | 1 byte            | No vàlid |

Tingueu en compte que això vol dir que com a molt podrem tenir $25$ vaixells per cada tipus en el tauler, i que un cop l'oponent ha fet `HIT` en una cel·la, perdem la informació sobre el tipus i instància de vaixell d'aquella cel·la. Per tant, si el client necessita aquesta ifnormació per visualitzar, l'haurà de guardar a partir de la configuració inicial que ha fet.

## Tauler oponent

En el cas del `board2`, cada byte tindrà un valor numèric $v_{r,c}$ segons la següent taula:

| **Valor**  |    **Tipus**     |  **Format**       |  **Significat**       |
|------------|------------------|-------------------|-----------------------|
|    0       | Numèric          | 1 byte            | No tenim informació.  |
|    1       | Numèric          | 1 byte            | Aigüa                 |
|    2       | Numèric          | 1 byte            | Tocat                 |
|    3       | Numèric          | 1 byte            | Enfonsat              |
