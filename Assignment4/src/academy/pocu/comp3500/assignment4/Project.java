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

        HashMap<Task, LinkedList<Edge>> taskEdgeListHashMap = new HashMap<Task, LinkedList<Edge>>();
        LinkedList<Task> startTaskList = new LinkedList<Task>();
        createEdge(taskEdgeListHashMap, startTaskList);

//        for (var taskEdgePair : taskEdgeListHashMap.entrySet()) {
//            System.out.print(taskEdgePair.getKey().getTitle() + " => ");
//            for (var edge : taskEdgePair.getValue()) {
//                if (edge.isBackEdge) continue;
//                System.out.print(edge.getTaskTo().getTitle() + ",");
//            }
//            System.out.println();
//        }

        return bfsFindMaxBonusCount(taskEdgeListHashMap, startTaskList, taskNodeGraphMap.get(task));
    }

    private void createEdge(HashMap<Task, LinkedList<Edge>> taskEdgeListHashMap,
                            LinkedList<Task> startTaskList) {
        for (var task : tasks) {
            var predecessors = task.getPredecessors();
            if (predecessors.size() == 0) {
                startTaskList.add(task);
                continue;
            }

            for (var predecessor : predecessors) {
                var edge = new Edge(predecessor, task, false);
                var backEdge = new Edge(task, predecessor, true);

                edge.setSymmetricEdge(backEdge);
                backEdge.setSymmetricEdge(edge);

                LinkedList<Edge> predecessorEdgeList;
                if (taskEdgeListHashMap.containsKey(predecessor)) {
                    predecessorEdgeList = taskEdgeListHashMap.get(predecessor);
                } else {
                    predecessorEdgeList = new LinkedList<Edge>();
                    taskEdgeListHashMap.put(predecessor, predecessorEdgeList);
                }
                predecessorEdgeList.add(edge);


                LinkedList<Edge> myEdgeList;
                if (taskEdgeListHashMap.containsKey(task)) {
                    myEdgeList = taskEdgeListHashMap.get(task);
                } else {
                    myEdgeList = new LinkedList<Edge>();
                    taskEdgeListHashMap.put(task, myEdgeList);
                }
                myEdgeList.add(backEdge);
            }
        }
    }

    private int bfsFindMaxBonusCount(HashMap<Task, LinkedList<Edge>> taskEdgeListHashMap,
                                     LinkedList<Task> startTaskList,
                                     final Task endTask) {
        LinkedList<Task> queue = new LinkedList<Task>();
        HashMap<Task, Edge> pathHashMap = new HashMap<Task, Edge>();
        HashMap<Task, Task> visitHashMap = new HashMap<Task, Task>();
        for (var startTask : startTaskList) {
            queue.add(startTask);
            while (queue.size() != 0) {
                var task = queue.removeFirst();
                if (task == endTask) {
                    // 최소 유량 구하기
                    var minBonusCapacity = Integer.MAX_VALUE;
                    var currentTask = endTask;
//                    System.out.print("BFS 경로 : " + endTask.getTitle() + " <= ");
                    while (currentTask != startTask) {
                        var edge = pathHashMap.get(currentTask);
                        var currentBonusCapacity = edge.getBonusCapacity();
                        if (minBonusCapacity > currentBonusCapacity) {
                            minBonusCapacity = currentBonusCapacity;
                        }
                        currentTask = edge.getTaskFrom();

//                        System.out.print(edge.getTaskFrom().getTitle() + " <= ");
                    }
//                    System.out.print("최대 유량 : " + minBonusCapacity);
//                    System.out.println();

                    // 유량 더하기
                    currentTask = endTask;
                    while (currentTask != startTask) {
                        var edge = pathHashMap.get(currentTask);
                        edge.updateBonusCapacity(minBonusCapacity);
                        currentTask = edge.getTaskFrom();
                    }

                    queue.clear();
                    visitHashMap.clear();
                    queue.add(startTask);
                    continue;
                }

                // 경로 찾기
                if (visitHashMap.containsKey(task)) continue;
                if (!taskEdgeListHashMap.containsKey(task)) continue;

                visitHashMap.put(task, task);

                var edgeList = taskEdgeListHashMap.get(task);
                for (var edge : edgeList) {
                    if (edge.isBonusCapacityMax()) continue;
                    if (visitHashMap.containsKey(edge.getTaskTo())) continue;
                    if (task != edge.getTaskFrom()) continue;
                    queue.addLast(edge.getTaskTo());
                    pathHashMap.put(edge.getTaskTo(), edge);
                }
            }

            queue.clear();
            visitHashMap.clear();
            pathHashMap.clear();
        }

        var result = 0;
        for (var edge : taskEdgeListHashMap.get(endTask)) {
            result += edge.getFinalBonusCapacity();
        }

        if (result > endTask.getEstimate()) {
            result = endTask.getEstimate();
        }

        return result;
    }
}