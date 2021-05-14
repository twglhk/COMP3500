package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node root;

    Stack() {
    }

    public void push(final int data) {
        root = LinkedList.prepend(root, data);
    }

    public int peek() {
        return root.getData();
    }

    public int pop() {
        return LinkedList.removeAt(root, 0).getData();
    }

    public int getSize() {
        var stackSize = 0;
        var currentNode = root;
        while (currentNode != null) {
            currentNode = currentNode.getNextOrNull();
            stackSize++;
        }
        return stackSize;
    }
}