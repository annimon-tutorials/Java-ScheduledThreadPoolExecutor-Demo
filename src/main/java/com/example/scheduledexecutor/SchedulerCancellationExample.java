package com.example.scheduledexecutor;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SchedulerCancellationExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> future = pool.scheduleAtFixedRate(() -> {
            System.out.format("%s: %s%n", LocalTime.now(), Thread.currentThread().getName());
        }, 2, 5, TimeUnit.SECONDS);
        // Start the task that will cancel repeatable task
        pool.schedule(() -> {
            System.out.println("Cancel fixed rate task and shutdown");
            future.cancel(true);
            pool.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}
