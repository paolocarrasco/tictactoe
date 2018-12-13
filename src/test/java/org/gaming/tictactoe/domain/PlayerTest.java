package org.gaming.tictactoe.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void shouldReturnPlayer1IfSymbolIsXWithDefaultForIt() {
    assertThat(Player.obtainPlayerBySymbol("X"), is(Player.Human1));
  }

  @Test
  public void shouldReturnPlayer2IfSymbolIsXWithDefaultForIt() {
    assertThat(Player.obtainPlayerBySymbol("O"), is(Player.Human2));
  }

  @Test
  public void shouldReturnRobotIfSymbolIsXWithDefaultForIt() {
    assertThat(Player.obtainPlayerBySymbol("C"), is(Player.Robot));
  }

  @Test
  public void shouldReturnNoOneIfSymbolIsNull() {
    assertThat(Player.obtainPlayerBySymbol(null), is(Player.NoOne));
  }

  @Test
  public void shoulReturnPlayerIfSymbolIsCustomized() {
    Player.Robot.setSymbol("R");
    assertThat(Player.obtainPlayerBySymbol("R"), is(Player.Robot));
  }
}