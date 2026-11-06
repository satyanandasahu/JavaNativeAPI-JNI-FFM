# Java Native Interface (JNI) Windows MessageBox Demo

A demonstration project showing how to integrate Java 26 with native Windows APIs (`MessageBoxA`) using JNI, MSVC (`cl.exe`), and Maven automation.

## Prerequisites

- **Java 26** (with `%JAVA_HOME%` configured in environment variables)
- **Apache Maven** (3.8+)
- **Eclipse IDE** 
- **C++ Development Libraries Installed**

## Project Structure

```text
JNIProject/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── satya/
│       │               └── jni/
│       │                   └── main/
│       │                       └── JniMessageBoxDemo.java
│       └── native/
│           └── jnimessagebox.c
└── pom.xml
```
## File Needed
- JniMessageBoxDemo.java
- jnimessagebox.c
- com_satya_jni_main_JniMessageBoxDemo.h

## Build and Run
You can build and execute the entire pipeline (Java compilation, JNI header generation, C compilation via MSVC, and Java execution) using a standard command prompt, You can compile and execute the project with standard Maven goals:
```diff
mvn clean test-compile exec:java@run-java-app
```
