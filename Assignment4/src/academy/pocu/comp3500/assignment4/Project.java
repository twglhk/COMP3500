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
    HashMap<String, Task> taskNodeGraphMap;
    private HashMap<Task, TaskNode> includeMaintenanceSccHashMap;
    private HashMap<Task, TaskNode> nonIncludeMaintenanceSccHashMap;

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

    private int dfsFindTotalManMonths(String taskTitle,
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
        // 코사라주 돌리면서 순환 scc를 제외하고 estimate 저장해서 다음 노드에 넘겨주기. 해당 노드가 본인에게 들어온 맨먼스 중에서 최대값만 취함
//        HashMap<Task, TaskNode> taskNodeGraphMap = new HashMap<Task, TaskNode>();
//        ArrayList<TaskNode> taskNodeList = kosaraju(tasks, taskNodeGraphMap, false);
//        var result = -1;
//
//        for (var taskNode : taskNodeList) {
//            if (taskNode.task.getTitle().equals(task)) {
//                result = taskNode.getMinDuration();
//                break;
//            }
//
//            for (var nextTaskNode : taskNode.nextTaskNodeList) {
//                nextTaskNode.updateMinDuration(taskNode.getMinDuration());
//            }
//        }
//
        return -1;
    }

    public int findMaxBonusCount(final String task) {
        // 코사라주 돌리면서 각 노드에 들어갈 때 마다 일감을 넘겨 받고 현재 노드의 맨먼스 상한을 쳐서 저장. 다음 노드에 그 상한 값 넘겨주기


        return -1;
    }

    public HashMap<Task, TaskNode> kosaraju(final Task[] tasks,
                                            final HashMap<Task, TaskNode> taskNodeGraphMap,
                                            boolean includeMaintenance) {
        HashMap<Task, TaskNode> result = new HashMap<Task, TaskNode>();

        if (tasks.length == 0)
            return result;

        // 그래프 만들기
        HashMap<TaskNode, Boolean> visitDFSMap = new HashMap<TaskNode, Boolean>();
        ArrayList<TaskNode> dfsTaskNodeList = new ArrayList<>(tasks.length);

        for (int i = 0; i < tasks.length; ++i) {
            TaskNode newTaskNode;
            if (!taskNodeGraphMap.containsKey(tasks[i])) {
                newTaskNode = new TaskNode(tasks[i]);
                taskNodeGraphMap.put(tasks[i], newTaskNode);
            } else {
                newTaskNode = taskNodeGraphMap.get(tasks[i]);
            }
            visitDFSMap.put(newTaskNode, false);

            for (var predecessor : tasks[i].getPredecessors()) {
                TaskNode predecessorTaskNode;
                if (!taskNodeGraphMap.containsKey(predecessor)) {
                    predecessorTaskNode = new TaskNode(predecessor);
                    taskNodeGraphMap.put(predecessor, predecessorTaskNode);
                } else {
                    predecessorTaskNode = taskNodeGraphMap.get(predecessor);
                }
                visitDFSMap.put(predecessorTaskNode, false);
                predecessorTaskNode.nextTaskNodeList.add(taskNodeGraphMap.get(tasks[i]));
            }
        }

        for (var taskNode : taskNodeGraphMap.entrySet()) {
            if (taskNode.getValue().task.getPredecessors().size() == 0) {
                dfsRecursive(taskNode.getValue(), dfsTaskNodeList, taskNodeGraphMap, visitDFSMap);
            }
        }

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

        var sccHashMap = new HashMap<Task, TaskNode>();
        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            if (visitDFSMap.get(dfsTaskNodeList.get(i))) continue;
            dfsTRecursive(dfsTaskNodeList.get(i), sccHashMap, taskNodeGraphMap, visitDFSMap, false);
        }

        for (var taskNode : sccHashMap.entrySet()) {
            if (!includeMaintenance)
                if (taskNode.getValue().isScc) continue;
            result.put(taskNode.getKey(), taskNode.getValue());
        }
        return result;
    }

    private void dfsRecursive(final TaskNode taskNode,
                              final ArrayList<TaskNode> dfsTaskList,
                              final HashMap<Task, TaskNode> taskNodeGraphMap,
                              final HashMap<TaskNode, Boolean> visitDFSMap) {
        if (visitDFSMap.get(taskNode))
            return;

        visitDFSMap.put(taskNode, true);
        for (var nextTaskNode : taskNode.nextTaskNodeList) {
            dfsRecursive(nextTaskNode, dfsTaskList, taskNodeGraphMap, visitDFSMap);
        }
        dfsTaskList.add(taskNode);
    }

    private void dfsTRecursive(final TaskNode taskNode,
                               final HashMap<Task, TaskNode> sccHashMap,
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
                    sccHashMap.put(taskNode.task, taskNode);
                    dfsTRecursive(taskNodeGraphMap.get(beforeTask), sccHashMap, taskNodeGraphMap, visitDFSMap, true);
                    return;
                }
                dfsTRecursive(taskNodeGraphMap.get(beforeTask), sccHashMap, taskNodeGraphMap, visitDFSMap, true);
            }
        }
        sccHashMap.put(taskNode.task, taskNode);
    }
}