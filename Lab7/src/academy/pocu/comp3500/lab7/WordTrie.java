package academy.pocu.comp3500.lab7;

public class WordTrie {
    public static WordTrieNode insertWordTrieNode(WordTrieNode node, char character) {
        if (node.containsCharacterInChildren(character)) {  // 자식 노드 중 같은 단어를 가지고 있는 경우
            return node.getChildNode(character);
        } else {    // 없는 경우
            var childNode = new WordTrieNode(character);
            node.insertChild(childNode);
            return childNode;
        }
    }

    public static void insertFinalWordTrieNode(WordTrieNode node, String word, char character) {
        if (node.containsCharacterInChildren(character)) {  // 자식 노드 중 같은 단어를 가지고 있는 경우

            node.getChildNode(character).setEndOfOriginalWord(word);
            return;
        }

        var finalNode = new WordTrieNode(character);
        finalNode.setEndOfOriginalWord(word);
        node.insertChild(finalNode);
    }

    public static WordTrieNode FindWordTrieNode(WordTrieNode node, char character) {
        if (node.containsCharacterInChildren(character))
            return node.getChildNode(character);
        else
            return null;
    }

//    public static void printTrie(WordTrieNode node) {
//        queue.enqueue(node);
//        while (queue.getSize() != 0) {
//            var childNode = queue.dequeue();
//            var childNodeEntrySet = childNode.getChildrenHashMap().entrySet();
//            for (var obj : childNodeEntrySet) {
//                queue.enqueue(obj.getValue());
//            }
//            System.out.print(childNode.getAlphabet());
//        }
//    }
}
