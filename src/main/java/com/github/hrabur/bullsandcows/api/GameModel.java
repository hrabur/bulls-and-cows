package com.github.hrabur.bullsandcows.api;

import com.github.hrabur.bullsandcows.repo.Guess;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.RepresentationModel;

public class GameModel extends RepresentationModel<GameModel> {

  private Long id;
  private String chosenNumber;
  private List<Guess> guess = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getChosenNumber() {
    return chosenNumber;
  }

  public void setChosenNumber(String chosenNumber) {
    this.chosenNumber = chosenNumber;
  }

  public List<Guess> getGuess() {
    return guess;
  }

  public void setGuess(List<Guess> guess) {
    this.guess = guess;
  }
}
