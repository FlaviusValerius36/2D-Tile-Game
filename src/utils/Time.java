package utils;

import java.util.concurrent.TimeUnit;

public class Time {

    public static final long ONE_SECOND = 1_000;
    public static final long ONE_MINUTE = Math.multiplyExact(ONE_SECOND, 60);
    public static final long ONE_HOUR = Math.multiplyExact(ONE_MINUTE, 60);
    public static final long ONE_DAY = Math.multiplyExact(ONE_HOUR,  24);



    public static void convertMilliSecondsToSeconds(long milliSeconds) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliSeconds);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliSeconds);

        System.out.format("%d Milliseconds = %d minutes\n", milliSeconds, minutes);
        System.out.println("Or");
        System.out.format("%d Milliseconds = %d seconds", milliSeconds, seconds);
    }

    public static String millisToStandardConversion(long duration) {
        StringBuilder res = new StringBuilder();
        long temp = 0;

        if(duration >= ONE_SECOND) {
            // Day Calculation
            temp = duration / ONE_DAY;

            if(temp > 0) {
                duration -= temp * ONE_DAY;
                res.append(temp).append(" day").append(temp > 1 ? "s" : "").append(duration >= ONE_MINUTE ? ", " : "");
            }
            // Hour Calculation
            temp = duration / ONE_HOUR;

            if(temp > 0) {
                duration -= temp * ONE_HOUR;
                res.append(temp).append(" hour").append(temp > 1 ? "s" : "").append(duration >= ONE_MINUTE ? ", " : "");
            }
            // Minute Calculation
            temp = duration / ONE_MINUTE;

            if(temp > 0) {
                duration -= temp * ONE_MINUTE;
                res.append(temp).append(" minute").append(temp > 1 ? "s" : "");
            }
            if(!res.toString().equals("") && duration >= ONE_SECOND) {
                res.append(" and ");
            }
            // Second Calculation
            temp = duration / ONE_SECOND;

            if(temp > 0) {
                res.append(temp).append(" second").append(temp > 1 ? "s" : "");
            }
            return res.toString();
        } else {
            return "0 second";
        }
    }
}
