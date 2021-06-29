import java.util.EnumSet;

public class HelloJava {
    public static void main(String args[]) {
        int[] arr = new int[]{3, 8, 32, 4, 7, 2, 1, 5};
//        selectionSort(arr);
//        bubbleSort(arr);
//        quickSort(arr);
        insertionSort(arr);
        print(arr);
    }

    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            int insertIndex = i;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] > nums[i]) {
                    insertIndex = j;
                }
            }

            if (insertIndex != i) {
                var temp = nums[i];
                for (int k = i; k > insertIndex; --k) {
                    nums[k] = nums[k - 1];
                }
                nums[insertIndex] = temp;
            }
        }
    }

    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int minIndex = i;
            for (int j = i; j < nums.length; ++j) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = 0; j < nums.length - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void quickSort(int[] nums) {
        if (nums == null)
            return;
        if (nums.length == 0)
            return;

        quickSortRecursive(nums, 0, nums.length - 1);
    }

    public static void quickSortRecursive(int[] nums, int left, int right) {
        if (left >= right)
            return;

        int pivotPos = partition(nums, left, right);

        quickSortRecursive(nums, left, pivotPos - 1);
        quickSortRecursive(nums, pivotPos + 1, right);
    }

    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(nums, pivotPos, right);
        return pivotPos;
    }

    public static void swap(int[] nums, int i, int j) {
        var temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void print(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            System.out.print(nums[i] + " ");
        }
    }
}
