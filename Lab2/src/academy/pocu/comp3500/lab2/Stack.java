package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node root;
    private int size;

    public Stack() {
        root = null;
        size = 0;
    }

    public void push(final int data) {
        root = LinkedList.prepend(root, data);
        size++;
    }

    public int peek() {
        return root.getData();
    }

    public int pop() {
        var data = peek();
        root = LinkedList.removeAt(root, 0);
        size--;
        return data;
    }

    public int getSize() {
        return size;
    }
}