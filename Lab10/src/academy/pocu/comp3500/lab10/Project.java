package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.*;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        List<String> result = new ArrayList<>();

        if (tasks.length == 0)
            return result;

        HashMap<Task, TaskNode> dfsNodeMap = new HashMap<Task, TaskNode>();
        ArrayList<TaskNode> dfsTaskNodeList = new ArrayList<>(tasks.length);

        for (int i = 0; i < tasks.length; ++i) {
            if (dfsNodeMap.containsKey(tasks[i]))
                continue;
            dfsRecursive(tasks[i], null, dfsTaskNodeList, dfsNodeMap);
        }

        // SCC 컨테이너
        var sccList = new LinkedList<SCCNode>();
        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            var taskNode = dfsTaskNodeList.get(i);
            if (taskNode.sccVisit) continue;
            var sscNode = new SCCNode();
            sccDfsRecursive(taskNode, dfsNodeMap, sscNode);

            if (sscNode.taskNodes.size() > 1) {
                if (!includeMaintenance)
                    continue;
            }
            sccList.addFirst(sscNode);
        }

        for (var sscNode : sccList) {
            for (var taskNode : sscNode.taskNodes) {
                result.add(taskNode.task.getTitle());
            }
        }

        return result;
    }

    private static void dfsRecursive(final Task task,
                                     final Task nextTask,
                                     final ArrayList<TaskNode> dfsTaskList,
                                     final HashMap<Task, TaskNode> dfsNodeMap) {
        if (dfsNodeMap.containsKey(task)) {
            if (nextTask != null) {
                dfsNodeMap.get(task).nextTaskList.add(nextTask);
            }
            return;
        }

        TaskNode taskNode = new TaskNode();
        taskNode.task = task;
        taskNode.sccVisit = false;

        if (nextTask != null)
            taskNode.nextTaskList.add(nextTask);

        dfsNodeMap.put(task, taskNode);

        var predecessorList = task.getPredecessors();
        for (var predecessor : predecessorList) {
            dfsRecursive(predecessor, task, dfsTaskList, dfsNodeMap);
        }
        dfsTaskList.add(taskNode);
    }

    private static void sccDfsRecursive(TaskNode taskNode,
                                        HashMap<Task, TaskNode> dfsNodeMap,
                                        SCCNode sccNode) {
        if (taskNode.sccVisit) {
            return;
        }
        taskNode.sccVisit = true;
        sccNode.taskNodes.addLast(taskNode);
        for (var nextTask : taskNode.nextTaskList) {
            if (dfsNodeMap.get(nextTask).sccVisit)
                continue;
            sccDfsRecursive(dfsNodeMap.get(nextTask), dfsNodeMap, sccNode);
        }
    }
}