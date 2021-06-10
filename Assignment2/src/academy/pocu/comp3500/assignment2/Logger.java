package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import javax.management.QueryEval;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class Logger {
    private static LinkedList<Indent> indentList = new LinkedList<Indent>();
    private static int indentCursor;

    public static void log(final String text) {
        if (indentList.getSize() == 0) {
            Indent indent = new Indent(0);
            indentList.add(indent);
            indentCursor = 0;
        }
        indentList.get(indentCursor).addLog(text);
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            for (var indent : indentList) {
                var logList = indent.getLogList();
                for (var log : logList) {
                    writer.write(log);
                    writer.newLine();
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    public static void clear() {
        indentList.clear();
    }

    public static Indent indent() {
        if (indentList.getSize() == indentCursor + 1) {
            Indent indent = new Indent(indentList.get(indentCursor).getLevel() + 1);
            indentList.addLast(indent);
            indentCursor++;
            return indent;
        } else {
            return indentList.get(++indentCursor);
        }
    }

    public static void unindent() {
        if (indentList.getSize() == 0)
            return;

        Indent indent = new Indent(indentList.get(indentCursor).getLevel() - 1);
        indentList.addLast(indent);
        indentCursor++;
    }
}