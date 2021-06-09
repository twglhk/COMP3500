package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;

import javax.management.QueryEval;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class Logger {
    private static LinkedList<String> logList = new LinkedList<String>();
    private static Indent indent = new Indent();

    public static void log(final String text) {
        logList.add(text);
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            for (var log : logList) {
                writer.write(log);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    public static void clear() {
        logList.clear();
    }

    public static Indent indent() {
        indent.setIndentNum(logList.getSize());
        return indent;
    }

    public static void unindent() {

    }
}