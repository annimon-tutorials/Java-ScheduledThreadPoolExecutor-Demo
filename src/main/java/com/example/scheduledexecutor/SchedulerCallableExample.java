package com.example.scheduledexecutor;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SchedulerCallableExample {

     public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        ScheduledFuture<LocalTime> future = pool.schedule(SchedulerCallableExample::task, 3, TimeUnit.SECONDS);
        pool.shutdown();
        System.out.format("Now: %s%n", LocalTime.now());
        try {
            System.out.format("Future: %s%n", future.get());
        } catch (ExecutionException ex) {
            System.err.println(ex.getMessage());
        }
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static LocalTime task() {
        final LocalTime now = LocalTime.now();
        if (now.getSecond() % 10 == 0) {
            throw new IllegalStateException();
        }
        return now;
    }
}
