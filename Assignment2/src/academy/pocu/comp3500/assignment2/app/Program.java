package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;
import academy.pocu.comp3500.assignment2.datastructure.Sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static academy.pocu.comp3500.assignment2.Logger.log;

public class Program {

    public static void main(String[] args) throws IOException {
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));
            log("hello");
            Indent indent = Logger.indent();
            {
                log("world");
                indent.discard();
            }
            Logger.unindent();
            log("this is logging at the top level");
            //Logger.printTo(writer);
            //Logger.clear();
            Logger.printTo(writer);
        }

        {
            final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));
            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};
            Sort.quickSort(nums);
            Logger.printTo(writer1);
            // quicksort1.log
            /*
            30 10 80 90 50 70 40
              L: 30 10 40
                L: 10
                R: 30
                X: 10 30
              R: 90 50 70 80
                L: 50 70 80
                  L: 50 70
                  R:
                  X: 50 70
                R: 90
                X: 50 70 80 90
              X: 10 30 40 50 70 80 90
            */
        }
    }
}
