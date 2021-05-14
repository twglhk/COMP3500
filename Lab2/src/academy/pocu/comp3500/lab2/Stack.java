package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node root;
    private LinkedList linkedList;

    Stack() {
        linkedList = new LinkedList();
    }

    public void push(final int data) {
        root = linkedList.prepend(root, data);
    }

    public int peek() {
        return root.getData();
    }

    public int pop() {
        return linkedList.removeAt(root, 0).getData();
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