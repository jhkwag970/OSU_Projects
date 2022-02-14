import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        BufferedReader in;
        BufferedReader filter;
        PrintWriter out;

        try {
            filter = new BufferedReader(new FileReader(args[2]));
        } catch (IOException e) {
            System.err.println("Error opening the file");
            return;
        }

        Set<String> filterList = new HashSet<String>();
        try {
            String filterString = filter.readLine();
            while (filterString != null) {
                filterList.add(filterString);
                filterString = filter.readLine();
            }
            filter.close();
        } catch (IOException e) {
            System.err.println("Error reading filter file");
            return;
        }

        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }

        try {
            String line = in.readLine();

            while (line != null) {
                for (String str : filterList) {
                    if (line.contains(str)) {
                        out.println(line);
                    }
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading the file");
        }
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing the file");

        }

    }

}
