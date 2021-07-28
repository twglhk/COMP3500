package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.HashMap;

public class DisjointSet {
    private class SetNode {
        private Point parent;
        private int size;

        public SetNode(final Point parent, final int size) {
            this.parent = parent;
            this.size = size;
        }
    }

    private final HashMap<Point, SetNode> sets = new HashMap<Point, SetNode>(64);

    public DisjointSet(final Point[] nodes) {
        for (var point : nodes) {
            SetNode setNode = new SetNode(point, 1);
            this.sets.put(point, setNode);
        }
    }

    // 인자로 들어온 노드가 속해 있는 집합의 루트를 반환
    public Point find(final Point node) {
        assert (this.sets.containsKey(node));

        SetNode n = this.sets.get(node);
        Point parent = n.parent;
        if (parent.equals(node)) {	// 여기서 루트는 자기 자신을 가리키고 있으면 됨
            return node;
        }

        n.parent = find(n.parent);

        return n.parent;
    }

    // 두 집합을 합침. node1이 속해 있는 집합과 node2가 속해 있는 집합
    public void union(final Point node1, final Point node2) {
        assert (this.sets.containsKey(node1));
        assert (this.sets.containsKey(node2));

        Point root1 = find(node1);
        Point root2 = find(node2);

        // 두 루트가 같다면 node1과 node2가 같은 집합이라는 의미
        if (root1.equals(root2)) {
            return;
        }

        SetNode parent = this.sets.get(root1);
        SetNode child = this.sets.get(root2);

        // 자손이 더 많은 루트를 다른 루트의 부모로 만듦
        if (parent.size < child.size) {
            SetNode temp = parent;

            parent = child;
            child = temp;
        }

        // 트리가 너무 높아지는 경우를 방지
        child.parent = parent.parent;
        parent.size = child.size + parent.size;
    }
}