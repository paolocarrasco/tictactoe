package org.gaming.tictactoe.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameConfigurationTest {
  private static final int DEFAULT_SIZE_VALUE = 3;

  @Test
  public void shouldUseGivenValueIfSizeIsValue() {
    GameConfiguration gameConfiguration = new GameConfiguration(5);
    assertThat(gameConfiguration.getSize(), is(5));
  }

  @Test
  public void shouldUseDefaultValueIfSizeIsLessThanMinimumValue() {
    GameConfiguration gameConfiguration = new GameConfiguration(2);
    assertThat(gameConfiguration.getSize(), is(DEFAULT_SIZE_VALUE));
  }

  @Test
  public void shouldUseDefaultValueIfSizeIsGreaterThanMaximumValue() {
    GameConfiguration gameConfiguration = new GameConfiguration(11);
    assertThat(gameConfiguration.getSize(), is(DEFAULT_SIZE_VALUE));
  }
}