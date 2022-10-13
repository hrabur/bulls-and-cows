package com.github.hrabur.bullsandcows.logic;

import com.github.hrabur.bullsandcows.repo.Game;
import com.github.hrabur.bullsandcows.repo.GameRepo;
import com.github.hrabur.bullsandcows.repo.Guess;
import java.util.HashSet;
import java.util.Random;

public class BullsAndCowsServiceImpl implements BullsAndCowsService {

  private GameRepo gameRepo;

  public BullsAndCowsServiceImpl(GameRepo gameRepo) {
    this.gameRepo = gameRepo;
  }

  @Override
  public Game startNewGame() {
    var chosenNumber = generateNumber();
    var game = new Game(chosenNumber);
    gameRepo.save(game);
    return game;
  }

  @Override
  public Game getGameById(Long gameId) {
    return gameRepo.findById(gameId).orElse(null);
  }

  @Override
  public Game makeGuess(Long gameId, String guessedNumber) {
    var game = getGameById(gameId);
    var guess = checkGuess(game.getChosenNumber(), guessedNumber);
    game.getGuess().add(guess);
    return game;
  }

  String generateNumber() {
    var random = new Random();
    var used = new HashSet<>();
    var buff = new StringBuilder();
    for (int i = 0; i < 4; i++) {
      int digit;
      while (true) {
        digit = random.nextInt(0, 10);
        if (used.contains(digit)) continue;
        used.add(digit);
        break;
      }

      buff.append(digit);
    }

    return buff.toString();
  }

  Guess checkGuess(String chosen, String guessed) {
    int bulls = 0, cows = 0;
    for (int gi = 0; gi < guessed.length(); gi++) {
      for (int ci = 0; ci < chosen.length(); ci++) {
        if (guessed.charAt(gi) == chosen.charAt(ci)) {
          if (gi == ci) {
            bulls++;
          } else {
            cows++;
          }
        }
      }
    }

    return new Guess(guessed, bulls, cows);
  }
}
