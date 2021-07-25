package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SCCNode {
    public int entryIndex;
    public LinkedList<TaskNode> taskNodes = new LinkedList<TaskNode>();

    public void findEntryNode(HashMap<Task, TaskNode> dfsNodeMap)
    {
        for (int i = 0; i < taskNodes.size(); ++i) {
            var predecessors = taskNodes.get(i).task.getPredecessors();
            for (var predecessor : predecessors) {
                if (!taskNodes.contains(dfsNodeMap.get(predecessor))) {
                    entryIndex = i;
                    return;
                }
            }
        }
    }

    public void addString(final List<String> result) {
        for (int i = entryIndex; i < taskNodes.size(); ++i) {
            result.add(taskNodes.get(i).task.getTitle());
        }
        for (int i = 0; i < entryIndex; ++i) {
            result.add(taskNodes.get(i).task.getTitle());
        }
    }
}
