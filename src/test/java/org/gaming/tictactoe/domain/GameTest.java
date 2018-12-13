package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.gaming.tictactoe.exceptions.InvalidMovementException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private Game game;

  @Before
  public void setUp() {
    game = new SimpleGame(new GameConfiguration(3));
  }

  @Test
  public void shouldShowBlankGridAtTheBeginning() {
    String[][] grid = game.getGrid();
    String[][] expectedGrid = {{null, null, null}, {null, null, null}, {null, null, null}};
    assertThat(grid, is(equalTo(expectedGrid)));
  }

  @Test
  public void shouldHaveGridWithSizeConfigured() {
    game = new SimpleGame(new GameConfiguration(4));
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
  public void shouldThrowExceptionWhenMovingToPlayedSlot() throws InvalidMovementException {
    game.move(new Coordinates(2, 1), Player.Human1);
    expectedException.expect(InvalidMovementException.class);
    expectedException.expectMessage("Slot was already played");
    game.move(new Coordinates(2, 1), Player.Robot);
  }

  @Test
  public void shouldThrowExceptionWhenMovingToNonExistingSlot() throws InvalidMovementException {
    expectedException.expect(InvalidMovementException.class);
    expectedException.expectMessage("Coordinates numbers of slot should be between 0 and 3");
    game.move(new Coordinates(2, 3), Player.Robot);
  }
}