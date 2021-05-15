package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node frontNode;
    private Node backNode;
    private int queueSize;

    public Queue() {
        frontNode = null;
        backNode = null;
        queueSize = 0;
    }

    public void enqueue(final int data) {
        backNode = LinkedList.append(backNode, data).getNextOrNull();
        if (frontNode == null)
            frontNode = backNode;
        queueSize++;
    }

    public int peek() {
        return frontNode.getData();
    }

    public int dequeue() {
        var data = frontNode.getData();
        frontNode = LinkedList.removeAt(frontNode, 0);
        queueSize--;
        return data;
    }

    public int getSize() {
        return queueSize;
    }
}