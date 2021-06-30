package academy.pocu.comp3500.lab7;

import java.util.PriorityQueue;

public class Decryptor {
    public WordTrieNode wordTrieRoot;

    public Decryptor(final String[] codeWords) {
        wordTrieRoot = new WordTrieNode('-');
        var priorityQueue = new PriorityQueue<Character>();
        for (int i = 0; i < codeWords.length; ++i) {
            for (int j = 0; j < codeWords[i].length(); ++j) {
                priorityQueue.add(Character.toLowerCase(codeWords[i].charAt(j)));
            }

            WordTrieNode nextNode = wordTrieRoot;
            while (priorityQueue.size() != 0) {
                if (priorityQueue.size() == 1) {
                    WordTrie.insertFinalWordTrieNode(nextNode, codeWords[i], priorityQueue.poll());
                } else {
                    nextNode = WordTrie.insertWordTrieNode(nextNode, priorityQueue.poll());
                }
            }
        }
    }

    public String[] findCandidates(final String word) {
        var priorityQueue = new PriorityQueue<Character>();
        for (int i = 0; i < word.length(); ++i) {
            priorityQueue.add(Character.toLowerCase(word.charAt(i)));
        }

        WordTrieNode nextNode = wordTrieRoot;
        while (priorityQueue.size() != 0) {
            if (nextNode == null)
                break;

            nextNode = WordTrie.findWordTrieNode(nextNode, priorityQueue.poll());
        }

        if (nextNode == null) {
            return new String[]{};
        }

        var result = new String[nextNode.getWordListSize()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = nextNode.getWordList().get(i).toLowerCase();
        }

        return result;
    }
}