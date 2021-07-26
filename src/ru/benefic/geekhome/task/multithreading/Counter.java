package ru.benefic.geekhome.task.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class Counter implements Runnable {

    private static int count = 0;
    private static final ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (count < 300) {
            increment();
        }
    }

    private void increment() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ": " + count);
        count++;
        lock.unlock();
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(counter).start();
        new Thread(counter).start();
        new Thread(counter).start();
    }
}
