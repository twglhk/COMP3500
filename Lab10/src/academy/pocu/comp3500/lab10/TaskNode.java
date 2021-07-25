package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskNode {
    public Task task;
    public ArrayList<Task> nextTaskList = new ArrayList<Task>();
    public HashMap<Task, Boolean> visitPredecessors = new HashMap<Task, Boolean>();
    public boolean sccVisit;

    public TaskNode(Task task) {
        this.task = task;
        for (var predecessor : task.getPredecessors()) {
            visitPredecessors.put(predecessor, false);
        }
    }

    public void visit(Task task) {
        if (task == null) {
            sccVisit = true;
        }

        visitPredecessors.put(task, true);
        for(var visitor : visitPredecessors.entrySet()) {
            if (!visitor.getValue())
                return;
        }
        sccVisit = true;
    }
}
