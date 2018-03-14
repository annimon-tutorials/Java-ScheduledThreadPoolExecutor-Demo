package com.example.scheduledexecutor;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public final class TimerExample {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        for (int i = 1; i <= 10; i++) {
            timer.schedule(new Task(), TimeUnit.SECONDS.toMillis(i));
        }
    }

    private static class Task extends TimerTask {

        @Override
        public void run() {
            final LocalTime now = LocalTime.now();
            if (now.getSecond() % 10 == 0) {
                throw new IllegalStateException();
            }
            System.out.format("%s: %s%n", now, Thread.currentThread().getName());
        }
    }
}
