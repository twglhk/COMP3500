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
        for (int i = 1; i < tasks.length; ++i) {
            if (visitDFSMap.get(taskNodeGraphMap.get(tasks[i]))) continue;
            dfsRecursive(taskNodeGraphMap.get(tasks[i]), dfsTaskNodeList, taskNodeGraphMap, visitDFSMap);
        }

//        for (var task : dfsTaskNodeList) {
//            System.out.print(task.task.getTitle() + " ");
//        } System.out.println();

        for (var visit : visitDFSMap.entrySet()) {
            visit.setValue(false);
        }

        var sccList = new ArrayList<TaskNode>();
        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            if (visitDFSMap.get(dfsTaskNodeList.get(i))) continue;
            dfsTRecursive(dfsTaskNodeList.get(i), sccList, taskNodeGraphMap, visitDFSMap, false);
        }

        for (int i = 0; i < sccList.size(); ++i) {
            if (!includeMaintenance) {
                if (sccList.get(i).isScc) continue;
            }
            result.add(sccList.get(i).task.getTitle());
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
                                      final ArrayList<TaskNode> sccList,
                                      final HashMap<Task, TaskNode> taskNodeGraphMap,
                                      final HashMap<TaskNode, Boolean> visitDFSMap,
                                      boolean isSccStart) {

        visitDFSMap.put(taskNode, true);
        for (var beforeTask : taskNode.task.getPredecessors()) {
            var beforeTaskNode = taskNodeGraphMap.get(beforeTask);
            if (visitDFSMap.get(beforeTaskNode)) {
                continue;
            } else {
                taskNode.isScc = true;
                beforeTaskNode.isScc = true;
                if (!isSccStart) {
                    sccList.add(taskNode);
                    dfsTRecursive(taskNodeGraphMap.get(beforeTask), sccList, taskNodeGraphMap, visitDFSMap, true);
                    return;
                }
                dfsTRecursive(taskNodeGraphMap.get(beforeTask), sccList, taskNodeGraphMap, visitDFSMap, true);
            }
        }
        sccList.add(taskNode);
    }
}