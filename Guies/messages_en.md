# Messages

Communication between the **client** and the **server** is carried out through message exchanges. Each message consists of a header indicating the message type and a body containing the message information, as specified in [the protocol](./battleship_en.md).

| **Parameter** | **Type** | **Format**      |
| ------------- | -------- | --------------- |
| messageType   | Numeric  | 1 byte          |
| body          | N bytes  | As per protocol |

The message codes (`messageType`), along with the associated errors for each message, are listed in the following table:

| **messageType** | **Message** | **Related [Error Codes](errors_en.md)** |
| --------------- | ----------- | --------------------------------------- |
| 0               | ERROR       |                                         |
| 1               | OK          |                                         |
| 2               | CREATE      | 1, 5                                    |
| 3               | JOIN        | 1                                       |
| 4               | REJOIN      | 2, 5                                    |
| 5               | GETCONFIG   | 3, 4                                    |
| 6               | GAMECONFIG  |                                         |
| 7               | ADDVESSEL   | 3, 4, 7, 8, 9, 10                       |
| 8               | GETSTATUS   | 3, 4                                    |
| 9               | GAMESTATUS  |                                         |
| 10              | SHOT        | 3, 4, 9, 10                             |
| 11              | HIT         |                                         |
| 12              | FAIL        |                                         |
| 13              | LEAVE       | 2, 5                                    |

**Note:** The table only lists errors directly related to a specific message. However, some errors may occur at any time, either as a response to a message or due to unexpected situations.

### Error Codes

| **errorId** | **Description**            |
| ----------- | -------------------------- |
| 0           | Unknown error              |
| 1           | Player name already exists |
| 2           | Invalid player name        |
| 3           | Invalid game identifier    |
| 4           | Invalid player identifier  |
| 5           | Game not available         |
| 6           | Incorrect game parameters  |
| 7           | Incorrect length           |
| 8           | Type not available         |
| 9           | Incorrect coordinate       |
| 10          | Incorrect state            |
