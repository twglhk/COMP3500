package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.HashMap;

public class TaskNode {
    private final String title;
    private final Task task;
    private final HashMap<TaskNode, Edge> taskNodeEdgeHashMap = new HashMap<TaskNode, Edge>();

    public TaskNode(final Task task, boolean isEnterNode) {
        this.task = task;

        if (isEnterNode)
            title = this.task.getTitle();
        else
            title = this.task.getTitle() + "'";
    }

    public Task getTask() {
        return task;
    }

    public String getTitle() {
        return title;
    }

    public HashMap<TaskNode, Edge> getTaskNodeEdgeHashMap() {
        return taskNodeEdgeHashMap;
    }

    public void addTaskNodeEdge(TaskNode toNode, Edge edge) {
        taskNodeEdgeHashMap.put(toNode, edge);
    }
}
