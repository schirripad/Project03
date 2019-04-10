package Simulation;

public class Clock {
	private static long timePassed;

	public static void updateTime() {
		timePassed++;
	}

	public static long getTimePassed() {
		return timePassed;
	}

}
