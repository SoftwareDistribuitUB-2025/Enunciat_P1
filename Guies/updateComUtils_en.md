# Modifications to the ComUtils Class

This document guides you through the changes that will facilitate implementing a derived class from **ComUtils**. These changes mainly consist of allowing access to the class attributes from derived classes and having a **copy constructor** to facilitate the creation of instances of the derived class from **ComUtils** instances.

## Attribute Visibility

The **ComUtils** class has two attributes that allow access to the output stream where we write and the input stream from which we read. These attributes are defined as **private** to prevent direct access, but this requires re-implementing many methods in derived classes.  

To avoid this problem, we propose changing their visibility to **protected**, which still prevents direct use but allows derived classes to access them and take advantage of the available methods.

The modification consists of changing the code where these two attributes are defined:

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

By this other code:

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

## Copy Constructor  
To be able to create a new **ComUtils** (or derived) instance from an already initialized **ComUtils** instance, we provide the copy constructor that you can directly add to the **ComUtils**

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
