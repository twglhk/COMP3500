package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//LinkedList
//ArrayList
//Stack
//PriorityQueue
//Hashmap
//java.util.Arrays, java.util.Collections에서는 sort() 메서드만 사용할 수 있습니다.

public class BallBoy {
    public static List<Point> findPath(final Point[] points) {
        //이 메서드는 다음의 인자를 받습니다.
        //주워야 하는 골프공의 위치들: Point[] points
        //적당히 짧은 최단 경로를 반환합니다.
        //볼보이는 언제나 x = 0, y = 0 위치에서 시작하며 일이 끝나면 이 위치로 다시 돌아와야 합니다. 즉, 반환된 경로의 시작과 끝은 언제나 이 위치여야 합니다.
        //시작 위치에 골프공이 있는 경우는 없습니다.

        ArrayList<Point> pointResult = new ArrayList<Point>(points.length + 2);
        Point startPoint = new Point(0, 0);
        pointResult.add(startPoint);

        if (points.length == 0) {
            return pointResult;
        }

        // 각 노드들로 트리를 하나씩 만들기
        DisjointSet set = new DisjointSet(points);
        set.addNode(startPoint);

        // 최종 결과를 저장할 mst 생성. mst 변들의 리스트
        LinkedList<Edge> mst = new LinkedList<Edge>();

        // Edge 데이터를 저장할 ArrayList
        ArrayList<Edge> edges = new ArrayList<>((points.length) * (points.length));

        // 시작점에서 변 데이터 생성
        for (int i = 0; i < points.length; ++i) {
            Edge edge = new Edge(startPoint, points[i]);
            edges.add(edge);
        }
        // 각 지점에서 변 데이터 생성
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                Edge edge = new Edge(points[i], points[j]);
                edges.add(edge);
            }
        }

        // 변을 오름차순으로 정렬
        Collections.sort(edges);

        // 이 변에 연결된 두 노드가 같은 집합 소속인지 확인
        for (int i = 0; i < edges.size(); ++i) {
            Point n1 = edges.get(i).getNode1();
            Point n2 = edges.get(i).getNode2();

            Point root1 = set.find(n1);
            Point root2 = set.find(n2);

            // 같은 집합 소속이 아니라면 mst에 이 변을 추가하고 두 집합을 합침
            if (!root1.equals(root2)) {
                mst.add(edges.get(i));
                set.union(n1, n2);
            }
        }

//        for (int i = 0; i < mst.size(); ++i) {
//            System.out.println(mst.get(i).getNode1() + " => " + mst.get(i).getNode2());
//        }

        Point currentPoint = startPoint;
        while (mst.size() != 0) {
            var removeIndex = -1;
            for (int i = 0; i < mst.size(); ++i) {
                var point1 = mst.get(i).getNode1();
                var point2 = mst.get(i).getNode2();

                if (point1 == currentPoint) {
                    pointResult.add(point2);
                    currentPoint = point2;
                    removeIndex = i;
                    break;
                } else if (point2== currentPoint) {
                    pointResult.add(point1);
                    currentPoint = point1;
                    removeIndex = i;
                    break;
                }
            }
            mst.remove(removeIndex);
        }
        pointResult.add(startPoint);
        return pointResult;
    }
}