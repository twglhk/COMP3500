package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskNode {
    public Task task;
    public boolean isScc = false;
    public ArrayList<TaskNode> nextTaskNodeList = new ArrayList<TaskNode>();
    public HashMap<TaskNode, Integer> addedTaskNodeHashMap = new HashMap<TaskNode, Integer>();
    public int minDuration;

    public TaskNode(Task task) {
        this.task = task;
        minDuration = 0;
        addedTaskNodeHashMap.put(this, task.getEstimate());
    }

    public int getEstimate() {
        var result = 0;
        for (var taskNode : addedTaskNodeHashMap.entrySet()) {
            result += taskNode.getValue();
        }
        return result;
    }

    public int getMinDuration() {
        return minDuration + task.getEstimate();
    }

    public void addTotalManMonthTaskNode(TaskNode node) {
        for (var addedNode : node.addedTaskNodeHashMap.entrySet()) {
            if (addedTaskNodeHashMap.containsKey(addedNode.getKey())) continue;
            addedTaskNodeHashMap.put(addedNode.getKey(), addedNode.getValue());
        }
    }

    public void updateMinDuration(int duration) {
        if (minDuration > duration) return;
        minDuration = duration;
    }
}
