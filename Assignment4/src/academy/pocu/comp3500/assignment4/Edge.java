package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

public final class Edge {
    private final int maxBonusCapacity;
    private int currentBonusCapacity = 0;
    private Edge symmetricEdge;
    public boolean isBackEdge;

    public Edge(final TaskNode taskNodeFrom, final TaskNode taskNodeTo, boolean isBackEdge) {
        this.isBackEdge = isBackEdge;

        var fromNodeEstimate = taskNodeFrom.getTask().getEstimate();
        var toNodeEstimate = taskNodeTo.getTask().getEstimate();

        if (!isBackEdge)
            maxBonusCapacity = Math.min(fromNodeEstimate, toNodeEstimate);
        else
            maxBonusCapacity = 0;
    }

    public int getBonusCapacity() {
        return this.maxBonusCapacity - this.currentBonusCapacity;
    }

    public int getFinalBonusCapacity() {
        if (!isBackEdge) return 0;
        return -currentBonusCapacity;
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

