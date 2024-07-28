package sn.esmt.guess_the_number.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.esmt.guess_the_number.models.Tentative;
import sn.esmt.guess_the_number.repository.TentativeRepository;

@Service
public class TentativesService {
  @Autowired
  private TentativeRepository tentativeRepository;

  public Tentative createTentative(Tentative tentative){
    return tentativeRepository.save(tentative);
  }
  public List<Tentative> findAllTentative(){ return tentativeRepository.findAll();}
}
