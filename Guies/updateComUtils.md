# Modificacions classe ComUtils

En aquest document us guiem en els canvis que us facilitaran implementar una classe derivada de **ComUtils**. Bàsicament consten en permetre l'accés als atributs de la classe per part de les classes derivades, i a tenir un **constructor de còpia** per facilitar la creació d'instàncies de la classe derivada a partir d'instàncies de **ComUtils**.

## Visibilitat atributs
La classe **ComUtils** té dos atributs que permeten accedir al stream de sortida on escriurem i al stream d'entrada del que llegirem. Aquests atributs estan definits com a **private** per evitar que es pugui accedir directament, però això obliga a re-implementar molts mètodes en les classes derivades. Per evitar aquest problema, us proposem canviar la visibilitat a **protected** que segueix evitant el seu ús directe, però si que permet a les classes derivades accedir-hi i aprofitar els mètodes disponibles.

Es tracta de canviar el codi on es defineixen els dos atributs:

```java
/**
 * Utility class for communication, providing methods to read and write data in different formats.
 */
public class ComUtils {

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    ...

}
```

Per aquest altre:

```java
/**
 * Utility class for communication, providing methods to read and write data in different formats.
 */
public class ComUtils {

    protected final DataInputStream dataInputStream;
    protected final DataOutputStream dataOutputStream;

    ...

}
```

## Construtor de còpia
Per poder construir una nova instància **ComUtils** (o derivada) a partir d'una altra instància **ComUtils** ja inicialitzada, us facilitem el constructor de còpia que podeu afegir directament a la classe **ComUtils**:

```java
/**
 * Copy contructor for ComUtils.
 * 
 * @param obj  Input ComUtils obj.     
 */
public ComUtils(ComUtils obj){
    dataInputStream = obj.dataInputStream;
    dataOutputStream = obj.dataOutputStream;
}
```
