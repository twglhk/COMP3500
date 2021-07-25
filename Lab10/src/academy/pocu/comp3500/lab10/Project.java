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

//        System.out.println();
//        System.out.println("After DFS");
//        for (var taskNode : dfsTaskNodeList) {
//            System.out.print(taskNode.task.getTitle() + " ");
//        }

        // SCC 컨테이너
        var sccList = new LinkedList<SCCNode>();
        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            var taskNode = dfsTaskNodeList.get(i);
            if (taskNode.sccVisit) continue;

            var sccNode = new SCCNode();
            sccNode.taskNodes.addLast(taskNode);
            taskNode.sccVisit = true;
            sccInsert(taskNode, dfsNodeMap, sccNode);
            sccList.addFirst(sccNode);
        }

        for (var sscNode : sccList) {
            if (sscNode.taskNodes.size() > 1) {
                if (!includeMaintenance) continue;
                sscNode.findEntryNode(dfsNodeMap);
                sscNode.addString(result);
                continue;
            }

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
        dfsNodeMap.put(task, taskNode);

        if (nextTask != null)
            taskNode.nextTaskList.add(nextTask);

        var predecessorList = task.getPredecessors();
        for (var predecessor : predecessorList) {
            dfsRecursive(predecessor, task, dfsTaskList, dfsNodeMap);
        }
        dfsTaskList.add(taskNode);
    }

    private static void sccInsert(TaskNode taskNode,
                                  HashMap<Task, TaskNode> dfsNodeMap,
                                  SCCNode sccNode) {
//        Queue<TaskNode> taskNodeQueue = new LinkedList<TaskNode>();
//        taskNodeQueue.add(taskNode);
//        while (taskNodeQueue.size() != 0) {
//            var currentNode = taskNodeQueue.poll();
//            for (var nextTask : currentNode.nextTaskList) {
//                if (dfsNodeMap.get(nextTask).sccVisit)
//                    continue;
//                sccNode.taskNodes.addLast(dfsNodeMap.get(nextTask));
//                dfsNodeMap.get(nextTask).sccVisit = true;
//                taskNodeQueue.add(dfsNodeMap.get(nextTask));
//            }
//        }

        Stack<TaskNode> taskNodeStack = new Stack<TaskNode>();
        taskNodeStack.push(taskNode);
        while (taskNodeStack.size() != 0) {
            var currentNode = taskNodeStack.pop();
            for (var nextTask : currentNode.nextTaskList) {
                if (dfsNodeMap.get(nextTask).sccVisit)
                    continue;
                sccNode.taskNodes.addLast(dfsNodeMap.get(nextTask));
                dfsNodeMap.get(nextTask).sccVisit = true;
                taskNodeStack.push(dfsNodeMap.get(nextTask));
            }
        }
    }
}