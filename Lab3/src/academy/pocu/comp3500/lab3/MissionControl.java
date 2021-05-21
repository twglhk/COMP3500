package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {

        return findMaxAltitudeRecursiveBinarySearch(altitudes, 0, altitudes.length - 1);
    }

    public static int findMaxAltitudeRecursiveBinarySearch(final int[] altitudes, int left, int right) {
        if (left == right)
            return left;

        int mid = (left + right) / 2;
        int midLeft;
        int midRight;

        if (mid != 0)
            midLeft = mid - 1;
        else
            midLeft = mid;

        if (mid != altitudes.length -1)
            midRight = mid + 1;
        else
            midRight = mid;

        if (altitudes[mid] > altitudes[midLeft] && altitudes[mid] > altitudes[midRight])
            return mid;

        if (altitudes[mid] <= altitudes[midLeft])
            return findMaxAltitudeRecursiveBinarySearch(altitudes, left, mid);
        else
            return findMaxAltitudeRecursiveBinarySearch(altitudes, mid + 1, right);
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> bounds = new ArrayList<>();

        return null;
    }
}