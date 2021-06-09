package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;

import javax.management.QueryEval;
import java.io.BufferedWriter;
import java.io.IOException;

public final class Logger {
    private static LinkedList<String> logList  = new LinkedList<String>();

    public static void log(final String text) {
        logList.add(text);
    }

    public static void printTo(final BufferedWriter writer) throws IOException {
        for (var log : logList) {
            writer.write(log);
            writer.newLine();
        }
        writer.flush();
    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    public static void clear() {
        logList.clear();
    }

    public static Indent indent() {
        return null;
    }

    public static void unindent() {

    }
}