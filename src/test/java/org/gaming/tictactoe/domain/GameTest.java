package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.gaming.tictactoe.exceptions.InvalidMovementException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.*;

public class GameTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private Game game;
  private Player[] players;

  @Before
  public void setUp() {
    players = new Player[]{Human1, Human2, Robot};
    game = new SimpleGame(new GameConfiguration(3, players));
  }

  @Test
  public void shouldShowBlankGridAtTheBeginning() {
    String[][] grid = game.getGrid();
    String[][] expectedGrid = {{null, null, null}, {null, null, null}, {null, null, null}};
    assertThat(grid, is(equalTo(expectedGrid)));
  }

  @Test
  public void shouldUseGridPassIfIsPassedAsParameterInConstructor() {
    String[][] grid = new String[][]{{"X", "C", "X"}, {" ", " ", " "}, {"X", "C", "X"}};
    GameConfiguration configuration = new GameConfiguration(3, new Player[]{Human1, Robot});
    Game game = new SimpleGame(configuration, grid, 0, 0);
    assertThat(game.getGrid(), is(equalTo(grid)));
  }

  @Test
  public void shouldHaveGridWithSizeConfigured() {
    game = new SimpleGame(new GameConfiguration(4, players));
    String[][] grid = game.getGrid();
    String[][] expectedGrid = {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
    };
    assertThat(grid, is(equalTo(expectedGrid)));
    assertThat(game.getSize(), is(4));
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
  public void shouldPutCoordinatesInGridWhenMovingToNotPlayedSlot() throws
      InvalidMovementException {
    Coordinates coordinates = new Coordinates(2, 1);
    game.move(coordinates, Human1);
    String[][] expectedGrid = {
        {null, null, null},
        {null, null, null},
        {null, Human1.getSymbol(), null}
    };
    String[][] grid = game.getGrid();
    assertThat(grid, is(expectedGrid));
  }

  @Test
  public void shouldThrowExceptionWhenMovingToPlayedSlot() throws InvalidMovementException {
    game.move(new Coordinates(2, 1), Human1);
    expectedException.expect(InvalidMovementException.class);
    expectedException.expectMessage("Slot was already played");
    game.move(new Coordinates(2, 1), Robot);
  }

  @Test
  public void shouldThrowExceptionWhenMovingToNonExistingSlot() throws InvalidMovementException {
    expectedException.expect(InvalidMovementException.class);
    expectedException.expectMessage("Coordinates numbers of slot should be between 0 and 3");
    game.move(new Coordinates(2, 3), Robot);
  }
}