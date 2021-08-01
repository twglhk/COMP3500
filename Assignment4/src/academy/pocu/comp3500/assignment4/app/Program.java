package academy.pocu.comp3500.assignment4.app;

import academy.pocu.comp3500.assignment4.Project;
import academy.pocu.comp3500.assignment4.project.Task;

public class Program {

    public static void main(String[] args) {
//        Task[] tasks = createTasks();
//
//        Project project = new Project(tasks);
//
//        int manMonths1 = project.findTotalManMonths("ms1");
//        assert (manMonths1 == 17);
//
//        int manMonths2 = project.findTotalManMonths("ms2");
//        assert (manMonths2 == 46);
//
//        int minDuration1 = project.findMinDuration("ms1");
//        assert (minDuration1 == 14);
//
//        int minDuration2 = project.findMinDuration("ms2");
//        assert (minDuration2 == 32);
//
//        int bonusCount1 = project.findMaxBonusCount("ms1");
//        assert (bonusCount1 == 6);
//
//        int bonusCount2 = project.findMaxBonusCount("ms2");
//        assert (bonusCount2 == 6);

        Task[] tasks2 = createTasks2();
        Project project2 = new Project(tasks2);
        int bonusCount3 = project2.findMaxBonusCount("12");
        assert (bonusCount3 == 10);
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 5);
        Task c = new Task("C", 3);
        Task d = new Task("D", 2);
        Task e = new Task("E", 1);
        Task f = new Task("F", 2);
        Task g = new Task("G", 6);
        Task h = new Task("H", 8);
        Task i = new Task("I", 2);
        Task j = new Task("J", 4);
        Task k = new Task("K", 2);
        Task l = new Task("L", 8);
        Task m = new Task("M", 7);
        Task n = new Task("N", 1);
        Task o = new Task("O", 1);
        Task p = new Task("P", 6);
        Task ms1 = new Task("ms1", 6);
        Task ms2 = new Task("ms2", 8);

        c.addPredecessor(b);

        ms1.addPredecessor(a, c);

        e.addPredecessor(c);
        f.addPredecessor(g);
        g.addPredecessor(e);

        i.addPredecessor(h);
        j.addPredecessor(ms1);

        k.addPredecessor(j);
        n.addPredecessor(k);
        m.addPredecessor(n);
        l.addPredecessor(m);

        p.addPredecessor(i, j);
        o.addPredecessor(j);

        ms2.addPredecessor(o, p);

        Task[] tasks = new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, ms1, ms2
        };

        return tasks;
    }

    private static Task[] createTasks2() {
        Task t0 = new Task("0", 5);
        Task t1 = new Task("1", 10);
        Task t2 = new Task("2", 2);
        Task t3 = new Task("3", 8);
        Task t4 = new Task("4", 3);
        Task t5 = new Task("5", 6);
        Task t6 = new Task("6", 7);
        Task t7 = new Task("7", 3);
        Task t8 = new Task("8", 5);
        Task t9 = new Task("9", 4);
        Task t10 = new Task("10", 2);
        Task t11 = new Task("11", 3);
        Task t12 = new Task("12", 11);

        t1.addPredecessor(t0);
        t2.addPredecessor(t1);
        t3.addPredecessor(t2);
        t4.addPredecessor(t1);
        t5.addPredecessor(t4);

        t7.addPredecessor(t6);
        t8.addPredecessor(t7);

        t10.addPredecessor(t9);
        t11.addPredecessor(t10);
        t12.addPredecessor(t3, t5, t8, t11);

        Task[] tasks = new Task[]{
                t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12
        };

        return tasks;
    }
}