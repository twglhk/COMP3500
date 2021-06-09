package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.Stack;

public final class Indent {
    private Stack<Integer> indentStack = new Stack<Integer>();

    public void discard() {

    }

    public void setIndentNum(Integer number) {
        indentStack.push(number);
    }

    public Integer getIndentNum() {
        return indentStack.peek();
    }
}
