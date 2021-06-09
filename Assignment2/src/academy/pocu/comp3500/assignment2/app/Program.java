package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static academy.pocu.comp3500.assignment2.Logger.log;

public class Program {

    public static void main(String[] args) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));
            log("hello");
            log("world");
            log("this is logging at the top level");

            Logger.printTo(writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
