package ua.advanced.task4;

public class Part1 {
    private static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                final int time_ms = 340;
                for(int i = 1; i < 7; i++) {
                    System.out.println(Thread.currentThread().getName() + " (MyThread): " + time_ms * i + " milliseconds have passed");
                    Thread.sleep(time_ms);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                final int time_ms = 340;
                for(int i = 1; i < 7; i++) {
                    System.out.println(Thread.currentThread().getName() + " (MyRunnable): " + time_ms * i + " milliseconds have passed");
                    Thread.sleep(time_ms);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " start");

        MyThread th0 = new MyThread();
        th0.start();

        try {
            th0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread th1 = new Thread(new MyRunnable());
        th1.start();

        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

}
//Create a child thread that, during about 2 seconds,
//would print out its name every 1/3 seconds.
//Implement it in two ways:
//	* using extension of the Thread class;
//	* using implementation of the Runnable interface.
//
//First, one implementation should be executed, then,
//after it has been finished, the other implementation
//should be executed.
