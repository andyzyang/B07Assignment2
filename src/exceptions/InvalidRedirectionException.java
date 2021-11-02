package exceptions;

public class InvalidRedirectionException extends ShellException {

  public InvalidRedirectionException() {
    super("invalid redirection");
  }
}
