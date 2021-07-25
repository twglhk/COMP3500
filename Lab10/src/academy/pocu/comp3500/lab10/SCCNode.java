package academy.pocu.comp3500.lab10;

import java.util.LinkedList;

public class SCCNode {
    public SCCNode nextSCCNode;
    public LinkedList<TaskNode> taskNodes = new LinkedList<TaskNode>();
    public boolean topologicalSortVisit = false;
}
