package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

import java.util.ArrayList;
import java.util.HashMap;
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

        ArrayList<Point> result = new ArrayList<Point>();
        Point startPos = new Point(0, 0);
        Point currentPos = startPos;
        result.add(startPos);

        if (points.length == 0) {
            return result;
        }

        Point closestPoint = null;
        var closestDistance = Integer.MAX_VALUE;
        while (true) {
            for (var point : points) {
                if (point == currentPos) continue;
                if (result.contains(point)) continue;

                int distance = (int)(Math.pow(point.getX() - currentPos.getX(), 2) + Math.pow(point.getY() - currentPos.getY(), 2));
                if (distance < closestDistance) {
                    closestPoint = point;
                    closestDistance = distance;
                }
            }

            if (closestPoint == null) {
                result.add(startPos);
                break;
            } else {
                result.add(closestPoint);
                closestPoint = null;
                closestDistance = Integer.MAX_VALUE;
            }
        }
        return result;
    }
}