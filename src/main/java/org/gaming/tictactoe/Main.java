package org.gaming.tictactoe;

import org.gaming.tictactoe.domain.GameConfiguration;

/**
 * Entry file to start the game.
 * Execute it with the filename as argument
 * or leave it alone to use the default
 * configuration file (resources/game.properties).
 */
public class Main {
  public static void main(String... args) {
    GameSetup gameSetup = new GameSetup();
    GameConfiguration configuration = args.length == 0
        ? gameSetup.read()
        : gameSetup.read(args[0]);

    System.out.printf("Welcome to the game with size %d", configuration.getSize());
  }
}
