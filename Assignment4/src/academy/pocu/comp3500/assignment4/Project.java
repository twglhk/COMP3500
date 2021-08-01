package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;

public final class Project {
    //사용 가능 자료 구조
    //LinkedList
    //ArrayList
    //Stack
    //PriorityQueue
    //Hashmap
    private Task[] tasks;
    private HashMap<String, Task> taskNodeGraphMap;

    public Project(final Task[] tasks) {
        this.tasks = tasks;
        taskNodeGraphMap = new HashMap<String, Task>();
        for (var task : tasks) {
            taskNodeGraphMap.put(task.getTitle(), task);
        }
    }

    public int findTotalManMonths(final String task) {
        HashMap<String, Task> visitTaskNodeHashMap = new HashMap<String, Task>();
        var result = 0;
        result = dfsFindTotalManMonths(task, visitTaskNodeHashMap);
        return result;
    }

    private int dfsFindTotalManMonths(final String taskTitle,
                                      HashMap<String, Task> visitTaskNodeHashMap) {
        var result = 0;
        if (visitTaskNodeHashMap.containsKey(taskTitle))
            return result;

        var task = taskNodeGraphMap.get(taskTitle);
        visitTaskNodeHashMap.put(taskTitle, task);

        for (var predecessorTask : task.getPredecessors()) {
            result += dfsFindTotalManMonths(predecessorTask.getTitle(), visitTaskNodeHashMap);
        }
        return result + task.getEstimate();
    }

    public int findMinDuration(final String task) {
        var result = 0;
        result = dfsFindMinDuration(task);
        return result;
    }

    private int dfsFindMinDuration(final String taskTitle) {
        var minDuration = 0;
        var task = taskNodeGraphMap.get(taskTitle);

        for (var predecessorTask : task.getPredecessors()) {
            var duration = dfsFindMinDuration(predecessorTask.getTitle());
            if (duration > minDuration)
                minDuration = duration;
        }

        return minDuration + task.getEstimate();
    }

    public int findMaxBonusCount(final String task) {
        return -1;
    }
}