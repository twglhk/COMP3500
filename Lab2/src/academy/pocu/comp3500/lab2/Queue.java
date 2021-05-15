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
        backNode = LinkedList.append(backNode, data);
        if (frontNode == null) {
            frontNode = backNode;
        }
        if (backNode.getNextOrNull() != null)
            backNode = backNode.getNextOrNull();
        queueSize++;
    }

    public int peek() {
        return frontNode.getData();
    }

    public int dequeue() {
        var data = frontNode.getData();
        frontNode = LinkedList.removeAt(frontNode, 0);
        queueSize--;
        if (queueSize == 0)
            backNode = null;
        return data;
    }

    public int getSize() {
        return queueSize;
    }
}