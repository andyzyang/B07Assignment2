// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: yangzon7
// UT Student #: 1004453423
// Author: Zongye Yang
//
// Student2:
// UTORID user_name: langyu1
// UT Student #: 1004026895
// Author: Yu Qiang Lang
//
// Student3:
// UTORID user_name: makgabri
// UT Student #: 1003591400
// Author: Gabrian Mak
//
// Student4:
// UTORID user_name: taojia5
// UT Student #: 1004512486
// Author: Jia Qi Tao
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import input.UserInput;
import org.junit.Test;

public class UserInputTest {

  @Test
  public void testCommand1() {
    assertEquals("Command only, no spaces", "cmd",
        new UserInput("cmd").getCommand());
  }

  @Test
  public void testCommand2() {
    assertEquals("Empty string", "", new UserInput("").getCommand());
  }

  @Test
  public void testCommand3() {
    assertEquals("Spaces only", "", new UserInput("   ").getCommand());
  }

  @Test
  public void testCommand4() {
    assertEquals("Command only with spaces in front", "cmd",
        new UserInput("   cmd").getCommand());
  }

  @Test
  public void testCommand5() {
    assertEquals("Command only with spaces at end", "cmd",
        new UserInput("cmd   ").getCommand());
  }

  @Test
  public void testCommand6() {
    assertEquals("Command only with spaces everywhere", "cmd",
        new UserInput("   cmd   ").getCommand());
  }

  @Test
  public void testCommand7() {
    assertEquals("Command with 3 args", "cmd",
        new UserInput("cmd arg1 arg2 arg3").getCommand());
  }

  @Test
  public void testCommand8() {
    assertEquals("Command with 3 args and spaces in front", "cmd",
        new UserInput("   cmd arg1 arg2 arg3").getCommand());
  }

  @Test
  public void testCommand9() {
    assertEquals("Command with 3 args and spaces at end", "cmd",
        new UserInput("cmd arg1 arg2 arg3   ").getCommand());
  }

  @Test
  public void testCommand10() {
    assertEquals("Command with 3 args and spaces between", "cmd",
        new UserInput("cmd   arg1  arg2   arg3").getCommand());
  }

  @Test
  public void testCommand11() {
    assertEquals("Command with 3 args and spaces everywhere", "cmd",
        new UserInput("  cmd  arg1    arg2    arg3    ").getCommand());
  }

  @Test
  public void testArgument1() {
    assertEquals("Command only, no spaces", "",
        new UserInput("cmd").getArgument().toString());
  }

  @Test
  public void testArgument2() {
    assertEquals("Empty string", "",
        new UserInput("").getArgument().toString());
  }

  @Test
  public void testArgument3() {
    assertEquals("Spaces only", "",
        new UserInput("   ").getArgument().toString());
  }

  @Test
  public void testArgument4() {
    assertEquals("Command only with spaces in front", "",
        new UserInput("   cmd").getArgument().toString());
  }

  @Test
  public void testArgument5() {
    assertEquals("Command only with spaces at end", "",
        new UserInput("cmd   ").getArgument().toString());
  }

  @Test
  public void testArgument6() {
    assertEquals("Command only with spaces everywhere", "",
        new UserInput("   cmd   ").getArgument().toString());
  }

  @Test
  public void testArgument7() {
    assertEquals("Command with 3 args", "arg1 arg2 arg3",
        new UserInput("cmd arg1 arg2 arg3").getArgument().toString());
  }

  @Test
  public void testArgument8() {
    assertEquals("Command with 3 args and spaces in front", "arg1 arg2 arg3",
        new UserInput("   cmd arg1 arg2 arg3").getArgument().toString());
  }

  @Test
  public void testArgument9() {
    assertEquals("Command with 3 args and spaces at end", "arg1 arg2 arg3",
        new UserInput("cmd arg1 arg2 arg3   ").getArgument().toString());
  }

  @Test
  public void testArgument10() {
    assertEquals("Command with 3 args and spaces between", "arg1  arg2   arg3",
        new UserInput("cmd   arg1  arg2   arg3").getArgument().toString());
  }

  @Test
  public void testArgument11() {
    assertEquals("Command with 3 args and spaces everywhere",
        "arg1    arg2    arg3",
        new UserInput("  cmd  arg1    arg2    arg3    ").getArgument()
            .toString());
  }

  @Test
  public void testSpaceSeparatedArguments1() {
    assertArrayEquals("Command only, no spaces", new String[]{},
        new UserInput("cmd").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments2() {
    assertArrayEquals("Command only with spaces at beginning", new String[]{},
        new UserInput("   cmd").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments3() {
    assertArrayEquals("Command only with spaces at end", new String[]{},
        new UserInput("cmd   ").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments4() {
    assertArrayEquals("Command only with spaces at both ends", new String[]{},
        new UserInput("cmd").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments5() {
    assertArrayEquals("Command with 1 arg", new String[]{"arg"},
        new UserInput("cmd arg").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments6() {
    assertArrayEquals("Command with 3 arg",
        new String[]{"arg1", "arg2", "arg3"},
        new UserInput("cmd arg1 arg2 arg3").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments7() {
    assertArrayEquals("Command with 1 arg and extra spaces between",
        new String[]{"arg"},
        new UserInput("cmd    arg").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments8() {
    assertArrayEquals("Command with 1 arg and extra spaces in front",
        new String[]{"arg"},
        new UserInput("   cmd arg").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments9() {
    assertArrayEquals("Command with 1 arg and extra spaces at end",
        new String[]{"arg"},
        new UserInput("cmd arg   ").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments10() {
    assertArrayEquals("Command with 1 arg and extra spaces everywhere",
        new String[]{"arg"},
        new UserInput("   cmd   arg   ").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments11() {
    assertArrayEquals("Command with 3 arg and extra spaces in front",
        new String[]{"arg1", "arg2", "arg3"},
        new UserInput("   cmd arg1 arg2 arg3").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments12() {
    assertArrayEquals("Command with 3 arg and extra spaces at end",
        new String[]{"arg1", "arg2", "arg3"},
        new UserInput("cmd arg1 arg2 arg3   ").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments13() {
    assertArrayEquals("Command with 3 arg and extra spaces between",
        new String[]{"arg1", "arg2", "arg3"},
        new UserInput("cmd    arg1    arg2    arg3")
            .getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments14() {
    assertArrayEquals("Command with 3 arg and extra spaces everywhere",
        new String[]{"arg1", "arg2", "arg3"},
        new UserInput("   cmd    arg1    arg2    arg3   ")
            .getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments15() {
    assertArrayEquals("Empty string", new String[]{},
        new UserInput("").getArgument().parseSpaces());
  }

  @Test
  public void testSpaceSeparatedArguments16() {
    assertArrayEquals("Space only string", new String[]{},
        new UserInput("         ").getArgument().parseSpaces());
  }
}