package org.gaming.tictactoe.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {

  @Test
  public void shouldReturnPlayer1IfSymbolIsX() {
    assertThat(Player.obtainPlayerBySymbol("X"), is(Player.Human1));
  }

  @Test
  public void shouldReturnPlayer2IfSymbolIsO() {
    assertThat(Player.obtainPlayerBySymbol("O"), is(Player.Human2));
  }

  @Test
  public void shouldReturnRobotIfSymbolIsC() {
    assertThat(Player.obtainPlayerBySymbol("C"), is(Player.Robot));
  }

  @Test
  public void shouldReturnUnknownIfSymbolIsNull() {
    assertThat(Player.obtainPlayerBySymbol(null), is(Player.Unknown));
  }
}