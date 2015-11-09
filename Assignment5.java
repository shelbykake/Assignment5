/*
 Shelby Kaiser
 Novermber 8, 2015
 Purpose: to return the average comparisons of words found and not found in 
 oliver.txt
 */

import java.util.*;
import java.io.*;

public class Assignment5 {

    long wordsFound = 0;
    long wordsNotFound = 0;
    long foundComp = 0;
    long notFound = 0;
    BinarySearchTree[] dictionary = new BinarySearchTree[26];

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Assignment5 program = new Assignment5();

        program.readDictionary();
        program.readOliver();
        double avgFound = (double) program.foundComp / program.wordsFound;
        double avgNotFound = (double) program.notFound / program.wordsNotFound;
        System.out.println("\t\tWords Found" + "\tWords Not Found");
        System.out.println("Total Words:\t" + program.wordsFound + "\t\t" + program.wordsNotFound);
        System.out.println("Comparisons:\t" + program.foundComp + "\t" + program.notFound);
        System.out.printf("Average:\t%2.2f", avgFound);
        System.out.printf("\t\t%2.2f", avgNotFound);

    }

    /**
     * Ensures: checks what words are found in binary tree and gathers
     * comparisons REquires: dictionary binary trees are populated and file
     * being read from is present
     */
    public void readOliver() {
        try {
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    if (dictionary[str.charAt(0) - 97].search(str) == true) {
                        foundComp += dictionary[str.charAt(0) - 97].getComparisons(str);
                        wordsFound++;
                    } else if (dictionary[str.charAt(0) - 97].search(str) == false) {
                        notFound += dictionary[str.charAt(0) - 97].getComparisons(str);
                        wordsNotFound++;
                    }
                    str = "";
                }

            }
            inf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ensures: dictionary file is read into binary trees Requires: dictionary
     * file to be present
     */
    public void readDictionary() {
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<String>();

        }
        try {
            FileInputStream inf = new FileInputStream(new File("random_dictionary.txt"));
            char let;
            String str = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);

                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    dictionary[str.charAt(0) - 97].insert(str);
                    str = "";
                }

            }
            inf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
 /*
output: 
		Words Found	Words Not Found
Total Words:	939674		52466
Comparisons:	15284398	568360
Average:	16.27		10.83
*/
