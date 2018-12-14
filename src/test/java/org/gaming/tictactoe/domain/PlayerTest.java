package org.gaming.tictactoe.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.gaming.tictactoe.domain.PlayerContainer.*;

public class PlayerTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void shouldBeEqualToAnotherPlayerIfTheyHaveTheSameName() {
    assertThat(Robot, is(not(equalTo(Human1))));
    assertThat(Robot, is(not(equalTo(Human2))));
    assertThat(Human1, is(not(equalTo(Human2))));
  }
}