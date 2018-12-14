Tic Tac Toe
===========

Simple project that allows a special Tic Tac Toe game from the command line.
The size of the grid can be configured (between 3x3 and 10x10).
It can be played by three people, one of them is an AI robot. 
Each of them can use the default symbols although they can be customized.
The first turn is random!

![How the game looks](https://media.giphy.com/media/7JBkg3fKumeX0xbBWU/giphy.gif)

Requirements
------------

The only requirement for this application is JDK 8.

Execution
---------

How to run it? Easy. Execute this instruction in the root of the project.

In *nix/bash environments:

```console
./gradlew execute
```

In Windows:

```console
gradlew execute
```

Configuration
-------------

The game can be customized.
For that you could use the `game.properties` file located inside
the `src/main/resources` folder in this project.
You can configure the following properties (both are optional):
- **size**: *number* - It will determine the size of the game grid.
Since it is a square, we just need an integer number.
- **symbols**: *letter,letter,letter* - They are the symbols that
will be used by the players.

Example of configuration:

```properties
size=5
symbols=A,B,C
```

> You can use a configuration file outside the project.
> For that you need to pass its absolute path as argument of the
> application when starting.

Testing
-------

In order to run the tests inside the application:

```console
./gradlew test
```
