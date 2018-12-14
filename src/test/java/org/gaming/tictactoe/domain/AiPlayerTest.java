package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Test;

import org.gaming.tictactoe.exceptions.InvalidMovementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.Human1;

public class AiPlayerTest {

  private AiPlayer robot;
  private Game game;

  @Before
  public void setUp() {
    robot = new AiPlayer("R");
    game = new SimpleGame(new GameConfiguration(3, new Player[]{Human1, robot}));
  }

  @Test
  public void shouldHaveDefaultNameRobot() {
    assertThat(robot.name(), is("Robot"));
  }

  @Test
  public void shouldConsiderTheFirstPositionWhenStartingTheGame() throws InvalidMovementException {
    Coordinates coordinates = robot.calculateMove(game.getGrid());
    assertThat(coordinates.getX(), is(0));
    assertThat(coordinates.getY(), is(0));
  }

  @Test
  public void shouldConsiderNextAvailablePositionAfterAMovement() throws InvalidMovementException {
    game.move(new Coordinates(0, 0), Human1);
    game.move(new Coordinates(0, 1), Human1);
    Coordinates coordinates = robot.calculateMove(game.getGrid());
    assertThat(coordinates.getX(), is(0));
    assertThat(coordinates.getY(), is(2));
  }
}