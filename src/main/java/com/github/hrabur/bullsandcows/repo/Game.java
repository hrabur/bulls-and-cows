package com.github.hrabur.bullsandcows.repo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Game {

  @Id
  @GeneratedValue
  @Column(name = "game_id")
  private String id;

  private String chosenNumber;

  @OrderColumn(name = "guess_id")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Guess> guess = new ArrayList<>();

  public Game() {}

  public Game(String chosenNumber) {
    this.chosenNumber = chosenNumber;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
