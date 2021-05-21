package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {

        return findMaxAltitudeRecursiveBinarySearch(altitudes, 0, altitudes.length-1);
    }

    public static int findMaxAltitudeRecursiveBinarySearch(final int[] altitudes, int left, int right) {
        if (left == right)
            return altitudes[left];

        int mid = (left + right) / 2;
        if (altitudes[mid] >= altitudes[mid-1] && altitudes[mid] >= altitudes[mid+1])
            return altitudes[mid];

        if (altitudes[mid] < altitudes[mid-1])
            return findMaxAltitudeRecursiveBinarySearch(altitudes, left, mid);
        else
            return findMaxAltitudeRecursiveBinarySearch(altitudes, mid + 1, right);
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        return null;
    }
}