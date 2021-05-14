package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node root;

    public Queue() {
    }

    public void enqueue(final int data) {
        root = LinkedList.append(root, data);
    }

    public int peek() {
        return -1;
    }

    public int dequeue() {
        return -1;
    }

    public int getSize() {
        return -1;
    }
}