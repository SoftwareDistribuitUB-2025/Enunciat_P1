# Sessió de proves creuades

| Grup | Data                   | Aula |
| ---- | ---------------------- | ---- |
| F    | 26/03/2025 17:00-19:00 | IB   |

## Informació grups participants

A la següent taula teniu definida la adreça IP i la informació per a tots els grups.

| Grup | IP                               | Port single-player | Port multi-player  | SRV join           | SRV setup          | SRV play           | CLI Join           | CLI setup          | CLI play           |
| ---- | -------------------------------- | ------------------ | ------------------ | ------------------ | ------------------ | ------------------ | ------------------ | ------------------ | ------------------ |
| F01  | 10.111.168.70 / 161.116.11.198   | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F02  |                                  | 8080               |                    |                    |                    |                    |                    |                    |                    |
| F03  | 10.133.30.156 / 72.14.201.63     | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F04  | 10.111.166.98 / 161.116.111.226  | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F05  |                                  | 8080               | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F07  | 161.116.52.55 / 161.116.52.55    | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F08  | 161.116.52.64 / 161.116.52.64    | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F09  | 161.116.52.58 / 161.116.52.58    | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F10  | 10.111.150.170 / 161.116.111.170 | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F11  | 10.111.129.172 / 161.116.111.172 | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F12  | 10.111.167.90 / VM               | 8080               | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F13  | 10.111.152.82 / 161.116.111.210  | 8080               | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |
| F14  |                                  | 8080               | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |

**TODO:** Create a row per each group. Use :x: to mark that an option is not implemented.

## Assignació de proves

A continuació es mostra la llista de proves que cada grup ha de fer de forma **obligatòria**. Un cop realitzades aquestes podeu provar amb altres grups.

| Grup (CLI) | Prova 1 (SRV) | Prova 2 (SRV) |
| ---------- | ------------- | ------------- |
| F01        | F03           | F04           |
| F03        | F04           | F05           |
| F04        | F05           | F07           |
| F05        | F07           | F08           |
| F07        | F08           | F09           |
| F08        | F09           | F10           |
| F09        | F10           | F11           |
| F10        | F11           | F12           |
| F11        | F12           | F13           |
| F12        | F13           | F14           |
| F13        | F14           | F01           |
| F14        | F01           | F03           |

**TODO:** Define test groups using previous table.
