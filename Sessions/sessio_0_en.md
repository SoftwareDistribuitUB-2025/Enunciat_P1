# Session 0

In this session, we will assign the practice groups and familiarize ourselves with the available resources for completing Practice 1. In the following sessions, we will implement different parts of the practice.

## Objectives

- Create the practice groups
- Understand the problem we will be working on in the practice
- Understand the structure of the provided base code
- Execute the provided code
- Review the protocol and message format

## Creation of Practice Groups

The practice will be done in groups of two people. To streamline the session, it is requested that you pair up at the beginning of the session. The session instructor will assign a group identifier to each pair of students using the following format:

$GN$ where $G\in\{B,C,F\}$ corresponds to the practice group and $N\in(0, 15)$ is the group identifier assigned by the instructor, **using two digits**. The groups correspond to:

- **B**: Thursday from 7 PM to 9 PM (Xavier Baró)
- **C**: Thursday from 5 PM to 7 PM (Xavier Baró)
- **F**: Thursday from 7 PM to 9 PM (Kaisar Kushibar)

Thus, groups will have a format like **B01**, **B12**, **F05**, etc.

Once the group is assigned, **one and only one** member of the group must enter GitHub Classroom via the following link and create the group with the specified code. Once the group is created, the other member must enter the same link and **join the already created group**.

## Statement

This semester, we will implement the Battleship game. The main objective of Practice 1 is to implement the low-level communication protocol following a specification. You will learn how to send and receive information between a server and multiple clients, as well as how to manage sockets.

You can find the game description in the [practice statement](../Guies/battleship_en.md). It contains all the information and details about the protocol and different messages. In each session, we will guide you on which functionalities need to be implemented, as well as which parts are optional and which are mandatory.

## Base Code

In the GitHub Classroom repository, you will find a JAVA project consisting of three components:

- **Server**: An application that implements the server. When launched, it will open a port and listen for client requests.
- **Client**: An application that implements the client. When launched, it will connect to a server (machine + port) and start interacting with it.
- **ComUtils**: A library used by both the **Server** and the **Client** to facilitate communication.

The `Readme.md` file in the code explains how to start both the server and the client. To do this, you must have a version of the JAVA SDK **1.9 or higher** installed, as well as the project manager [Maven](https://maven.apache.org/).

### Compiling the Code

Maven is a JAVA project manager that automates the building of JAVA software projects. The configuration has already been provided in the `pom.xml` files, so you do not need prior Maven knowledge. Below are the necessary commands to compile and test the project:

```bash
mvn clean package
```

First, it removes previous builds, typically found in the `/target` directories. Once removed, it compiles the project (with all its modules) and packages it into a JAR file. Once the project is generated, we can test that everything works:

```bash
mvn test
```

This will run all [JUnit](https://junit.org/) unit tests defined in the code. You will see a summary of the test results for all project modules.

### Running the Code

As defined in the project, compiling and packaging the code generates a JAR file for the **Server** and another for the **Client**. First, we need to run the server using the following command:

```bash
java -jar target/Server-1.0-SNAPSHOT-jar-with-dependencies.jar -p 8080
```

Note that with the `-p 8080` parameter, we specify that we want the server to run and listen on **port** 8080 of the machine.

Once the server is running, we can connect to it with the **Client**. To do this, we execute:

```bash
java -jar target/Client-1.0-SNAPSHOT-jar-with-dependencies.jar -h localhost -p 8080
```

In this case, in addition to specifying the **port** we want to connect to, we also need to specify the **machine (host)** to which we are connecting. If we are running the client on the same machine as the server, we can use `localhost` to indicate this. If it is a different machine, we must specify it using its IP address or domain name.

