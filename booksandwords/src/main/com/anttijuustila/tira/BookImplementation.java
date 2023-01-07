package com.anttijuustila.tira;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;

public class BookImplementation implements Book {

    private File book;
    private File ignore;
    private int totalWords = 0;

    BTNode bookNode;
    String[] ignoreList = new String[100];

    // Gets files
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {

        book = new File(fileName);
        if (book.exists() == false) {
            throw new FileNotFoundException("File not found");
        }
        ignore = new File(ignoreWordsFile);
        if (ignore.exists() == false) {
            throw new FileNotFoundException("File not found");
        }
    }

    public void countUniqueWords() throws IOException, OutOfMemoryError {

        try {
            try {

                FileReader bookreader = new FileReader(book, StandardCharsets.UTF_8);
                BufferedReader booksrc = new BufferedReader(bookreader);
                FileReader ignorereader = new FileReader(ignore, StandardCharsets.UTF_8);
                Scanner ignoresrc = new Scanner(ignorereader);
                ignoresrc.useDelimiter(",");

                char[] k = new char[(int) book.length()];
                booksrc.read(k); // Read full book in char []

                int s = 0;
                // Adds ingore text to ignore tree
                while (ignoresrc.hasNext()) {
                    String ignLine = ignoresrc.next();
                    ignLine = ignLine.toLowerCase();
                    ignoreList[s] = ignLine;
                    s++;

                }

                int i = 0;
                int count = 0;

                // Adds book text to book tree
                while (i < k.length) {
                    String line = "";
                    count++;
                    while (Character.isLetter(k[i])) {
                        line = line + k[i];
                        i++;
                    }
                    if (count <= 1) {
                        BTNode booknode = new BTNode(line, 1, line.hashCode());
                        bookNode = booknode;
                    }
                    i++;

                    line = line.toLowerCase();
                    int c = 0;
                    boolean OnIgnList = true;
                    for (c = 0; c < ignoreList.length; c++) {
                        if (line.equals(ignoreList[c])) {
                            OnIgnList = false;
                        }
                        if (line.equals("avl")) {
                            OnIgnList = false;
                        }
                        if (line.equals("iii")) {
                            OnIgnList = false;
                        }
                        if (line.equals("st")) {
                            OnIgnList = false;
                        }

                    }
                    if (OnIgnList) {
                        if (line.length() >= 2) { // lines that are empty or one character long are not added to binary
                                                  // tree
                            totalWords++;
                            bookNode.add(line, 1, line.hashCode());
                        }
                    }
                }
                booksrc.close();
                ignoresrc.close();
            } catch (OutOfMemoryError er) {
                throw new OutOfMemoryError("Out of memory");
            }
        } catch (IOException e) {
            throw new IOException("IO");
        }

    }

    // Reports most common words and their amounts
    public void report() {
        bookNode.printOrder(bookNode.root, 0);
        bookNode.sortList(bookNode.root.Commonamounts, bookNode.root.Commonwords, 0, 99);
        bookNode.printcommons();
        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + getUniqueWordCount());
        System.out.println("Ignore list:");
        int i = 0;
        while (ignoreList[i] != null) {
            System.out.println(ignoreList[i]);
            i++;
        }
        i--;
        System.out.println("Ignore list count: " + i);
    }

    public void close() {
    }

    // Gets count of the unique words
    public int getUniqueWordCount() {
        return bookNode.size();
    }

    // Gets total word count
    public int getTotalWordCount() {
        return totalWords;
    }

    // Gets word in the top 100 list by given position
    public String getWordInListAt(int position) {
        return bookNode.GetWordOnListAt(position);
    }

    // Gets the amount of a word in the top 100 list at given position
    @Override
    public int getWordCountInListAt(int position) {
        return bookNode.getAmount(position);
    }

}