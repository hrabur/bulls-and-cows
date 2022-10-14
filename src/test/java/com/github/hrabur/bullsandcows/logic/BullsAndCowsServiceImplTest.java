package com.github.hrabur.bullsandcows.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.github.hrabur.bullsandcows.repo.Game;
import com.github.hrabur.bullsandcows.repo.GameRepo;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class BullsAndCowsServiceImplTest {

  static Stream<Arguments> testCheckGuessMethodSource() {
    return Stream.of(
        arguments("1234", "5678", 0, 0),
        arguments("1234", "1548", 1, 1),
        arguments("1234", "4321", 0, 4),
        arguments("1234", "1234", 4, 0));
  }

  // If no factory method names are declared for @MethodSource, a static method within the test
  // class that has the same name as the test method will be used as the factory method by default.
  @MethodSource
  @ParameterizedTest
  void testCheckGuessMethodSource(String chosen, String guessed, int bulls, int cows) {
    var bullsAndCowsService = new BullsAndCowsServiceImpl(null);
    var result = bullsAndCowsService.checkGuess(chosen, guessed);
    assertThat(result.getBulls()).isEqualTo(bulls);
    assertThat(result.getCows()).isEqualTo(cows);
  }

  @ParameterizedTest
  @CsvSource({"1234, 5678, 0, 0", "1234, 1548, 1, 1", "1234, 4321, 0, 4", "1234, 1234, 4, 0"})
  void testCheckGuessCsvSource(String chosen, String guessed, int bulls, int cows) {
    var bullsAndCowsService = new BullsAndCowsServiceImpl(null);
    var result = bullsAndCowsService.checkGuess(chosen, guessed);
    assertEquals(bulls, result.getBulls());
    assertEquals(cows, result.getCows());
  }

  @Test
  void testGenerateNummber() {
    var bullsAndCowsService = new BullsAndCowsServiceImpl(null);
    var bullsAndCowsServiceSpy = Mockito.spy(bullsAndCowsService);
    when(bullsAndCowsServiceSpy.nextRandomDigit())
        .thenReturn(0)
        .thenReturn(1)
        .thenReturn(2)
        .thenReturn(2)
        .thenReturn(3)
        .thenReturn(4);
    var result = bullsAndCowsServiceSpy.generateNumber();
    assertThat(result).isEqualTo("1234");
    verify(bullsAndCowsServiceSpy, times(6)).nextRandomDigit();
  }

  @Test
  void testStartNewGame() {
    var gameRepoMock = mock(GameRepo.class);
    when(gameRepoMock.save(any()))
        .then(
            invocation -> {
              var game = invocation.getArgument(0, Game.class);
              game.setId(123456l);
              return game;
            });
    var bullsAndCowsService = new BullsAndCowsServiceImpl(gameRepoMock);
    var bullsAndCowsServiceSpy = Mockito.spy(bullsAndCowsService);
    when(bullsAndCowsServiceSpy.generateNumber()).thenReturn("1234");

    var game = bullsAndCowsServiceSpy.startNewGame();
    assertThat(game).isNotNull();
    assertThat(game.getId()).isEqualTo(123456l);
    assertThat(game.getChosenNumber()).isEqualTo("1234");
    assertThat(game.getGuess()).isEmpty();
    verify(gameRepoMock).save(any());
  }

  @Test
  void testMakeGuess() {
    fail("TODO");
  }
}
