package handler;

import java.util.Timer;
import java.util.TimerTask;

public class timer extends TimerTask {

    public void run() {
        player.check();
    }

    public static void main() {
        Timer timer = new Timer();
        timer.schedule(new timer(), 1000, 10000);
    }

}

