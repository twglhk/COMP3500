package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.LogBox;
import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import java.util.function.Function;

public final class Indent {
    private int level;
    private Indent childIndent;
    private LinkedList<DiscardInterface> logBoxDiscardFuncList;

    public Indent(int level) {
        this.level = level;
        logBoxDiscardFuncList = new LinkedList<DiscardInterface>();
    }

    public int getLevel() {
        return level;
    }

    public void setChildIndent(Indent indent) {
        childIndent = indent;
    }

    public void setDiscardTarget(DiscardInterface func) {
        logBoxDiscardFuncList.add(func);
    }

    public void discard() {
        for (var func : logBoxDiscardFuncList) {
            func.discardLogBox();
        }

        if (childIndent != null) {
            childIndent.discard();
        }
    }

    public interface DiscardInterface {
        void discardLogBox();
    }
}