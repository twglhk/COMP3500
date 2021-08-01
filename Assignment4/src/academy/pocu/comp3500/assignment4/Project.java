package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.HashMap;
import java.util.LinkedList;

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
        if (tasks.length == 1) {
            return tasks[0].getEstimate();
        }

        LinkedList<TaskNode> startTaskNodeList = new LinkedList<TaskNode>();
        HashMap<Task, TaskNode> enterTaskNodeHashMap = new HashMap<Task, TaskNode>();
        HashMap<Task, TaskNode> exitTaskNodeHashMap = new HashMap<Task, TaskNode>();
        createEdge(startTaskNodeList, enterTaskNodeHashMap, exitTaskNodeHashMap);

//        for (var taskEdgePair : taskEdgeListHashMap.entrySet()) {
//            System.out.print(taskEdgePair.getKey().getTitle() + " => ");
//            for (var edge : taskEdgePair.getValue()) {
//                if (edge.isBackEdge) continue;
//                System.out.print(edge.getTaskTo().getTitle() + ",");
//            }
//            System.out.println();
//        }

        for (var startTaskNode : startTaskNodeList) {
            if (startTaskNode.getTitle().equals(task))
                return startTaskNode.getTask().getEstimate();
        }

        var endTaskNode = enterTaskNodeHashMap.get(taskNodeGraphMap.get(task));
        return bfsFindMaxBonusCount(startTaskNodeList, endTaskNode);
    }

    private void createEdge(LinkedList<TaskNode> startTaskNodeList,
                            HashMap<Task, TaskNode> enterTaskNodeHashMap,
                            HashMap<Task, TaskNode> exitTaskNodeHashMap) {
        for (var task : tasks) {
            TaskNode enterNode = new TaskNode(task, true);
            TaskNode exitNode = new TaskNode(task, false);

            var edge = new Edge(enterNode, exitNode, false);
            var backEdge = new Edge(exitNode, enterNode, true);
            edge.setSymmetricEdge(backEdge);
            backEdge.setSymmetricEdge(edge);

            enterNode.addTaskNodeEdge(exitNode, edge);
            exitNode.addTaskNodeEdge(enterNode, backEdge);
            enterTaskNodeHashMap.put(task, enterNode);
            exitTaskNodeHashMap.put(task, exitNode);
        }

        for (var task : tasks) {
            var predecessors = task.getPredecessors();
            var myEnterNode = enterTaskNodeHashMap.get(task);

            if (predecessors.size() == 0) {
                startTaskNodeList.add(myEnterNode);
                continue;
            }

            for (var predecessor : predecessors) {
                var predecessorTaskNode = exitTaskNodeHashMap.get(predecessor);
                var edge = new Edge(predecessorTaskNode, myEnterNode, false);
                var backEdge = new Edge(myEnterNode, predecessorTaskNode, true);

                edge.setSymmetricEdge(backEdge);
                backEdge.setSymmetricEdge(edge);
                predecessorTaskNode.addTaskNodeEdge(myEnterNode, edge);
                myEnterNode.addTaskNodeEdge(predecessorTaskNode, backEdge);
            }
        }
    }

    private int bfsFindMaxBonusCount(LinkedList<TaskNode> startTaskNodeList,
                                     final TaskNode endTaskNode) {
        LinkedList<TaskNode> queue = new LinkedList<TaskNode>();
        HashMap<TaskNode, TaskNode> pathHashMap = new HashMap<TaskNode, TaskNode>();
        HashMap<TaskNode, String> visitHashMap = new HashMap<TaskNode, String>();

        for (var startTaskNode : startTaskNodeList) {
            queue.add(startTaskNode);
            while (queue.size() != 0) {
                var currentTaskNode = queue.removeFirst();
                if (currentTaskNode == endTaskNode) {
                    // 최소 유량 구하기
                    var minBonusCapacity = Integer.MAX_VALUE;
                    var tempTaskNode = endTaskNode;
//                    System.out.print("BFS 경로 : " + tempTaskNode.getTitle() + " <= ");
                    while (tempTaskNode != startTaskNode) {
                        var nextTaskNode = pathHashMap.get(tempTaskNode);
                        var currentBonusCapacity = nextTaskNode.getTaskNodeEdgeHashMap().get(tempTaskNode).getBonusCapacity();
                        if (minBonusCapacity > currentBonusCapacity) {
                            minBonusCapacity = currentBonusCapacity;
                        }
                        tempTaskNode = nextTaskNode;

//                        System.out.print(tempTaskNode.getTitle() + " <= ");
                    }
//                    System.out.print("최대 유량 : " + minBonusCapacity);
//                    System.out.println();

                    // 유량 더하기
                    tempTaskNode = endTaskNode;
                    while (tempTaskNode != startTaskNode) {
                        var nextTaskNode = pathHashMap.get(tempTaskNode);
                        nextTaskNode.getTaskNodeEdgeHashMap().get(tempTaskNode).updateBonusCapacity(minBonusCapacity);
                        tempTaskNode = nextTaskNode;
                    }

                    queue.clear();
                    visitHashMap.clear();
                    queue.add(startTaskNode);
                    continue;
                }

                // 경로 찾기
                if (visitHashMap.containsKey(currentTaskNode)) continue;
                visitHashMap.put(currentTaskNode, currentTaskNode.getTitle());

                for (var taskNodeEdgeKeyPair : currentTaskNode.getTaskNodeEdgeHashMap().entrySet()) {
                    if (taskNodeEdgeKeyPair.getValue().isBonusCapacityMax()) continue;
                    if (visitHashMap.containsKey(taskNodeEdgeKeyPair.getKey())) continue;
                    queue.addLast(taskNodeEdgeKeyPair.getKey());
                    pathHashMap.put(taskNodeEdgeKeyPair.getKey(), currentTaskNode);
                }
            }

            queue.clear();
            visitHashMap.clear();
            pathHashMap.clear();
        }

        var result = 0;
        for (var taskNodeEdgeKeyPair : endTaskNode.getTaskNodeEdgeHashMap().entrySet()) {
            result += taskNodeEdgeKeyPair.getValue().getFinalBonusCapacity();
        }

        if (result > endTaskNode.getTask().getEstimate()) {
            result = endTaskNode.getTask().getEstimate();
        }

        return result;
    }
}