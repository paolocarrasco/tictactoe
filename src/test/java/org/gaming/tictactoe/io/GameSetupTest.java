package org.gaming.tictactoe.io;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.gaming.tictactoe.domain.GameConfiguration;
import org.gaming.tictactoe.domain.Player;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.GameConfiguration.DEFAULT_SIZE;

@SuppressWarnings("ConstantConditions")
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

  @Test
  public void shouldUseDefaultValueIfPropertyForSizeIsNotSet() {
    URL configurationFile = getClass().getClassLoader().getResource("empty-game.properties");
    String fileLocation = configurationFile.getFile();
    GameConfiguration configuration = gameSetup.read(fileLocation);
    assertThat(configuration.getSize(), is(DEFAULT_SIZE));
  }

  @Test
  public void shouldUseDefaultSymbolsForPlayersIfTheyAreNotSet() {
    URL configurationFile = getClass().getClassLoader().getResource("empty-game.properties");
    String fileLocation = configurationFile.getFile();
    GameConfiguration configuration = gameSetup.read(fileLocation);
    List<String> symbols = Arrays.stream(configuration.getPlayers())
        .map(Player::getSymbol)
        .collect(Collectors.toList());

    assertThat(symbols, hasItems("C", "X", "O"));
  }
}