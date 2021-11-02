package test;

import output.Output;

/**
 * Mock output for testing commands
 */
public class MockOutput implements Output {

  private String output = "";

  @Override
  public void send(Object obj) {
    output += obj.toString();
  }

  @Override
  public void sendln(Object obj) {
    send(obj + System.lineSeparator());
  }

  @Override
  public void sendln() {
    send(System.lineSeparator());
  }

  @Override
  public String toString() {
    return output;
  }
}
