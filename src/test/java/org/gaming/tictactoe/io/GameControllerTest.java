package org.gaming.tictactoe.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.gaming.tictactoe.domain.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameControllerTest {

  private GameController gameController;
  private Game game;
  private ByteArrayOutputStream outputStream;
  private MockedWinnerContainer mockedWinnerContainer;

  @Before
  public void setUp() {
    mockedWinnerContainer = new MockedWinnerContainer();
    game = new SimpleGame(new GameConfiguration(3)) {
      @Override
      public Player getWinner() {
        return mockedWinnerContainer.getWinner();
      }
    };
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    gameController = new GameController(game, printStream, System.in) {
      @Override
      Coordinates requestNextMoveFor(Player player) {
        return new Coordinates(1, 2);
      }
    };
  }

  @Test
  public void shouldPassTheCoordinatesEnteredToTheGameWhenEnteringNextMove() {
    gameController.enterNextMove();
    String[][] grid = game.getGrid();
    assertThat(grid[1][2], is(not(nullValue())));
  }

  @Test
  public void shouldShowCurrentStateOfGridWhenPrintingSnapshot() throws IOException {
    gameController.printSnapshot();
    outputStream.flush();
    String output = new String(outputStream.toByteArray());
    assertThat(output, is(
        "| | | |\n"
            + "| | | |\n"
            + "| | | |\n"
    ));
  }

  @Test
  public void shouldShowDrawWhenPrintingWinnerAndThereIsNoWinner() throws IOException {
    gameController.printWinner();
    outputStream.flush();
    String output = new String(outputStream.toByteArray());
    assertThat(output, is("Game is over! It was a draw...\n"));
  }

  @Test
  public void shouldShowPlayerNameWhenPrintingWinnerAndThereIsWinner() throws IOException {
    mockedWinnerContainer.setWinner(Player.Robot);
    gameController.printWinner();
    outputStream.flush();
    String output = new String(outputStream.toByteArray());
    assertThat(output, is("Game is over! The winner was Robot.\n"));
  }

}