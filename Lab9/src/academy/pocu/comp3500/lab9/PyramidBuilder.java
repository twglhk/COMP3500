package academy.pocu.comp3500.lab9;

import java.util.PriorityQueue;

public class PyramidBuilder {
    public static int findMaxHeight(final int[] widths, int statue) {
        int height = 0;
        int stoneNumber = 0;
        int minStoneNumber = 2;
        PriorityQueue<Integer> widthMinQueue = new PriorityQueue<>();
        for (int i = 0; i < widths.length; ++i) {
            widthMinQueue.add(widths[i]);
        }

        // 동상 페이즈
//        System.out.println();
//        System.out.println(statue);
        int totalWidth = 0;
        while (totalWidth <= statue || stoneNumber < minStoneNumber) {
            if (widthMinQueue.size() == 0)
                return height;
            var stone = widthMinQueue.poll();
            totalWidth += stone;
            stoneNumber++;
//            System.out.print(stone + " ");
        }
        height++;
        minStoneNumber = stoneNumber + 1;
//        System.out.println();

        // 벽돌 페이즈
        while (widthMinQueue.size() >= minStoneNumber) {
            stoneNumber = 0;
            while (stoneNumber < minStoneNumber) {
                var stone = widthMinQueue.poll();
//                System.out.print(stone + " ");
                stoneNumber++;
            }
//            System.out.println();
            minStoneNumber++;
            height++;
        }
//        System.out.println();
        return height;
    }
}