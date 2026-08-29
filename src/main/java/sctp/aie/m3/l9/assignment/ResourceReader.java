package sctp.aie.m3.l9.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ResourceReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceReader.class);

    // Resource file names for testing
    private static final String NON_EXISTENT_FILE = "nonexistentfile.txt";
    private static final String EXISTING_FILE = "existingfile.txt";

    private static String readFileContent(String resourceName) throws IOException {
        // Locate the resource file using the class loader
        URL resource = App.class.getClassLoader().getResource(resourceName);

        // Throw an exception if the resource is not found
        if (resource == null)
            throw new FileNotFoundException("Resource not found: " + resourceName);

        // Open the resource file and read its content
        try (InputStream input = resource.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            // Read the content of the resource file line by line
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            return content.toString();
        }
    }

    public static void runTest() {
        // Test reading a non-existent file first
        try {
            LOGGER.info("Attempting to read non-existent file: " + NON_EXISTENT_FILE);
            String content = ResourceReader.readFileContent(NON_EXISTENT_FILE);
            LOGGER.info("File content: \n" + content);
        } catch (FileNotFoundException ex) {
            LOGGER.error("File not found: ", ex);
        } catch (IOException ex) {
            LOGGER.error("An error occurred while reading the file: ", ex);
        }

        // Test reading an existing file next
        try {
            LOGGER.info("Attempting to read existing file: " + EXISTING_FILE);
            String content = ResourceReader.readFileContent(EXISTING_FILE);
            LOGGER.info("File content: \n" + content);
        } catch (FileNotFoundException ex) {
            LOGGER.error("File not found: ", ex);
        } catch (IOException ex) {
            LOGGER.error("An error occurred while reading the file: ", ex);
        }
    }

}
