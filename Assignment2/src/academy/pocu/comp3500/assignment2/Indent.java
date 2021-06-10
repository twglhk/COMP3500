package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

public final class Indent {
    private LinkedList<String> logList;
    private Indent nextIndent;
    private int level;

    public Indent(int level) {
        this.level = level;
        logList = new LinkedList<String>();
    }

    public void addLog(String str) {
        String indentedString = "";
        for (int i = 0; i < level; ++i) {
            indentedString += "   ";
        }
        indentedString += str;
        logList.addLast(indentedString);
    }

    public LinkedList<String> getLogList() {
        return logList;
    }

    public int getLevel() {
        return level;
    }

    public void discard() {
        logList.clear();
    }
}
