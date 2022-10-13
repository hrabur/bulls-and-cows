package com.github.hrabur.bullsandcows.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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
    fail("TODO");
  }

  @Test
  void testStartNewGame() {
    fail("TODO");
  }

  @Test
  void testMakeGuess() {
    fail("TODO");
  }
}
