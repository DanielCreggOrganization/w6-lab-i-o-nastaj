package ie.atu.iolab;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        // DIY 1
        // Path projectRoot = Paths.get(System.getProperty("user.dir"));
        // Path inputFilePath = projectRoot.resolve("resources").resolve("output.txt");

        // if (Files.exists(inputFilePath)) {
        //     System.out.println("output.txt found at: " + inputFilePath.toAbsolutePath());
        // } else {
        //     System.out.println("output.txt not found at: " + inputFilePath.toAbsolutePath());
        // }

        // DIY 2
        // String filePath = "resources/input.txt";
        // FileInputStream fis = null;

        // try {
        //     fis = new FileInputStream(filePath);
        //     int data;
        //     int charCount = 0;
        //     Byte bytes = 0;
        //     while ((data = fis.read()) != -1) {
        //         System.out.print((char) data); // Convert byte to character
        //         charCount++;
        //         bytes++;
        //     }
        //     System.out.println("\nTotal characters: " + charCount);
        //     System.out.println("Total bytes: " + bytes);

        // } catch (FileNotFoundException e) {
        //     System.err.println("File not found: " + e.getMessage());
        // } catch (IOException e) {
        //     System.err.println("Error reading file: " + e.getMessage());
        // } finally {
        //     try {
        //         if (fis != null) {
        //             fis.close(); // Always close the stream
        //         }
        //     } catch (IOException e) {
        //         System.err.println("Error closing file: " + e.getMessage());
        //     }
        // }

        // DIY 3
        // String inputPath = "resources/input.txt";
        // String outputPath = "resources/output.txt";

        // try (FileInputStream fis = new FileInputStream(inputPath);
        //      FileOutputStream fos = new FileOutputStream(outputPath)) {

        //     int data;
        //     while ((data = fis.read()) != -1) {
        //         if((char)data == 't' || (char)data == 'h' || (char)data == 'i' || (char)data == 's') {
        //             fos.write(Character.toUpperCase((char)data));
        //         } else {
        //             fos.write((char)data);
        //         }
        //     }
        //     System.out.println("File copied successfully.");

        // } catch (IOException e) {
        //     System.err.println("Error copying file: " + e.getMessage());
        // }
        
        // DIY 4
        // String inputPath = "resources/input.txt";
        // String outputPath = "resources/output.txt";

        // try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
        //      BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

        //     String line;
        //     while ((line = reader.readLine()) != null) { // Read line by line
        //         // writer.write(line.toUpperCase());
        //         if (line.contains("second")) {
        //             writer.write(line.toUpperCase());
        //             writer.newLine(); // Add a newline after each line
        //         }
        //     }
        //     System.out.println("File processed with buffered I/O.");
        // } catch (IOException e) {
        //     System.err.println("Error processing file: " + e.getMessage());
        // }

        // DIY 5
        String inputPath = "resources/input.txt";

        // Reading and counting lines
        try (Stream<String> lines = Files.lines(Paths.get(inputPath))) {
            long lineCount = lines.count();
            System.out.println("Number of lines: " + lineCount);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Reading and processing each line, and counting words
        try (Stream<String> lines = Files.lines(Paths.get(inputPath))) {
           long wordCount = lines
                    .flatMap(line -> Arrays.stream(line.split("\\s+"))) // Split each line into words
                    .count(); // Count the words
            System.out.println("Number of words: " + wordCount);

        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }

        // Finding longest word in a file
        try (Stream<String> lines = Files.lines(Paths.get(inputPath))) {
            String longestWord = lines
                    .flatMap(line -> Arrays.stream(line.split("\\s+"))) // Split each line into words
                    .max(Comparator.comparingInt(String::length)) // Find the longest word
                    .orElse(null); // Handle empty file case

            if (longestWord != null) {
                System.out.println("Longest word: " + longestWord);
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}