package Server;

import java.util.HashMap;

public class Dict {
    private String dictFile;
    private HashMap<String, String> dict;

    public Dict(String dictFile) {
        this.dictFile = dictFile;
        this.dict = loadDict(dictFile);
    }

    public HashMap<String, String> getDict() {
        return this.dict;
    }

    // read the dictionary file and store it into the HashMap
    private HashMap<String, String> loadDict(String dictFile) {
        addWord("USA", "United States of America");
        addWord("UK", "United Kingdom");

        return null;
    }

    public void addWord(String word, String meaning) {
        this.dict.put(word, meaning);
    }

    public String removeWord(String word){
        if (this.dict.remove(word) != null) {
            // successfully removed the word provided
            return "[" + word + "] has been successfully removed";
        } else {
            return "[" + word + "] doesn't exist in the dictionary";
        }
    }

    public String lookUp(String word) {
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


