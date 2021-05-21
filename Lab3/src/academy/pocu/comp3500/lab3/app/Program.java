package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

public class Program {

    public static void main(String[] args) {
	    // write your code here
        {
            final int[] altitudes = new int[] { 5, 6, 7, 8, 2, 1 };

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            System.out.println(maxAltitudeTime);
            //assert (maxAltitudeTime == 6);
        }
    }
}
