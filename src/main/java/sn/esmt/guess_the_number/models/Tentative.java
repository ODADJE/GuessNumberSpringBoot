package sn.esmt.guess_the_number.models;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tentative {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int tentativeNumber;
 private  Duration duration;

  @ManyToOne
  private User user;

  public Tentative(int tentativeNumber, User user, Duration duration) {
    this.tentativeNumber = tentativeNumber;
    this.user = user;
    this.duration = duration;
  }

  public Tentative() {}

  public int getTentativeNumber() {
    return tentativeNumber;
  }
  public void setTentativeNumber(int tentativeNumber) {
    this.tentativeNumber = tentativeNumber;
  }
  public Duration getDuration() {
    return duration;
  }
  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Tentative [id=" + id + ", tentativeNumber=" + tentativeNumber + "User= " + user + "]";
  }
  
}
