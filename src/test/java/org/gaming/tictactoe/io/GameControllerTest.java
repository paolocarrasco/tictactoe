package org.gaming.tictactoe.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.gaming.tictactoe.domain.*;
import org.gaming.tictactoe.helpers.MockablePlayerContainer;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.*;

public class GameControllerTest {

  private GameController gameController;
  private Game game;
  private ByteArrayOutputStream outputStream;
  private MockablePlayerContainer mockablePlayerContainer;

  @Before
  public void setUp() {
    mockablePlayerContainer = new MockablePlayerContainer();
    game = new SimpleGame(new GameConfiguration(3, new Player[]{Human1, Human2})) {
      @Override
      public Player getWinner() {
        return mockablePlayerContainer.getWinner();
      }

      @Override
      public Player nextPlayer() {
        return mockablePlayerContainer.getPlayer();
      }
    };
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    gameController = new GameController(game, printStream, System.in) {
      @Override
      String readInput() {
        return "1,2";
      }
    };
  }

  @Test
  public void shouldPassTheCoordinatesEnteredToTheGameWhenEnteringNextMoveOfHuman() {
    gameController.enterNextMove();
    String[][] grid = game.getGrid();
    assertThat(grid[1][2], is(not(nullValue())));
  }

  @Test
  public void shouldAskForCoordinatesWhenEnteringNextMoveOfRobot() {
    Player mockedRobot = new DumbAiPlayer("C") {
      @Override
      public Coordinates calculateMove(String[][] grid) {
        return new Coordinates(2,2);
      }
    };
    mockablePlayerContainer.setPlayer(mockedRobot);
    gameController.enterNextMove();
    String[][] grid = game.getGrid();
    assertThat(grid[2][2], is(not(nullValue())));
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
    mockablePlayerContainer.setWinner(Robot);
    gameController.printWinner();
    outputStream.flush();
    String output = new String(outputStream.toByteArray());
    assertThat(output, is("Game is over! The winner was Robot.\n"));
  }

}