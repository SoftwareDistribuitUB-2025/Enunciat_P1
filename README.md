## Software Distribuït 2024

| Professor    | Mail            | Classes                                                               |
|--------------|-----------------|-----------------------------------------------------------------------|
| Eloi Puertas | epuertas@ub.edu | Teoria, dimecres de 15 a 17h. |
| Kaisar Kushibar| kaisar.kushibar@ub.edu| Pràctiques, dimecres de 17 a 19h (Grup F)|
| Xavier Baró | xbaro@ub.edu |Pràctiques, dijous de 17 a 19h (Grup B), dijous de 19 a 21 (Grup C) |

## Avaluació

* Es treballarà amb GitHub, on cada parella tindrà un repositori. És obligatori que tota la feina realitzada es vegi **contrastada a GitHub de manera continuada**, és a dir, no pot haver-hi un "push" enorme el dia abans de l'entrega amb tot el codi de la P1. A continuació teniu les directrius a seguir.
* PR setmanals (amb revisió/explicació) i versió => Mínim un PR per persona/lliurament
    * Objectius assolits (justificació no assolits)
    * Errors detectats
    * Metodologia treball
    * Acceptació del Merge per part de l'altre membre de la parella
    * Tests realitzats
    * Excepcionalment es pot saltar un PR setmanal, però s'ha de justificar en el següent PR.
* En cas de no seguir aquesta metodologia, no s'acceptarà la pràctica i s'haurà de recuperar a reavaluació o passar-se a la convocatòria única. 

* A cada sessió el professor explicarà el progrès a realitzar durant la pràctica, a part de resoldre dubtes. 	
* No hi ha exàmen parcial


| Concepte                                                      | Tipus de nota | Pes |
|---------------------------------------------------------------|---------------|-----|
| Codi                                                       | Grupal   | 0.3 |
| Documentació (Javadoc i memòria) | Grupal | 0.2 |
| Proves i Sessió de Proves | Grupal | 0.3|
| Avaluació de GitHub (Pull Requests, Feedback, continuïtat...) | Individual    | 0.2 |

## P1 - Client / Servidor

La practica 1 consisteix en implementar un joc senzill amb arquitectura Client-Servidor fent servir Sockets amb Java. Establirem un protocol i els seus estats a seguir de manera que qualsevol grup podrà testejar el seu Client contra qualsevol Servidor d'un altre grup, i viceversa.

* Es programarà en Java. Es recomana usar una SDK recent, per exemple, la 18. És també recomanat l'ús de Linux.
* Es seguirà estrictament la [guia d'estil de JAVA](https://google.github.io/styleguide/javaguide.html) per la primera pràctica tan pel que fa al codi com a la documentació.
* La configuració dels projectes es farà en Maven. Podeu fer servir Visual Studio Code o el IntelliJ IDEA amb llicència d'estudiant de la UB que integra en l'IDE  eines per a [Github](https://www.jetbrains.com/help/idea/github.html) i per [reformatar codi segons una guia d'estil](https://medium.com/swlh/configuring-google-style-guide-for-java-for-intellij-c727af4ef248)
* La última sessió de cada pràctica serà de testing creuat.

**Avaluació de GitHub**

* El conjunt de **commits** d'un membre de la parella dins la seva branca s'hauran de revisar (**code review**) per l'altra membre abans de fer _merge_ a la branca principal. Es farà mitjançant[ **Pull Requests**,](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/proposing-changes-to-your-work-with-pull-requests) per tant no es podrà seguir desenvolupant fins que l'altre membre de la parella no acceptin els canvis en el codi proposats. Es poden fer tants PR com es vulgui, com mes _feedback_ entre la parella, millor.
* Per acceptar els canvis, el revisor ha de llegir-se el codi, comprovar que segueix els estàndards i provar els tests.  En cas de trobar coses per millorar o TESTS que faltin, ho ha d'explicar en el comentaris del  **Pull Request** i no acceptar els canvis fins que l'altra ho millori. En cas de que estigui tot correcte ha de comentar els canvis realitzats i les proves que ha fet.  


### Sessions

| Sessió |  Setmana  | Guió                                      |  Notes   |
|--------|-----------|-------------------------------------------|----------|
|    0   | 12 Febrer | [Guió Sessió 0](./Sessions/sessio_0.md)   | Aquesta setmana farem la creació de grups i revisarem el problema a resoldre i el codi que es facilita |
|    1   | 19 Febrer | [Guió Sessió 1](./Sessions/sessio_1.md)   | En aquesta sessió clarificarem els dubtes sobre la codificació dels missatges i el protocol en si. També començarem la codificació de la pràctica. |
|    2   | 26 Febrer | [Guió Sessió 1](./Sessions/sessio_2.md) | En aquesta sessió començarem a intercanviar missatges entre Client i Servidor, a implementant les primeres fases del joc. |

