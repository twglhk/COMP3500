package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import javax.naming.InterruptedNamingException;

public final class Edge {
    private final Task taskFrom;
    private final Task taskTo;
    private final int maxBonusCapacity;
    private int currentBonusCapacity;
    private Edge symmetricEdge;

    public Edge(final Task taskFrom, final Task taskTo, boolean isBackEdge) {
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;

        if (!isBackEdge) {
            this.maxBonusCapacity = taskFrom.getEstimate();
        } else {
            this.maxBonusCapacity = 0;
        }
        this.currentBonusCapacity = 0;
    }

    public Task getTaskFrom() {
        return this.taskFrom;
    }

    public Task getTaskTo() {
        return this.taskTo;
    }

    public int getBonusCapacity() {
        return this.maxBonusCapacity - this.currentBonusCapacity;
    }

    public void setSymmetricEdge(Edge symmetricEdge) {
        this.symmetricEdge = symmetricEdge;
    }

    public void updateBonusCapacity(int bonus) {
        currentBonusCapacity += bonus;
        symmetricEdge.updateSymmetricEdgeBonusCapacity(bonus);
    }

    public void updateSymmetricEdgeBonusCapacity(int bonus) {
        currentBonusCapacity -= bonus;
    }

    public boolean isBonusCapacityMax() {
        return currentBonusCapacity == maxBonusCapacity;
    }
}

