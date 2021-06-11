package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.LogBox;
import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import javax.management.QueryEval;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class Logger {
    private static Stack<Indent> indentStack = new Stack<Indent>();
    private static LinkedList<LogBox> logBoxList = new LinkedList<LogBox>();
    private static int indentLevel = 0;

    public static void log(final String text) {
        if (indentStack.getSize() == 0) {
            Indent indent = new Indent(0);
            indentStack.push(indent);
        }

        var logBox = new LogBox();
        var indent = indentStack.peek();
        logBox.setLog(text);
        logBox.setIndent(indent);
        logBoxList.addLast(logBox);
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            for (var logBox : logBoxList) {
                var indent = logBox.getIndent();
                if (indent.getDiscarded()) {
                    indent.executeDiscard();
                    continue;
                }

                var indentLevel = indent.getLevel();
                String indentedString = "";
                for (int i = 0; i < indentLevel; ++i) {
                    indentedString += "  ";
                }
                indentedString += logBox.getLog();
                writer.write(indentedString);
                writer.newLine();
            }
            writer.flush();
            clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    public static void clear() {
        logBoxList.clear();
        indentLevel = 0;
        while (indentStack.getSize() != 0) {
            indentStack.pop();
        }
    }

    public static Indent indent() {
        if (indentStack.getSize() == 0) {
            Indent indent = new Indent(0);
            indentStack.push(indent);
        }

        indentLevel++;
        var indent = new Indent(indentLevel);
        indentStack.peek().addChildIndent(indent);
        indentStack.push(indent);

        return indent;
    }

    public static void unindent() {
        if (indentStack.getSize() == 0)
            return;
        if (indentLevel == 0)
            return;
        indentLevel--;
        indentStack.pop();
    }
}