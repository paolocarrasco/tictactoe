package org.gaming.tictactoe;

import org.gaming.tictactoe.domain.GameConfiguration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.gaming.tictactoe.domain.GameConfiguration.DEFAULT_SIZE;

class GameSetup {

  private static final String SIZE_PROPERTY_NAME = "size";
  private static final String DEFAULT_FILE_LOCATION = "game.properties";

  GameConfiguration read() {
    URL configurationFile = getClass().getClassLoader().getResource(DEFAULT_FILE_LOCATION);

    // We know this file exists in the resources file
    @SuppressWarnings("ConstantConditions")
    String fileLocation = configurationFile.getFile();

    return read(fileLocation);
  }

  GameConfiguration read(String configurationFileLocation) {
    int size = DEFAULT_SIZE;

    try {
      Path configurationPath = Paths.get(configurationFileLocation);
      Properties configuration = new Properties();
      configuration.load(Files.newBufferedReader(configurationPath));
      size = obtainSize(configuration);
    } catch (IOException e) {
      String errorMessage = "There was an error while loading the configuration. Using default size";
      System.err.println(String.format(errorMessage, DEFAULT_SIZE));
    }

    return new GameConfiguration(size);
  }

  private int obtainSize(Properties configuration) {
    String sizePropertyValue = configuration.getProperty(SIZE_PROPERTY_NAME);

    if (!sizePropertyValue.matches("^\\d+$")) {
      return DEFAULT_SIZE;
    }

    return Integer.parseInt(sizePropertyValue);
  }
}
