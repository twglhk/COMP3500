package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        List<String> result = new ArrayList<>();

        if (tasks.length == 0)
            return result;

        HashMap<Task, TaskNode> taskNodeGraphMap = new HashMap<Task, TaskNode>();
        HashMap<TaskNode, Boolean> visitDFSMap = new HashMap<TaskNode, Boolean>();
        ArrayList<TaskNode> dfsTaskNodeList = new ArrayList<>(tasks.length);

        if (tasks[0].getPredecessors().size() != 0) {
            for (int i = 1; i < tasks.length; ++i) {
                if (tasks[i].getPredecessors().size() == 0) {
                    var temp = tasks[0];
                    tasks[0] = tasks[i];
                    tasks[i] = temp;
                }
            }
        }

        TaskNode startNode = new TaskNode(tasks[0]);
        taskNodeGraphMap.put(tasks[0], startNode);
        visitDFSMap.put(startNode, false);
        for (int i = 1; i < tasks.length; ++i) {
            if (!taskNodeGraphMap.containsKey(tasks[i])) {
                TaskNode newTaskNode = new TaskNode(tasks[i]);
                taskNodeGraphMap.put(tasks[i], newTaskNode);
                visitDFSMap.put(newTaskNode, false);
            }

            for (var predecessor : tasks[i].getPredecessors()) {
                TaskNode predecessorTaskNode;
                if (!taskNodeGraphMap.containsKey(predecessor)) {
                    predecessorTaskNode = new TaskNode(predecessor);
                    taskNodeGraphMap.put(predecessor, predecessorTaskNode);
                    visitDFSMap.put(predecessorTaskNode, false);
                } else {
                    predecessorTaskNode = taskNodeGraphMap.get(predecessor);
                }
                predecessorTaskNode.nextTaskList.add(tasks[i]);
            }
        }

        dfsRecursive(startNode, dfsTaskNodeList, taskNodeGraphMap, visitDFSMap);

        for (var visit : visitDFSMap.entrySet()) {
            visit.setValue(false);
        }

        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            if (visitDFSMap.get(dfsTaskNodeList.get(i))) continue;
            dfsTRecursive(dfsTaskNodeList.get(i), taskNodeGraphMap, visitDFSMap);
        }

        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            if (!includeMaintenance) {
                if (dfsTaskNodeList.get(i).isScc) continue;
            }
            result.add(dfsTaskNodeList.get(i).task.getTitle());
        }
        return result;
    }

    private static void dfsRecursive(final TaskNode taskNode,
                                     final ArrayList<TaskNode> dfsTaskList,
                                     final HashMap<Task, TaskNode> taskNodeGraphMap,
                                     final HashMap<TaskNode, Boolean> visitDFSMap) {
        if (visitDFSMap.get(taskNode))
            return;

        visitDFSMap.put(taskNode, true);
        for (var nextTask : taskNode.nextTaskList) {
            dfsRecursive(taskNodeGraphMap.get(nextTask), dfsTaskList, taskNodeGraphMap, visitDFSMap);
        }
        dfsTaskList.add(taskNode);
    }

    private static void dfsTRecursive(final TaskNode taskNode,
                                     final HashMap<Task, TaskNode> taskNodeGraphMap,
                                     final HashMap<TaskNode, Boolean> visitDFSMap) {
        if (visitDFSMap.get(taskNode)) {
            return;
        }

        visitDFSMap.put(taskNode, true);
        for (var beforeTask : taskNode.task.getPredecessors()) {
            if (visitDFSMap.get(taskNodeGraphMap.get(beforeTask))) {
                continue;
            } else {
                taskNode.isScc = true;
                taskNodeGraphMap.get(beforeTask).isScc = true;
                dfsTRecursive(taskNodeGraphMap.get(beforeTask), taskNodeGraphMap, visitDFSMap);
            }
        }
    }
}