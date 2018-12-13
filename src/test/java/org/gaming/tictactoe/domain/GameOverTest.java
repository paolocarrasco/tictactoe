package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Test;

import org.gaming.tictactoe.exceptions.NotValidMovementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameOverTest {
  private Game game;

  @Before
  public void setUp() {
    game = new SimpleGame(new GameConfiguration(3));
  }

  @Test
  public void shouldReturnFalseIsOverIfGameHasJustStarted() {
    assertThat(game.isOver(), is(false));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInARow() throws NotValidMovementException {
    game.move(new Coordinates(1, 0), Player.Human1);
    game.move(new Coordinates(1, 1), Player.Human1);
    game.move(new Coordinates(1, 2), Player.Human1);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInAColumn() throws NotValidMovementException {
    game.move(new Coordinates(1, 1), Player.Human2);
    game.move(new Coordinates(2, 1), Player.Human2);
    game.move(new Coordinates(0, 1), Player.Human2);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInForwardDiagonal() throws
      NotValidMovementException {
    game.move(new Coordinates(0, 0), Player.Human2);
    game.move(new Coordinates(1, 1), Player.Human2);
    game.move(new Coordinates(2, 2), Player.Human2);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInBackDiagonal() throws
      NotValidMovementException {
    game.move(new Coordinates(0, 2), Player.Robot);
    game.move(new Coordinates(1, 1), Player.Robot);
    game.move(new Coordinates(2, 0), Player.Robot);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameIsDraw() throws NotValidMovementException {
    // x o c
    // x c x
    // o c o
    game.move(new Coordinates(0, 0), Player.Human2);
    game.move(new Coordinates(0, 1), Player.Human1);
    game.move(new Coordinates(0, 2), Player.Robot);
    game.move(new Coordinates(1, 0), Player.Human2);
    game.move(new Coordinates(1, 1), Player.Robot);
    game.move(new Coordinates(1, 2), Player.Human2);
    game.move(new Coordinates(2, 0), Player.Human1);
    game.move(new Coordinates(2, 1), Player.Robot);
    game.move(new Coordinates(2, 2), Player.Human1);

    assertThat(game.isOver(), is(true));
  }

  @Test
  public void shouldReturnWinnerWhenColumnIsWon() throws NotValidMovementException {
    game.move(new Coordinates(1, 2), Player.Human2);
    game.move(new Coordinates(2, 2), Player.Human2);
    game.move(new Coordinates(0, 2), Player.Human2);

    game.isOver();
    assertThat(game.getWinner(), is(Player.Human2));
  }

  @Test
  public void shouldReturnWinnerWhenRowIsWon() throws NotValidMovementException {
    game.move(new Coordinates(0, 2), Player.Human1);
    game.move(new Coordinates(0, 0), Player.Human1);
    game.move(new Coordinates(0, 1), Player.Human1);

    game.isOver();
    assertThat(game.getWinner(), is(Player.Human1));
  }

  @Test
  public void shouldReturnWinnerWhenForwardDiagonalIsWon() throws NotValidMovementException {
    game.move(new Coordinates(0, 2), Player.Robot);
    game.move(new Coordinates(1, 1), Player.Robot);
    game.move(new Coordinates(2, 0), Player.Robot);

    game.isOver();
    assertThat(game.getWinner(), is(Player.Robot));
  }

  @Test
  public void shouldReturnWinnerWhenBackDiagonalIsWon() throws NotValidMovementException {
    game.move(new Coordinates(0, 0), Player.Human2);
    game.move(new Coordinates(1, 1), Player.Human2);
    game.move(new Coordinates(2, 2), Player.Human2);

    game.isOver();
    assertThat(game.getWinner(), is(Player.Human2));
  }

  @Test
  public void shouldReturnUnknownWhenThereIsDraw() throws NotValidMovementException {
    game.move(new Coordinates(0, 0), Player.Human2);
    game.move(new Coordinates(0, 1), Player.Human1);
    game.move(new Coordinates(0, 2), Player.Robot);
    game.move(new Coordinates(1, 0), Player.Human2);
    game.move(new Coordinates(1, 1), Player.Robot);
    game.move(new Coordinates(1, 2), Player.Human2);
    game.move(new Coordinates(2, 0), Player.Human1);
    game.move(new Coordinates(2, 1), Player.Robot);
    game.move(new Coordinates(2, 2), Player.Human1);

    game.isOver();
    assertThat(game.getWinner(), is(Player.NoOne));
  }
}
