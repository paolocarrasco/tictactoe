package org.gaming.tictactoe.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.Human1;
import static org.gaming.tictactoe.domain.PlayerContainer.Human2;

public class GameConfigurationTest {
  private static final int DEFAULT_SIZE_VALUE = 3;
  private Player[] players;

  @Before
  public void setUp() {
    players = new Player[]{Human1, Human2};
  }

  @Test
  public void shouldUseGivenValueIfSizeIsValue() {
    GameConfiguration gameConfiguration = new GameConfiguration(5, players);
    assertThat(gameConfiguration.getSize(), is(5));
  }

  @Test
  public void shouldUseDefaultValueIfSizeIsLessThanMinimumValue() {
    GameConfiguration gameConfiguration = new GameConfiguration(2, players);
    assertThat(gameConfiguration.getSize(), is(DEFAULT_SIZE_VALUE));
  }

  @Test
  public void shouldUseDefaultValueIfSizeIsGreaterThanMaximumValue() {
    GameConfiguration gameConfiguration = new GameConfiguration(11, players);
    assertThat(gameConfiguration.getSize(), is(DEFAULT_SIZE_VALUE));
  }

  @Test
  public void shouldUsePlayersSetInConstructor() {
    GameConfiguration gameConfiguration = new GameConfiguration(4, players);
    assertThat(gameConfiguration.getPlayers(), is(players));
  }
}