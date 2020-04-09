package server;

import java.util.HashMap;

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
        // TODO
        addWord("USA", "United States of America");
        addWord("UK", "United Kingdom");
        System.out.println("Dictionary [" + dictFile + "] has been loaded");
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
        return this.dict.toString();
    }
}


