import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;
import components.utilities.Reporter;

/**
 * Put a short phrase describing the program here.
 *
 * @author Jiyong Kwag and Yuan Hong
 *
 */
public final class tagCloud {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private tagCloud() {
    }

    private static class compareInt
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {
            return p2.value() - p1.value();
        }
    }

    private static class compareString
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {
            return p1.key().toLowerCase().compareTo(p2.key().toLowerCase());
        }
    }

    /**
     *
     * processFeed is for inserting the separated word and its number of uses
     * into map and sequence.
     *
     * @param fileIn
     *            SimpleReader file
     * @param word
     *            the map that insert the word and its number of uses
     * @param wordIndex
     *            the sequence that insert the only word
     * @requires fileIn should not be empty
     * @update word and wordIndex
     * @ensures word and wordIndex should not be empty and key of the word must
     *          be same as wordIndex.
     */

    public static void countingWords(SimpleReader fileIn, Set<String> wordIndex,
            Map<String, Integer> word) {

        Set<Character> separatorSet = new Set1L<Character>();
        String separator = "\\ \t\n\r,-.!?[]';:/()@&~`\"";

        for (int i = 0; i < separator.length(); i++) {
            separatorSet.add(separator.charAt(i));
        }

        while (!fileIn.atEOS()) {
            String line = fileIn.nextLine();
            int pos = 0;
            while (pos < line.length()) {
                String token = nextWordOrSeparator(line, pos, separatorSet);
                if (!separatorSet.contains(token.charAt(0))) {
                    if (word.hasKey(token)) {
                        int num = word.value(token);
                        num++;
                        word.replaceValue(token, num);
                    } else {
                        word.add(token, 1);
                        wordIndex.add(token);
                    }
                }
                pos += token.length();
            }
        }
    }

    private static String nextWordOrSeparator(String text, int pos,
            Set<Character> separators) {
        int cnt = pos;
        String result = "";

        if (separators.contains(text.charAt(pos))) {
            while (cnt < text.length()
                    && separators.contains(text.charAt(cnt))) {
                cnt++;
            }
        } else {
            while (cnt < text.length()
                    && !separators.contains(text.charAt(cnt))) {
                cnt++;
            }
        }
        result = text.substring(pos, cnt);
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        String maxAndMin;
        Set<String> wordList = new Set1L<>();
        Map<String, Integer> wordCount = new Map1L<>();
        Comparator<Map.Pair<String, Integer>> intOrder = new compareInt();
        SortingMachine<Map.Pair<String, Integer>> mapSortedByInt = new SortingMachine1L<>(
                intOrder);
        Comparator<Map.Pair<String, Integer>> stringOrder = new compareString();
        SortingMachine<Map.Pair<String, Integer>> mapSortedByString = new SortingMachine1L<>(
                stringOrder);

        String inputFile = "";

        out.println("Please enter a txt file name with its location:");
        out.println("e.g. .../.../exampleTxtFile.txt");
        inputFile = in.nextLine();
        SimpleReader inFile = new SimpleReader1L(inputFile);

        String outputFile = "";

        out.println(
                "Please enter a html file name with its location as output:");
        out.println("e.g. .../.../exampleHTMLFile.html");
        outputFile = in.nextLine();
        SimpleWriter outFile = new SimpleWriter1L(outputFile);

        int n = 0;
        do {
            out.println("Please enter an positive integer as number of words "
                    + "included in word cloud:");
            n = in.nextInteger();
        } while (n < 0);

        countingWords(inFile, wordList, wordCount);
        maxAndMin = sortingWords(wordCount, mapSortedByInt, mapSortedByString,
                n);
        creatHtml(mapSortedByString, outFile, outputFile, maxAndMin);

        inFile.close();
        outFile.close();

        in.close();
        out.close();
    }

    public static void creatHtml(SortingMachine<Pair<String, Integer>> sort,
            SimpleWriter out, String txt, String maxAndmin) {

        out.println("<html>");

        out.println("<head>");
        out.println("<title>" + "Top " + sort.size() + " words in " + txt
                + "</title>");
        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel =\"stylesheet\" type=\"text/css\"");
        out.println("</head>");

        out.println("<body>");

        out.println("<h2> Top " + sort.size() + " words in " + txt + "</h2>");
        out.println("<hr>");
        out.println("<div class = \"cdiv\">");
        out.println("<p class=\"cbox\">");

        int index = maxAndmin.indexOf(';');
        String maxS = "", minS = "";
        maxS = maxAndmin.substring(0, index);
        minS = maxAndmin.substring(index + 1);

        int max = Integer.parseInt(maxS), min = Integer.parseInt(minS);

        int length = sort.size();
        for (int i = 0; i < length; i++) {

            Pair<String, Integer> wordCount = sort.removeFirst();
            String word = wordCount.key();
            int cnt = wordCount.value();

            int rang = (37 * (cnt - min) / (max - min)) + 11;

            out.println("<span style=\"cursor:default\" class=\"" + "f" + rang
                    + "\" title=\"count: " + cnt + "\">" + word + "</span>");

        }

        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    private static String sortingWords(Map<String, Integer> wordCount,
            SortingMachine<Pair<String, Integer>> mapSortedByInt,
            SortingMachine<Pair<String, Integer>> mapSortedByString, int n) {
        String maxAndMin = "0;0";

        for (Pair<String, Integer> p : wordCount) {
            mapSortedByInt.add(p);
        }

        Reporter.assertElseFatalError(n <= mapSortedByInt.size(),
                "number of word in ouput is greater than the number of words in text input; number of words try to show = "
                        + n + " words in txt input = " + mapSortedByInt.size());
        mapSortedByInt.changeToExtractionMode();

        for (int i = 0; i < n; i++) {
            Pair<String, Integer> p = mapSortedByInt.removeFirst();
            if (i == 0) {
                maxAndMin = p.value().toString() + ";";
            }
            if (i == n - 1) {
                maxAndMin += p.value().toString();
            }
            mapSortedByString.add(p);
        }
        mapSortedByString.changeToExtractionMode();
        return maxAndMin;
    }
}
