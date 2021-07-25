package academy.pocu.comp3500.lab10.app;

import academy.pocu.comp3500.lab10.Project;
import academy.pocu.comp3500.lab10.project.Task;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        {
            Task a = new Task("A", 12);
            Task b = new Task("B", 7);
            Task c = new Task("C", 10);
            Task d = new Task("D", 9);
            Task e = new Task("E", 8);
            Task f = new Task("F", 11);
            Task g = new Task("G", 14);
            Task h = new Task("H", 13);
            Task i = new Task("I", 6);
            Task j = new Task("J", 5);
            Task k = new Task("K", 5);
            Task l = new Task("L", 5);

            b.addPredecessor(a,d);
            c.addPredecessor(b);
            d.addPredecessor(c);
            e.addPredecessor(a,g);
            f.addPredecessor(e);
            g.addPredecessor(f,h);
            h.addPredecessor(f);
            i.addPredecessor(a,j);


            Task[] tasks = new Task[]{ c,g,a,b,e,i,h,f,d,j };

            List<String> schedule = Project.findSchedule(tasks, true);
//            for (var str : schedule) {
//                System.out.print(str + " ");
//            } System.out.println();
        }

        {
            Task a = new Task("A", 12);
            Task b = new Task("B", 7);
            Task c = new Task("C", 10);
            Task d = new Task("D", 9);
            Task e = new Task("E", 8);
            Task f = new Task("F", 11);
            Task g = new Task("G", 14);
            Task h = new Task("H", 13);
            Task i = new Task("I", 6);
            Task j = new Task("J", 5);
            Task k = new Task("K", 5);
            Task l = new Task("L", 5);

            j.addPredecessor(f, k);
            k.addPredecessor(l);
            l.addPredecessor(j);
            i.addPredecessor(f);
            f.addPredecessor(e);
            e.addPredecessor(b, c);
            d.addPredecessor(c, h);
            c.addPredecessor(b);
            b.addPredecessor(a);
            h.addPredecessor(g);
            g.addPredecessor(d);

            Task[] tasks = new Task[]{ a, b, c, d, e, f, g, h, i, j, k, l };

            List<String> schedule = Project.findSchedule(tasks, true);
//            for (var str : schedule) {
//                System.out.print(str + " ");
//            } System.out.println();
        }

        {
            Task a = new Task("A", 12);
            Task b = new Task("B", 7);
            Task c = new Task("C", 10);
            Task d = new Task("D", 9);
            Task e = new Task("E", 8);
            Task f = new Task("F", 11);
            Task g = new Task("G", 14);
            b.addPredecessor(a);
            c.addPredecessor(b, e);
            d.addPredecessor(c);
            e.addPredecessor(d);
            f.addPredecessor(a);
            g.addPredecessor(b, f);
            Task[] tasks = new Task[]{a, b, c, d, e, f, g};
            for (int i = 0; i < 10; ++i) {
                Collections.shuffle(Arrays.asList(tasks));
                List<String> schedule = Project.findSchedule(tasks, true);
                assert (schedule.size() == 7);
                assert (schedule.get(0).equals("A"));
                assert (schedule.indexOf("A") < schedule.indexOf("B"));
                assert (schedule.indexOf("A") < schedule.indexOf("F"));
                assert (schedule.indexOf("B") < schedule.indexOf("G"));
                assert (schedule.indexOf("F") < schedule.indexOf("G"));
                assert (schedule.indexOf("B") < schedule.indexOf("C"));
                assert (schedule.indexOf("C") < schedule.indexOf("D"));
                assert (schedule.indexOf("D") < schedule.indexOf("E"));
            }
        }

        {
            Task a = new Task("A", 12);
            Task b = new Task("B", 7);
            Task c = new Task("C", 10);
            Task d = new Task("D", 9);
            Task e = new Task("E", 8);
            Task f = new Task("F", 11);

            b.addPredecessor(a, e);
            c.addPredecessor(b);
            d.addPredecessor(c);
            e.addPredecessor(d, f);
            f.addPredecessor(c);

            Task[] tasks = new Task[]{ a, b, c, d, e, f };

            List<String> schedule = Project.findSchedule(tasks, true);
//            System.out.println("Test Case by fergcd");
//            for (String title : schedule) {
//                System.out.println(title);
//            }

            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("C") < schedule.indexOf("F"));
            assert (schedule.indexOf("D") < schedule.indexOf("E"));
            assert (schedule.indexOf("F") < schedule.indexOf("E"));
        }

        {
            Task a = new Task("A", 15);
            Task b = new Task("B", 12);
            Task c = new Task("C", 11);

            c.addPredecessor(b);
            b.addPredecessor(a);

            Task[] tasks = new Task[]{b, c, a};

            List<String> schedule = Project.findSchedule(tasks, false);

            assert (schedule.size() == 3);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
        }

        {
            Task[] tasks = createTasks();

            List<String> schedule = Project.findSchedule(tasks, false);
//            for (var str : schedule) {
//                System.out.print(str + " ");
//            } System.out.println();
            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.get(3).equals("E"));
            assert (schedule.get(4).equals("F"));
            assert (schedule.get(5).equals("I"));
        }

        {
            Task[] tasks = createTasks();

            List<String> schedule = Project.findSchedule(tasks, true);

            assert (schedule.size() == 9);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.indexOf("C") < schedule.indexOf("E"));
            assert (schedule.indexOf("E") < schedule.indexOf("F"));
            assert (schedule.indexOf("F") < schedule.indexOf("I"));

            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("D") < schedule.indexOf("G"));
            assert (schedule.indexOf("G") < schedule.indexOf("H"));
        }
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);

        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, h);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);

        return new Task[]{
                a, b, c, d, e, f, g, h, i
        };
    }
}