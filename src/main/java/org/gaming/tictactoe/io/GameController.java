package org.gaming.tictactoe.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.gaming.tictactoe.domain.Coordinates;
import org.gaming.tictactoe.domain.Game;
import org.gaming.tictactoe.domain.Player;

import static java.util.Optional.ofNullable;

/**
 * It allows the interaction with the game: entering the movement, see the status.
 */
public class GameController {

  private final Game game;
  private final PrintStream out;
  private final Scanner scanner;

  public GameController(Game game, PrintStream out, InputStream in) {
    this.game = game;
    this.out = out;
    scanner = new Scanner(in);
  }

  public void enterNextMove() {
    Player player = this.game.nextPlayer();
    Coordinates coordinates = requestNextMove();
    this.game.move(coordinates, player);
  }

  Coordinates requestNextMove() {
    out.printf(
        "Please enter coordinates separated by comma.They should be less than %d%n",
        game.getSize()
    );
    String input = scanner.next();
    String[] coordinates = input.split(",");

    return new Coordinates(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
  }

  public void printSnapshot() {
    String[][] grid = this.game.getGrid();

    for (int i = 0; i < game.getSize(); i++) {
      out.print("|");

      for (int j = 0; j < game.getSize(); j++) {
        String symbol = grid[i][j];
        out.print(ofNullable(symbol).orElse(" ") + "|");
      }
      out.println();
    }
  }

  public void printWinner() {
    out.print("Game is over! ");
    Player winner = this.game.getWinner();

    if (winner == Player.Unknown) {
      out.println("It was a draw...");
    } else {
      out.printf("The winner was %s.%n", winner.name());
    }
  }

  @Override
  protected void finalize() {
    scanner.close();
  }

}