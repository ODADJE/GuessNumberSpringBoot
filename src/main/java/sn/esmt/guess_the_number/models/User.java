package sn.esmt.guess_the_number.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name= "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  @Column(unique = true)
  private String login;
  private String password;

  @OneToMany(mappedBy = "user")
  List<Tentative> tentatives = new ArrayList<>();

  public User (String name, String login, String password) {
    this.name = name;
    this.login = login;
    this.password = password;
  }

  public User() {}

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

@Override
public String toString() {
  // TODO Auto-generated method stub
  return "User [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + "]";
}

  
}
