package org.gaming.tictactoe.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.*;

public class PlayerTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Player[] players;

  @Before
  public void setUp() {
    players = new Player[]{Human1, Human2, Robot};
  }

  @After
  public void tearDown() {
    Robot.setSymbol("C");
  }

  @Test
  public void shouldReturnPlayer1IfSymbolIsXWithDefaultForIt() {
    assertThat(obtainPlayerBySymbol("X", players), is(Human1));
  }

  @Test
  public void shouldReturnPlayer2IfSymbolIsXWithDefaultForIt() {
    assertThat(obtainPlayerBySymbol("O", players), is(Human2));
  }

  @Test
  public void shouldReturnRobotIfSymbolIsXWithDefaultForIt() {
    assertThat(obtainPlayerBySymbol("C", players), is(Robot));
  }

  @Test
  public void shouldReturnNoOneIfSymbolIsNull() {
    assertThat(obtainPlayerBySymbol(null, players), is(NoOne));
  }

  @Test
  public void shoulReturnPlayerIfSymbolIsCustomized() {
    Robot.setSymbol("R");
    assertThat(obtainPlayerBySymbol("R", players), is(Robot));
  }

  @Test
  public void shouldBeEqualToAnotherPlayerIfTheyHaveTheSameName() {
    assertThat(Robot, is(not(equalTo(Human1))));
    assertThat(Robot, is(not(equalTo(Human2))));
    assertThat(Human1, is(not(equalTo(Human2))));
  }
}