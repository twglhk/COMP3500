package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {

        return findMaxAltitudeRecursiveBinarySearch(altitudes, 0, altitudes.length - 1);
    }

    public static int findMaxAltitudeRecursiveBinarySearch(final int[] altitudes, int left, int right) {
        if (left > right)
            return -1;

        int mid = (left + right) / 2;
        int midLeft;
        int midRight;

        if (mid != 0)
            midLeft = mid - 1;
        else
            midLeft = mid;

        if (mid != altitudes.length - 1)
            midRight = mid + 1;
        else
            midRight = mid;

        if (altitudes[mid] >= altitudes[midLeft] && altitudes[mid] >= altitudes[midRight])
            return mid;
        else if (altitudes[mid] < altitudes[midLeft])
            return findMaxAltitudeRecursiveBinarySearch(altitudes, left, mid - 1);
        else //if (altitudes[mid] < altitudes[midRight])
            return findMaxAltitudeRecursiveBinarySearch(altitudes, mid + 1, right);
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> bounds = new ArrayList<>();
        int maxAltitudeTime = findMaxAltitudeRecursiveBinarySearch(altitudes, 0, altitudes.length - 1);
        if (altitudes[maxAltitudeTime] == targetAltitude) {
            bounds.add(maxAltitudeTime);
            return bounds;
        }
        recursiveFindTargetTimeLeftBounds(bounds, altitudes, targetAltitude, 0, maxAltitudeTime);
        recursiveFindTargetTimeRightBounds(bounds, altitudes, targetAltitude, maxAltitudeTime, altitudes.length - 1);
        return bounds;
    }

    public static void recursiveFindTargetTimeLeftBounds(final ArrayList<Integer> bounds, final int[] altitudes, int targetAltitude, int left, int right) {
        if (left > right)
            return;

        int mid = (left + right) / 2;
        if (altitudes[mid] < targetAltitude)
            recursiveFindTargetTimeLeftBounds(bounds, altitudes, targetAltitude, mid + 1, right);
        else if (altitudes[mid] > targetAltitude)
            recursiveFindTargetTimeLeftBounds(bounds, altitudes, targetAltitude, left, mid - 1);
        else
            bounds.add(mid);
    }

    public static void recursiveFindTargetTimeRightBounds(final ArrayList<Integer> bounds, final int[] altitudes, int targetAltitude, int left, int right) {
        if (left > right)
            return;

        int mid = (left + right) / 2;
        if (altitudes[mid] < targetAltitude)
            recursiveFindTargetTimeRightBounds(bounds, altitudes, targetAltitude, left, mid - 1);
        else if (altitudes[mid] > targetAltitude)
            recursiveFindTargetTimeRightBounds(bounds, altitudes, targetAltitude, mid + 1, right);
        else
            bounds.add(mid);
    }
}