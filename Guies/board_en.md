# Game Board Representation

This section describes the encoding of game boards as the **server** must send them to the **client**. This representation is used in `GAMESTATUS` messages.

Given a reduced board of size $W\times H = 4\times 3$ like the following:

|     | 1   | 2   | 3   | 4   |
| --- | --- | --- | --- | --- |
| 1   |     |     |     |     |
| 2   |     |     |     |     |
| 3   |     |     |     |     |

We will represent it with **1 byte** per cell, in row-major order. Thus, we will have a series of $12$ bytes. If $v_{r,c}$ is the value of the cell at row $r$ and column $c$, the board will be structured as follows:

| $v_{1,1}$ | $v_{1,2}$ | $v_{1,3}$ | $v_{1,4}$  | $v_{2,1}$ | $v_{2,2}$ | $v_{2,3}$ | $v_{2,4}$  | $v_{3,1}$ | $v_{3,2}$ | $v_{3,3}$ | $v_{3,4}$  |
| --------- | --------- | --------- | ---------- | --------- | --------- | --------- | ---------- | --------- | --------- | --------- | ---------- |

The representation of $v_{r,c}$ depends on whether we are representing our own board, `board1`, where we know the positions and types of ships, or the opponent’s board, `board2`, where we only have information about the cells we have shot at and whether we missed or hit.

## Own Board

For `board1`, we will use a byte encoding to store all necessary information. Since a **1-byte** value ranges from $0$ to $255$, we can interpret it as three digits `<d_1, d_2, d_3>`, where we take $n=<d_1,d_2> \in [0,25]$ and $t = <d_3>\in [0,9]$. Thus, for a value of $134$, we decompose it into $n=13$ and $t=4$.

The value $t$ indicates the type of ship in the cell, following the encoding described in the [game description](./battleship_en.md), where $0$ is a special value detailed below, and values $1,\ldots,5$ correspond to ship types $T_1,\ldots,T_5$ respectively. The value $n$ represents the ship instance, starting from $1$ and going up to $25$. When $t=0$, we use $n$ to encode the cell’s state:

- $0$ if it is empty
- $1$ if it is empty but the opponent has shot at it (missed)
- $2$ if a ship in the cell has been hit
- $3$ if the ship in the cell has been sunk

We summarize the encoding in the following table:

| **Value** | **Type** | **Format** | **Meaning**                                      |
| --------- | -------- | ---------- | ------------------------------------------------ |
| 000       | Numeric  | 1 byte     | Water                                            |
| 010       | Numeric  | 1 byte     | Missed shot                                      |
| 020       | Numeric  | 1 byte     | Hit                                              |
| 030       | Numeric  | 1 byte     | Sunk                                             |
| XY1       | Numeric  | 1 byte     | Ship instance $n = 10\times X + Y$ of type $T_1$ |
| XY2       | Numeric  | 1 byte     | Ship instance $n = 10\times X + Y$ of type $T_2$ |
| XY3       | Numeric  | 1 byte     | Ship instance $n = 10\times X + Y$ of type $T_3$ |
| XY4       | Numeric  | 1 byte     | Ship instance $n = 10\times X + Y$ of type $T_4$ |
| XY5       | Numeric  | 1 byte     | Ship instance $n = 10\times X + Y$ of type $T_5$ |
| XY6       | Numeric  | 1 byte     | Invalid                                          |
| XY7       | Numeric  | 1 byte     | Invalid                                          |
| XY8       | Numeric  | 1 byte     | Invalid                                          |
| XY9       | Numeric  | 1 byte     | Invalid                                          |

Note that this means we can have at most **25 ships of each type** on the board. Once an opponent scores a `HIT` on a cell, we lose information about the ship's type and instance in that cell. If the client needs this information for visualization, it must store it from the initial board configuration.

## Opponent's Board

For `board2`, each byte will have a numerical value $v_{r,c}$ according to the following table:

| **Value** | **Type** | **Format** | **Meaning**              |
| --------- | -------- | ---------- | ------------------------ |
| 0         | Numeric  | 1 byte     | No information available |
| 1         | Numeric  | 1 byte     | Water (Missed shot)      |
| 2         | Numeric  | 1 byte     | Hit                      |
| 3         | Numeric  | 1 byte     | Sunk                     |
