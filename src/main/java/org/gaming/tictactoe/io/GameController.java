package org.gaming.tictactoe.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.gaming.tictactoe.domain.AiPlayer;
import org.gaming.tictactoe.domain.Coordinates;
import org.gaming.tictactoe.domain.Game;
import org.gaming.tictactoe.domain.Player;
import org.gaming.tictactoe.exceptions.InvalidMovementException;

import static java.util.Optional.ofNullable;

import static org.gaming.tictactoe.domain.PlayerContainer.NoOne;

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

    do {
      try {
        Coordinates coordinates = requestNextMoveFor(player);
        this.game.move(coordinates, player);
        break;
      } catch (InvalidMovementException e) {
        showInvalidMovement();
      }
    } while (true);
  }

  private void showInvalidMovement() {
    out.println("Invalid movement! Try again.");
  }

  private Coordinates requestNextMoveFor(Player player) {
    if (player instanceof AiPlayer) {
      Coordinates coordinates = ((AiPlayer) player).calculateMove(game.getGrid());
      out.printf(
          "%s turn: Wisely admire its movement: (%s).%n"
              + "Reflect how lucky you are to play against him%n",
          player.name(),
          coordinates.toString(),
          game.getSize()
      );
      return coordinates;
    }
    out.printf(
        "%s turn: Please enter coordinates separated by comma.\n"
            + "They should be zero-based and less than %d%n",
        player.name(),
        game.getSize()
    );
    String input = readInput();
    String[] coordinates = input.split(",");

    return new Coordinates(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
  }

  String readInput() {
    return scanner.next();
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

    if (winner == NoOne) {
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
