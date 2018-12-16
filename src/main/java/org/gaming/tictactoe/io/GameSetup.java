package org.gaming.tictactoe.io;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gaming.tictactoe.domain.GameConfiguration;
import org.gaming.tictactoe.domain.Player;

import static org.gaming.tictactoe.domain.GameConfiguration.DEFAULT_SIZE;
import static org.gaming.tictactoe.domain.PlayerContainer.*;

/**
 * It allows to setup the game by reading a configuration file.
 * By default in the resources folder in this application.
 */
public class GameSetup {

  private static final String SIZE_PROPERTY_NAME = "size";
  private static final String DEFAULT_FILE_LOCATION = "game.properties";

  private final Set<Player> players = Stream.of(Robot, Human1, Human2)
      .collect(Collectors.toSet());

  public GameConfiguration read() {
    URL configurationFile = getClass().getClassLoader().getResource(DEFAULT_FILE_LOCATION);

    // We know this file exists in the resources file
    @SuppressWarnings("ConstantConditions")
    String fileLocation = configurationFile.getFile();

    return read(fileLocation);
  }

  public GameConfiguration read(String configurationFileLocation) {
    int size = DEFAULT_SIZE;

    try {
      Path configurationPath = Paths.get(configurationFileLocation);
      Properties configuration = new Properties();
      configuration.load(Files.newBufferedReader(configurationPath));
      size = obtainSize(configuration);
      String symbolsRawValue = configuration.getProperty("symbols", null);
      if (symbolsRawValue != null) {
        String[] customSymbols = symbolsRawValue.split(",");
        Robot.setSymbol(customSymbols[0]);
        Human1.setSymbol(customSymbols[1]);
        Human2.setSymbol(customSymbols[2]);
      }
    } catch (IOException e) {
      String errorMessage = "There was an error while loading the configuration. "
          + "Using default size";
      System.err.println(String.format(errorMessage, DEFAULT_SIZE));
    }

    return new GameConfiguration(size, players.toArray(new Player[0]));
  }

  private int obtainSize(Properties configuration) {
    String sizePropertyValue = configuration.getProperty(
        SIZE_PROPERTY_NAME,
        String.valueOf(DEFAULT_SIZE)
    );

    if (!sizePropertyValue.matches("^\\d+$")) {
      return DEFAULT_SIZE;
    }

    return Integer.parseInt(sizePropertyValue);
  }
}
