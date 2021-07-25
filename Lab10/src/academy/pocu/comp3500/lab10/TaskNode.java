package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.ArrayList;

public class TaskNode {
    public Task task;
    public ArrayList<Task> nextTaskList = new ArrayList<Task>();
    public boolean sccVisit;
}
