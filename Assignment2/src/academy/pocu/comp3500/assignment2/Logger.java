package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.app.LogBox;
import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import javax.management.QueryEval;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class Logger {
    private static LinkedList<Indent> indentList = new LinkedList<Indent>();
    private static LinkedList<LogBox> logBoxList = new LinkedList<LogBox>();
    private static int indentLevel = 0;

    public static void log(final String text) {
        if (indentList.getSize() == 0) {
            Indent indent = new Indent(0);
            indentList.add(indent);
        }

        var logBox = new LogBox();
        var indent = indentList.get(indentLevel);
        logBox.setLog(text);
        logBox.setIndent(indent);
        Indent.DiscardInterface discardFunc = () -> { logBoxList.remove(logBox); };
        indent.setDiscardTarget(discardFunc);
        logBoxList.addLast(logBox);
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            for (var logBox : logBoxList) {
                var indentLevel = logBox.getIndent().getLevel();
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
    }

    public static Indent indent() {
        if (indentList.getSize() == 0) {
            Indent indent = new Indent(0);
            indentList.add(indent);
        }

        indentLevel++;
        if (indentLevel == indentList.getSize()) {
            var indent = new Indent(indentLevel);
            indentList.getLast().setChildIndent(indent);
            indentList.addLast(indent);
            return indent;
        } else {
            return indentList.get(indentLevel);
        }
    }

    public static void unindent() {
        if (indentList.getSize() == 0)
            return;
        if (indentLevel == 0)
            return;
        indentLevel--;
    }
}