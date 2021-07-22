package ru.benefic.geekhome.task.multithreading;

public class PingPong implements Runnable {

    final Object lock;
    String word;

    public PingPong(String word, Object lock) {
        this.word = word;
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Object lock = new Object();

        Thread ping = new Thread(new PingPong("ping", lock));
        Thread pong = new Thread(new PingPong("pong", lock));

        ping.start();
        pong.start();

        ping.join();
        pong.join();

        System.out.println("finish main");
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                lock.notifyAll();
                System.out.println(word);
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            lock.notifyAll();
        }
    }
}
