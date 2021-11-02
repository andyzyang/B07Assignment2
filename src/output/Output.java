package output;

/**
 * Represents something that can send output
 */
public interface Output {

  /**
   * Send string as regular output
   *
   * @param obj The object whose toString() to send as output
   */
  void send(Object obj);

  /**
   * Send string + newline as regular output
   *
   * @param obj The object whose toString() + newline to send as output
   */
  void sendln(Object obj);

  /**
   * Send a newline as output
   */
  void sendln();
}
