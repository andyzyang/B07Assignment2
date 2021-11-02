package exceptions;

public class ParsingException extends ShellException {

  public ParsingException() {
    super("error parsing input");
  }
}
