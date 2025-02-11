# Sessió 0

En aquesta sessió farem l'assignació dels grups de pràctiques i ens familiaritzarem amb els recursos disponibles de cara a la realització de la pràctica 1. En les següents sessions anirem implementant diferents parts de la pràctica.


## Objectius

- Crear els grups de pràctiques
- Entendre el problema que treballarem a la pràctica
- Entendre l'estructura del codi base proporcionat
- Executar el codi proporcionat
- Revisar el protocol i el format dels missatges


## Creació dels grups de pràctiques

La pràctica es farà en grups de dues persones. Per agilitzar la sessió, es demana que a l'inici de la sessió ja us poseu en parelles. El professor de la sessió assignarà un Identificador de grup a cada parella d'estudiants, seguint el format:

$GN$ on $G\in\{B,C,F\}$ correspon al grup de pràctiques i $N\in(0, 15)$ serà el identificador de grup assignat pel professor, **utilitzant dos dígits**. Els grups corresponen a:

- **B**: Dijous de 19h a 21h  (Xavier Baró)
- **C**: Dijous de 17h a 19h  (Xavier Baró)
- **F**: Dijous de 19h a 21h  (Kaisar Kushibar)

Per tant, els grups tindran un format tipus   **B01**, **B12**, **F05**, etc...

Un cop assignat el grup, **un i només un** dels membres del grup haurà d'entrar a GitHub Classroom mitjançant l'enllaç següent, i crear el grup amb el codi especificat. Un cop fet el grup, l'altre membre haurà d'entrar al mateix enllaç i **afegir-se al grup ja creat**.

[GitHub Classroom]()

## Enunciat

Aquest semestre implementarem el joc d'enfonsar la flota (Battleship). El principal objectiu de la pràctica 1 serà la implementació del protocol de comunicació a baix nivell, seguint una especificació. Es veurà com enviar i rebre informació entre un Servidor i varis clients, aixó com la gestió de sockets.

Teniu la descripció del joc a l'[enunciat de la pràctica](../Guies/battleship.md). Hi trobareu tota l'informació i el detall del protocol i els diferents missatges. A cada sessió us anirem guiant sobre quines funcionalitats caldrà implementar, i quines parts seran opcionals i quines no.


## Codi base

En el repositori de GitHub Classroom trobareu un projecte en JAVA, en el que es diferencien tres components:

- **Servidor**: Aplicació que implementa el servidor. Al arrancar obrirà un port i es quedarà escoltant per rebre peticions de clients.
- **Client**: Aplicació que implementa el client. Al arrancar es connectarà a un servidor (màquina + port), i iniciarà la interacció amb aquest.
- **ComUtils**: Es tracta d'una llibreria utilitzada tant pel **Servidor** com pel **Client** per tal de fer la comunicació de la informació.

En el `Readme.md` del codi s'explica com arrancar tant el servidor com el client. Per fer-ho, caldrà tenir instal·lada una versió de JAVA SDK superior o igual a la 1.9 i el gestor de projectes [maven](https://maven.apache.org/) també instal·lat.

### Compilar el codi

Maven és un gestor de projectes JAVA, que permet automatitzar la construcció de projectes de software en JAVA. La part de configuració ja la teniu donada en els fitxers `pom.xml`, per tant no cal que tingueu coneixements de Maven. A continuació us expliquem les comandes necessàries per compilar i provar el projecte:

```bash 
mvn clean package
```
Primer, elimina compilacions anteriors, generalment continguts en les carpetes `/target`. Un cop eliminat, fa la compilació del projecte (amb tots els seus mòduls) i l'empaqueta en format JAR. Un cop generat el projecte, podem provar que tot funciona:

```bash 
mvn test
```

Executarà totes les proves unitàries [JUnit](https://junit.org/) definides en el codi. Veureu un resum de les proves per a tots els mòduls del projecte.

### Executar el codi

Tal com està definit el projecte, al compilar i empaquetar el codi es genera un JAR per al **Servidor** i un JAR per al **Client**. Primer haurem d'executar el servidor amb la comanda:

```bash 
java -jar target/Server-1.0-SNAPSHOT-jar-with-dependencies.jar -p 8080
```

Fixeu-vos que amb el paràmetre `-p 8080` li indiquem que volem que el servidor s'executi i escolti en el **port** 8080 de la màquina. 

Quan el servidor ja s'està executant, ja podem connectar-hi amb el **client**. Per fer-ho, executarem:

```bash 
java -jar target/Client-1.0-SNAPSHOT-jar-with-dependencies.jar -h localhost -p 8080
```

En aquest cas, a més a més del **port** al que ens volem connectar, també haurem d'indicar **la màquina (host)** a la que ens connectem. Si estem executant el client en la mateixa màquina que el servidor, podem utilitzar `localhost` per indicar-ho. En cas que sigui una altra màquina, caldrà indicar-la via la seva adreça IP o nom de domini.


