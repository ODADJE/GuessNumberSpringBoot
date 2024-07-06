package sn.esmt.guess_the_number.exception;

import org.eclipse.jdt.core.compiler.InvalidInputException;

public class NumberOutOfBoundError extends Exception{
  public NumberOutOfBoundError() {
    super("Le nombre saisie doit etre compris entre 1 et 100.");
}
}
