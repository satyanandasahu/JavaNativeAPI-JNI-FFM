```markdown
# Foreign Function & Memory (FFM) API Windows MessageBox Demo

A pure Java implementation using Java's modern **Foreign Function & Memory (FFM) API** (JEP 454) to invoke native Windows DLL functions (`user32.dll` -> `MessageBoxA`) directly without writing C code, managing header generation, or invoking a C compiler.

## Prerequisites

- **Java 26** (with `%JAVA_HOME%` configured)
- **Apache Maven** (3.8+)
- **Windows OS** (required for `user32.dll`)

## Project Structure

```text
WindowsFfmDemo/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── satya/
│                   └── jni/
│                       └── main/
│                           └── WindowsFfmPopup.java
└── pom.xml

## Build and Run
Since FFM bypasses native compilation steps and compilers entirely, you can compile and execute the project with standard Maven goals: mvn clean compile exec:java