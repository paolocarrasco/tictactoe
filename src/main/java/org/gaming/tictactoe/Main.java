package org.gaming.tictactoe;

import org.gaming.tictactoe.domain.Game;
import org.gaming.tictactoe.domain.GameConfiguration;
import org.gaming.tictactoe.io.GameSetup;

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

    new GameFlow(new Game(configuration), System.out, System.in)
        .start();
  }
}
