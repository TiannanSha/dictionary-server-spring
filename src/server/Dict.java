/**
 * @auther Tiannan Sha 861696
 * This class implements a dictionary class that users can search a word, insert a word, delete a word,
 * print the entire dictionary.
 */

package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Dict {
    private String dictFile;
    private HashMap<String, String> dict = new HashMap<>();

    public Dict(String dictFile) {
        this.dictFile = dictFile;
        loadDict(dictFile);
    }

    public HashMap<String, String> getDict() {
        return this.dict;
    }

    // read the dictionary file and store it into the HashMap
    private void loadDict(String dictFile) {
        File file = new File(dictFile);
        try {
            Scanner scanner = new Scanner(file);
            // read the dict file line by line
            while (scanner.hasNextLine()) {
                String[] lineWords = scanner.nextLine().split("\\*");
                String word = lineWords[0];
                String meaning = lineWords[1];
                addWord(word, meaning);
            }
            System.out.println("Dictionary [" + dictFile + "] has been loaded");

        } catch (FileNotFoundException e) {
            System.out.println("Can't find dictionary file " + dictFile);
        }
    }

    public String addWord(String word, String meaning) {
        this.dict.put(word, meaning);
        return "[" + word + "] has been successfully added";
    }

    public String removeWord(String word){
        if (this.dict.remove(word) != null) {
            // successfully removed the word provided
            return "[" + word + "] has been successfully removed";
        } else {
            return "[" + word + "] doesn't exist in the dictionary";
        }
    }

    public String search(String word) {
        String meaning = this.dict.get(word);
        if (meaning!=null) {
            return meaning;
        } else {
            return "[" + word + "] doesn't exist in the dictionary";
        }
    }

    @Override
    public String toString() {
        String result = "";
        Iterator iterator = this.dict.entrySet().iterator();
        while (iterator.hasNext()) {
            result = result + iterator.next().toString() + "\n";
        }
        return result;
    }
}


