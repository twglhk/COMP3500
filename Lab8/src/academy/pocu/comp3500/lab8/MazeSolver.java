package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.security.cert.PolicyNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public final class MazeSolver {
    private static char wall = 'x';
    private static char exit = 'E';
    private static char path = ' ';


    public static List<Point> findPath(final char[][] maze, final Point start) {
        Stack<Point> pathStack = new Stack<Point>();
        LinkedList<Point> resultList = new LinkedList<>();
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        findPathRecursive(maze, visit, start, pathStack, resultList);

        for (var point : resultList) {
            System.out.print("[" + point.getX() + " , " + point.getY() + "]");
        }

        System.out.println();
        return resultList;
    }

    public static void findPathRecursive(final char[][] maze, final boolean[][] visit, final Point currentPoint, final Stack<Point> pathStack, final LinkedList<Point> resultList) {
        var xPoint = currentPoint.getX();
        var yPoint = currentPoint.getY();
        if (visit[yPoint][xPoint])
            return;

        visit[yPoint][xPoint] = true;
        pathStack.push(currentPoint);

        var mazePointInfo = maze[yPoint][xPoint];
        if (mazePointInfo == exit) {
            resultList.addAll(pathStack);
        } else if (mazePointInfo == wall) {
            pathStack.pop();
        } else if (mazePointInfo == path) {
            // Left
            if (xPoint != 0) {
                findPathRecursive(maze, visit, new Point(xPoint - 1, yPoint), pathStack, resultList);
            }
            // Right
            if (xPoint != maze[0].length - 1) {
                findPathRecursive(maze, visit, new Point(xPoint + 1, yPoint), pathStack, resultList);
            }
            // Up
            if (yPoint != 0) {
                findPathRecursive(maze, visit, new Point(xPoint, yPoint - 1), pathStack, resultList);
            }
            // Down
            if (yPoint != maze.length - 1) {
                findPathRecursive(maze, visit, new Point(xPoint, yPoint + 1), pathStack, resultList);
            }
            pathStack.pop();
        }
    }
}