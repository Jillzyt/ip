package src.main.java.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import src.main.java.duke.data.Duke;
import src.main.java.duke.data.exception.IllegalValueException;


/**
 * StorageFile manages the storage of the Best2103Bot
 */
public class StorageFile {

    private static String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/data";
    private static String TEXT_FILE_NAME = "/duke.txt";

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = STORAGE_DIRECTORY + TEXT_FILE_NAME;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageFile() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageFile(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file. The file path
     * is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the {@code addressBook} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or
     *                                   storing data to file.
     */
    public void save(Duke duke) throws StorageOperationException {
        try {
            File tempFile = new File(String.valueOf(path));
            boolean exists = tempFile.exists();
            if (exists) {
                List<String> encodedDuke = DukeEncoder.encodeDuke(duke);
                Files.write(path, encodedDuke);
            } else {
                File directory = new File(System.getProperty("user.dir") + "/data");
                directory.mkdirs();
                File file = new File(String.valueOf(path));
                boolean truth = file.createNewFile();
                List<String> encodedDuke = DukeEncoder.encodeDuke(duke);
                Files.write(path, encodedDuke);
            }
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code Duke} data from this storage file, and then returns it.
     * Returns an empty {@code Duke} if the file does not exist, or is not a regular
     * file.
     *
     * @throws StorageOperationException if there were errors reading and/or
     *                                   converting data from file.
     */
    public Duke load() throws StorageOperationException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new Duke();
        }
        try {
            return DukeDecoder.decodeDuke(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    public String getPath() {
        return path.toString();
    }

    /*
     * Note: Note the use of nested classes below. More info
     * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath
     * constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write
     * data between the application and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}