package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node root;

    public Queue() {
        root = null;
    }

    public void enqueue(final int data) {
        root = LinkedList.append(root, data);
    }

    public int peek() {
        return root.getData();
    }

    public int dequeue() {
        var data = root.getData();
        root = LinkedList.removeAt(root, 0);
        return data;
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