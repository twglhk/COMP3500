package academy.pocu.comp3500.lab11.app;

import academy.pocu.comp3500.lab11.BallBoy;
import academy.pocu.comp3500.lab11.data.Point;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        String startPoint = new Point(0, 0).toString();

//        {
//            Point[] points = {
//                    new Point(10, 10),
//                    new Point(10, -10),
//                    new Point(-10, -10),
//                    new Point(-10, 10)
//            };
//            String ballA = points[0].toString();
//            String ballB = points[1].toString();
//            String ballC = points[2].toString();
//            String ballD = points[3].toString();
//
//            List<Point> path = BallBoy.findPath(points);
//
//            assert (path.size() == points.length + 2);
//
//            String path0 = path.get(0).toString();
//            String path1 = path.get(1).toString();
//            String path2 = path.get(2).toString();
//            String path3 = path.get(3).toString();
//            String path4 = path.get(4).toString();
//            String path5 = path.get(5).toString();
//
//            assert (path0.equals(startPoint));
//            assert ((path1.equals(ballA) && path2.equals(ballB))
//                    || path1.equals(ballB) && path2.equals(ballA));
//            assert (path3.equals(startPoint));
//        }

        {
            Point[] points = {
                    new Point(0, 80),
                    new Point(0, 40),
            };
            String ballA = points[0].toString();
            String ballB = points[1].toString();

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();
            String path3 = path.get(3).toString();

            assert (path0.equals(startPoint));
            assert ((path1.equals(ballA) && path2.equals(ballB))
                    || path1.equals(ballB) && path2.equals(ballA));
            assert (path3.equals(startPoint));
        }

        {
            Point[] points = {
                    new Point(0, 80),
                    new Point(0, 40),
            };
            String ballA = points[0].toString();
            String ballB = points[1].toString();

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();
            String path3 = path.get(3).toString();

            assert (path0.equals(startPoint));
            assert ((path1.equals(ballA) && path2.equals(ballB))
                    || path1.equals(ballB) && path2.equals(ballA));
            assert (path3.equals(startPoint));
        }

        {
            Point[] points = {
                    new Point(20, 15),
                    new Point(20, 48),
                    new Point(0, 63)
            };
            String ballA = points[0].toString();
            String ballB = points[1].toString();
            String ballC = points[2].toString();

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();
            String path3 = path.get(3).toString();
            String path4 = path.get(4).toString();

            assert (path0.equals(startPoint));
            assert ((path1.equals(ballA) && path2.equals(ballB) && path3.equals(ballC))
                    || (path1.equals(ballC) && path2.equals(ballB) && path3.equals(ballA)));
            assert (path4.equals(startPoint));
        }

        {
            Point[] points = {};

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == 1);

            String path0 = path.get(0).toString();

            assert (path0.equals(startPoint));
        }

        {
            Point[] points = {
                    new Point(-3, -4)
            };
            String ballA = points[0].toString();

            List<Point> path = BallBoy.findPath(points);

            assert (path.size() == points.length + 2);

            String path0 = path.get(0).toString();
            String path1 = path.get(1).toString();
            String path2 = path.get(2).toString();

            assert (path0.equals(startPoint));
            assert (path1.equals(ballA));
            assert (path2.equals(startPoint));
        }
    }
}