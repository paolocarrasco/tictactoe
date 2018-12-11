package org.gaming.tictactoe.io;

import org.gaming.tictactoe.domain.GameConfiguration;
import org.gaming.tictactoe.io.GameSetup;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.gaming.tictactoe.domain.GameConfiguration.DEFAULT_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameSetupTest {

  private static final int CONFIGURED_VALUE_IN_FILE = 4;

  private GameSetup gameSetup;

  @Before
  public void setUp() {
    gameSetup = new GameSetup();
  }

  @Test
  public void shouldReturnTheSizeOfTheGameWhenItReadsFromDefaultFile() {
    GameConfiguration configuration = gameSetup.read();
    assertThat(configuration.getSize(), is(CONFIGURED_VALUE_IN_FILE));
  }

  @Test
  public void shouldReturnASizeOfTheGameWhenItUsesADifferentConfigurationFile() {
    URL configurationFile = getClass().getClassLoader().getResource("another-game.properties");
    String fileLocation = configurationFile.getFile();
    GameConfiguration configuration = gameSetup.read(fileLocation);
    assertThat(configuration.getSize(), is(6));
  }

  @Test
  public void shouldReturnADefaultSizeOfTheGameWhenItDoesNotFindTheConfiguredFile() {
    GameConfiguration configuration = gameSetup.read("unknown-game.properties");
    assertThat(configuration.getSize(), is(DEFAULT_SIZE));
  }

  @Test
  public void shouldUseDefaultValueIfSizeIsNotANumber() {
    URL configurationFile = getClass().getClassLoader().getResource("invalid-game.properties");
    String fileLocation = configurationFile.getFile();
    GameConfiguration configuration = gameSetup.read(fileLocation);
    assertThat(configuration.getSize(), is(DEFAULT_SIZE));
  }
}