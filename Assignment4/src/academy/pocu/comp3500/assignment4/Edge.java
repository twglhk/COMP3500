package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

public final class Edge {
    private final Task taskFrom;
    private final Task taskTo;
    private final int maxBonusCapacity;
    private int currentBonusCapacity;

    public Edge(final Task taskFrom, final Task taskTo) {
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
        this.maxBonusCapacity = taskFrom.getEstimate();
        this.currentBonusCapacity = 0;
    }

    public Task getTaskFrom() {
        return this.taskFrom;
    }

    public Task getTaskTo() {
        return this.taskTo;
    }

    public int getMaxBonusCapacity() {
        return this.maxBonusCapacity;
    }

    public int getCurrentBonusCapacity() {
        return this.currentBonusCapacity;
    }

    public void addCurrentBonusCapacity(int bonus) {
        currentBonusCapacity += bonus;
    }
}

