package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;

public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        quickSort(tasks);
        int resultProfit = 0;
        for (int skillLevel : skillLevels) {
            for (Task task : tasks) {
                if (task.getDifficulty() <= skillLevel) {
                    resultProfit += task.getProfit();
                    break;
                }
            }
        }
        return resultProfit;
    }

    public static void quickSort(final Task[] tasks) {
        if (tasks == null)
            return;
        if (tasks.length == 0)
            return;

        quickSortRecursive(tasks, 0, tasks.length - 1);
    }

    public static void quickSortRecursive(final Task[] tasks, int left, int right) {
        if (left >= right)
            return;

        int pivotPos = partition(tasks, left, right);

        quickSortRecursive(tasks, left, pivotPos - 1);
        quickSortRecursive(tasks, pivotPos + 1, right);
    }

    public static int partition(final Task[] tasks, int left, int right) {
        int pivot = tasks[right].getProfit();
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            if (tasks[j].getProfit() > pivot) {
                i++;
                swap(tasks, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(tasks, pivotPos, right);
        return pivotPos;
    }

    public static void swap(final Task[] tasks, int i, int j) {
        var temp = tasks[i];
        tasks[i] = tasks[j];
        tasks[j] = temp;
    }
}