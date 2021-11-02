package output;

import exceptions.InvalidRedirectionException;
import filesystem.Directory;
import filesystem.File;
import filesystem.Path;

/**
 * Sends output to file
 */
public class FileWriter implements Output {

  /**
   * The file to send output to
   */
  private final Path path;
  private boolean overwrite;

  /**
   * Create a FileWriter for a file
   *
   * @param path The file
   * @param overwrite Whether or not overwrite mode is enabled
   */
  public FileWriter(Path path, boolean overwrite) {
    this.path = path;
    this.overwrite = overwrite;
  }

  @Override
  public void send(Object obj) {
    if (obj.toString().length() > 0) {
      getOutFile().appendContent(obj.toString());
    }
  }

  @Override
  public void sendln(Object obj) {
    send(obj + System.lineSeparator());
  }

  @Override
  public void sendln() {
    send(System.lineSeparator());
  }

  /**
   * Get the file to write to, creating it if necessary
   *
   * @return File The file to write to
   */
  private File getOutFile() {
    if (path.getFile() instanceof File) {
      File file = (File) path.getFile();
      if (overwrite) {
        file.setContent("");
        overwrite = false;
      }
      return file;
    } else if (path.getParent() instanceof Directory
        && path.getFile() == null) {
      overwrite = false;
      File file = new File(path.getFileName());
      ((Directory) path.getParent()).addChild(file);
      return file;
    }
    throw new InvalidRedirectionException();
  }
}
