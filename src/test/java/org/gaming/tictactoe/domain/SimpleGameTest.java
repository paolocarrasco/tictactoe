package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import org.gaming.tictactoe.exceptions.InvalidMovementException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.*;

public class SimpleGameTest {
  private Game game;

  @Before
  public void setUp() {
    GameConfiguration configuration = new GameConfiguration(
        3,
        new Player[]{Human1, Human2, Robot}
    );
    game = new SimpleGame(configuration);
  }

  @Test
  public void shouldReturnFalseIsOverIfGameHasJustStarted() {
    assertThat(game.isOver(), is(false));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInARow() throws InvalidMovementException {
    game.move(new Coordinates(1, 0), Human1);
    game.move(new Coordinates(1, 1), Human1);
    game.move(new Coordinates(1, 2), Human1);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInAColumn() throws InvalidMovementException {
    game.move(new Coordinates(1, 1), Human2);
    game.move(new Coordinates(2, 1), Human2);
    game.move(new Coordinates(0, 1), Human2);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInForwardDiagonal() throws
      InvalidMovementException {
    game.move(new Coordinates(0, 0), Human2);
    game.move(new Coordinates(1, 1), Human2);
    game.move(new Coordinates(2, 2), Human2);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInBackDiagonal() throws
      InvalidMovementException {
    game.move(new Coordinates(0, 2), Robot);
    game.move(new Coordinates(1, 1), Robot);
    game.move(new Coordinates(2, 0), Robot);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameIsDraw() throws InvalidMovementException {
    // x o c
    // x c x
    // o c o
    game.move(new Coordinates(0, 0), Human2);
    game.move(new Coordinates(0, 1), Human1);
    game.move(new Coordinates(0, 2), Robot);
    game.move(new Coordinates(1, 0), Human2);
    game.move(new Coordinates(1, 1), Robot);
    game.move(new Coordinates(1, 2), Human2);
    game.move(new Coordinates(2, 0), Human1);
    game.move(new Coordinates(2, 1), Robot);
    game.move(new Coordinates(2, 2), Human1);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnWinnerWhenColumnIsWon() throws InvalidMovementException {
    game.move(new Coordinates(1, 2), Human2);
    game.move(new Coordinates(2, 2), Human2);
    game.move(new Coordinates(0, 2), Human2);

    game.isOver();
    assertThat(game.getWinner(), is(Human2));
  }

  @Test
  public void shouldReturnWinnerWhenRowIsWon() throws InvalidMovementException {
    game.move(new Coordinates(0, 2), Human1);
    game.move(new Coordinates(0, 0), Human1);
    game.move(new Coordinates(0, 1), Human1);

    game.isOver();
    assertThat(game.getWinner(), is(Human1));
  }

  @Test
  public void shouldReturnWinnerWhenForwardDiagonalIsWon() throws InvalidMovementException {
    game.move(new Coordinates(0, 2), Robot);
    game.move(new Coordinates(1, 1), Robot);
    game.move(new Coordinates(2, 0), Robot);

    game.isOver();
    assertThat(game.getWinner(), is(Robot));
  }

  @Test
  public void shouldReturnWinnerWhenBackDiagonalIsWon() throws InvalidMovementException {
    game.move(new Coordinates(0, 0), Human2);
    game.move(new Coordinates(1, 1), Human2);
    game.move(new Coordinates(2, 2), Human2);

    game.isOver();
    assertThat(game.getWinner(), is(Human2));
  }

  @Test
  public void shouldReturnAllCoordinatesWhenStartingGameOnGetAvailableMoves() {
    List<Coordinates> availableMoves = game.getAvailableMoves();
    assertThat(availableMoves.size(), is(9));
  }

  @Test
  public void shouldReturnUnknownWhenThereIsDraw() throws InvalidMovementException {
    game.move(new Coordinates(0, 0), Human2);
    game.move(new Coordinates(0, 1), Human1);
    game.move(new Coordinates(0, 2), Robot);
    game.move(new Coordinates(1, 0), Human2);
    game.move(new Coordinates(1, 1), Robot);
    game.move(new Coordinates(1, 2), Human2);
    game.move(new Coordinates(2, 0), Human1);
    game.move(new Coordinates(2, 1), Robot);
    game.move(new Coordinates(2, 2), Human1);

    game.isOver();
    assertThat(game.getWinner(), is(NoOne));
  }

  @Test
  public void shouldReturnOnlyAMoveWhenThereIsOnlyTheLastMoveOnGetAvailableMoves() {
    String[][] grid = new String[][]{{"C", "X", null}, {"X", "C", "X"}, {"X", "C", "C"}};
    GameConfiguration configuration = new GameConfiguration(3, new Player[]{Human1, Robot});
    SimpleGame endingGame = new SimpleGame(configuration, grid, 0, 0);

    List<Coordinates> availableMoves = endingGame.getAvailableMoves();

    Coordinates lastMove = new Coordinates(0, 2);
    assertThat(availableMoves.get(0).getX(), is(lastMove.getX()));
    assertThat(availableMoves.get(0).getY(), is(lastMove.getY()));
  }

  @Test
  public void shouldSendTheCurrentStateToTheCopy() throws InvalidMovementException {
    GameConfiguration configuration = new GameConfiguration(4, new Player[]{Human1, Human2});
    Game baseGame = new SimpleGame(configuration);
    baseGame.move(new Coordinates(0, 0), Human2);
    baseGame.move(new Coordinates(0, 1), Human1);

    Game copiedGame = baseGame.copy();

    assertThat(copiedGame.getSize(), is(4));
    assertThat(copiedGame.turn, is(baseGame.turn));
    assertThat(copiedGame.players, is(baseGame.players));
    assertThat(copiedGame.grid, is(not(sameInstance(baseGame.grid))));
    assertThat(copiedGame.grid[0][1], is(Human1.getSymbol()));
    assertThat(copiedGame.grid[0][0], is(Human2.getSymbol()));
  }

  @Test
  public void shouldNotAffectOriginalGameTheChangesMadeToCopiedGame() throws
      InvalidMovementException {
    GameConfiguration configuration = new GameConfiguration(4, new Player[]{Human1, Human2});
    Game baseGame = new SimpleGame(configuration);

    Game copiedGame = baseGame.copy();
    copiedGame.move(new Coordinates(1, 0), Human2);

    assertThat(copiedGame.grid[1][0], is(Human2.getSymbol()));
    assertThat(baseGame.grid[1][0], is(nullValue()));
  }
}
