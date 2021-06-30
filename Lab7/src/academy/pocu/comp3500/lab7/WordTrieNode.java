package academy.pocu.comp3500.lab7;

import java.util.HashMap;
import java.util.LinkedList;

public class WordTrieNode {
    private char alphabet;
    private HashMap<Character, WordTrieNode> childrenHashMap;
    private boolean isEndOfWord;
    private LinkedList<String> wordList;

    public WordTrieNode(char character) {
        alphabet = character;
        childrenHashMap = new HashMap<Character, WordTrieNode>();
        wordList = new LinkedList<String>();
    }

    public char getAlphabet() {
        return alphabet;
    }

    public int getChildrenHashSize() {
        return childrenHashMap.size();
    }

    public HashMap<Character, WordTrieNode> getChildrenHashMap() {
        return childrenHashMap;
    }

    public void setEndOfOriginalWord(String word) {
        this.wordList.add(word);
        isEndOfWord = true;
    }

    public boolean containsCharacterInChildren(char character) {
        return childrenHashMap.containsKey(character);
    }

    public void insertChild(WordTrieNode node) {
        childrenHashMap.put(node.getAlphabet(), node);
    }

    public WordTrieNode getChildNode(char character) {
        return childrenHashMap.get(character);
    }

    public int getWordListSize() {
        return wordList.size();
    }

    public LinkedList<String> getWordList() {
        return wordList;
    }
}
