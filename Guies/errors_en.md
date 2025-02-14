# Error Messages

The protocol allows the **server** to send an error message at any time to communicate unexpected situations or incorrect parameters, among other issues. Messages of type `ERROR` will indicate an error code and optionally an error description. To encode the error message, the length $N$ (where $N$ is the number of characters) of the message is sent first (it may be zero), followed by the message itself.

`C <------------- ERROR (errorId, message) ------------ S`

| **Parameter** | **Type**         | **Format**        |
| ------------- | ---------------- | ----------------- |
| errorId       | Numeric          | 1 byte            |
| message       | Numeric + String | Int32 + $N$ bytes |

The predefined error codes in the protocol are described in the following table:

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
