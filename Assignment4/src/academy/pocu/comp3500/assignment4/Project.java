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

    public Project(final Task[] tasks) {
        this.tasks = tasks;
    }

    public int findTotalManMonths(final String task) {
        // 코사라주 돌리면서 순환 scc를 제외하고 estimate 저장해서 다음 노드에 넘겨주기. 해당 노드가  맨먼스 반환
        HashMap<Task, TaskNode> taskNodeGraphMap = new HashMap<Task, TaskNode>();
        ArrayList<TaskNode> taskNodeList = kosaraju(tasks, taskNodeGraphMap, false);
        var result = -1;

        for (var taskNode : taskNodeList) {
//            System.out.print("현재 노드 : " + taskNode.task.getTitle() + " / 현재 맨먼스 : " + taskNode.getEstimate() + " / ");

            if (taskNode.task.getTitle().equals(task)) {
//                System.out.print(" 종료");
                result = taskNode.getEstimate();
//                for (var node : taskNode.addedTaskNodeHashMap.entrySet()) {
//                    System.out.print(node.getKey().task.getTitle() + ",");
//                }
                break;
            }

//            System.out.print("다음 노드 : ");
            for (var nextTaskNode : taskNode.nextTaskNodeList) {
//                System.out.print(nextTaskNode.task.getTitle() + " ");
//                if (nextTaskNode.task.getTitle().equals(task)) {
//                    System.out.print(task + "에 + " + taskNode.getEstimate());
//                }

                nextTaskNode.addTotalManMonthTaskNode(taskNode);
            }
//            System.out.println();
        }
//        System.out.println();
//        System.out.println();

        return result;
    }

    public int findMinDuration(final String task) {
        // 코사라주 돌리면서 순환 scc를 제외하고 estimate 저장해서 다음 노드에 넘겨주기. 해당 노드가 본인에게 들어온 맨먼스 중에서 최대값만 취함
        HashMap<Task, TaskNode> taskNodeGraphMap = new HashMap<Task, TaskNode>();
        ArrayList<TaskNode> taskNodeList = kosaraju(tasks, taskNodeGraphMap, false);
        var result = -1;

        for (var taskNode : taskNodeList) {
            if (taskNode.task.getTitle().equals(task)) {
                result = taskNode.getMinDuration();
                break;
            }

            for (var nextTaskNode : taskNode.nextTaskNodeList) {
                nextTaskNode.updateMinDuration(taskNode.getMinDuration());
            }
        }

        return result;
    }

    public int findMaxBonusCount(final String task) {
        // 코사라주 돌리면서 각 노드에 들어갈 때 마다 일감을 넘겨 받고 현재 노드의 맨먼스 상한을 쳐서 저장. 다음 노드에 그 상한 값 넘겨주기


        return -1;
    }

    public static ArrayList<TaskNode> kosaraju(final Task[] tasks,
                                               final HashMap<Task, TaskNode> taskNodeGraphMap,
                                               boolean includeMaintenance) {
        ArrayList<TaskNode> result = new ArrayList<TaskNode>(tasks.length);

        if (tasks.length == 0)
            return result;

        // 그래프 만들기
        HashMap<TaskNode, Boolean> visitDFSMap = new HashMap<TaskNode, Boolean>();
        ArrayList<TaskNode> dfsTaskNodeList = new ArrayList<>(tasks.length);

        for (int i = 0; i < tasks.length; ++i) {
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

        var sccList = new ArrayList<TaskNode>();
        for (int i = dfsTaskNodeList.size() - 1; i >= 0; --i) {
            if (visitDFSMap.get(dfsTaskNodeList.get(i))) continue;
            dfsTRecursive(dfsTaskNodeList.get(i), sccList, taskNodeGraphMap, visitDFSMap, false);
        }

        for (int i = 0; i < sccList.size(); ++i) {
            if (!includeMaintenance)
                if (sccList.get(i).isScc) continue;
            result.add(sccList.get(i));
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
        for (var nextTaskNode : taskNode.nextTaskNodeList) {
            dfsRecursive(nextTaskNode, dfsTaskList, taskNodeGraphMap, visitDFSMap);
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