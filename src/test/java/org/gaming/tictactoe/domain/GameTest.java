package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  private Game game;

  @Before
  public void setUp() {
    game = new Game(new GameConfiguration(3));
  }

  @Test
  public void shouldShowBlankGridAtTheBeginning() {
    String[][] grid = game.getGrid();
    String[][] expectedGrid = {{null, null, null}, {null, null, null}, {null, null, null}};
    assertThat(grid, is(equalTo(expectedGrid)));
  }

  @Test
  public void shouldHaveGridWithSizeConfigured() {
    game = new Game(new GameConfiguration(4));
    String[][] grid = game.getGrid();
    String[][] expectedGrid = {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
    };
    assertThat(grid, is(equalTo(expectedGrid)));
  }

  @Test
  public void shouldReturnADifferentPlayerEachTimeNextPlayerIsInvoked() {
    Player playerA = game.nextPlayer();
    Player playerB = game.nextPlayer();
    Player playerC = game.nextPlayer();
    assertThat(playerA, is(not(playerB)));
    assertThat(playerA, is(not(playerC)));
    assertThat(playerB, is(not(playerC)));
  }

  @Test
  public void shouldRotatePlayersWhenNextPlayerPassedThroughAllPlayers() {
    Player playerA = game.nextPlayer();
    game.nextPlayer();
    game.nextPlayer();
    Player rotatedPlayer = game.nextPlayer();
    assertThat(playerA, is(rotatedPlayer));
  }

  @Test
  public void shouldPutCoordinatesInGridWhenMoving() {
    Coordinates coordinates = new Coordinates(2, 1);
    game.move(coordinates, Player.Human1);
    String[][] expectedGrid = {
        {null, null, null},
        {null, null, null},
        {null, Player.Human1.getSymbol(), null}
    };
    String[][] grid = game.getGrid();
    assertThat(grid, is(expectedGrid));
  }

  @Test
  public void shouldReturnFalseIsOverIfGameHasJustStarted() {
    assertThat(game.isOver(), is(false));
  }

  @Test
  public void shouldReturnTrueIsOverIfGameHasWinnerInARow() {
    game.move(new Coordinates(1, 0), Player.Human1);
    game.move(new Coordinates(1, 1), Player.Human1);
    game.move(new Coordinates(1, 2), Player.Human1);

    assertThat(game.isOver(), is(true));
  }

}