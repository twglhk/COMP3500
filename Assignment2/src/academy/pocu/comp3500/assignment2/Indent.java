package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.LogBox;
import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import java.util.function.Function;

public final class Indent {
    private int level;
    private LinkedList<Indent> childIndents;
    private boolean isDiscarded;

    public Indent(int level) {
        this.level = level;
        childIndents = new LinkedList<Indent>();
    }

    public int getLevel() {
        return level;
    }

    public boolean getDiscarded() {
        return isDiscarded;
    }

    public void addChildIndent(Indent indent) {
        childIndents.add(indent);
    }

    public void discard() {
        if (level == 0) {
            return;
        }
        isDiscarded = true;
    }

    public void executeDiscard() {
        for (var childIndent : childIndents) {
            childIndent.discard();
        }
    }
}