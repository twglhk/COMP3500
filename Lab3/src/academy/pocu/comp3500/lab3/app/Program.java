package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

public class Program {

    public static void main(String[] args) {
	    // write your code here
        {
            final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            System.out.println(maxAltitudeTime);
            assert (maxAltitudeTime == 6);
        }
    }
}
