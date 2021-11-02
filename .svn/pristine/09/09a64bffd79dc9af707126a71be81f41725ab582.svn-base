package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import output.Console;

public class ConsoleTest {

  private ByteArrayOutputStream out;
  private Console console;

  @Before
  public void setUp() {
    console = new Console();
    out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
  }

  @Test
  public void testSendLn() {
    console.sendln();
    assertEquals(System.lineSeparator(), out.toString());
  }

  @Test
  public void testSendLnWithContent() {
    console.sendln("line");
    assertEquals("line" + System.lineSeparator(), out.toString());
  }

  @Test
  public void testSend() {
    console.send("line");
    assertEquals("line", out.toString());
  }
}