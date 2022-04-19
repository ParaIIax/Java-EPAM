package ua.advanced.task4;

public class Part3 {
    private int counter;
    private int counter2;
    
    public void compare() {
        if (counter > counter2)
            System.out.println("counter > counter2");
        else if (counter < counter2)
            System.out.println("counter < counter2");
        else
            System.out.println("counter = counter2");
        ++counter;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++counter2;
    }

    private static Object obj = new Object();

    public void compareSync() {
        synchronized (obj) {
            compare();
        }
    }
    
    
    public static void main(String[] args) {
        Part3 example = new Part3();
        System.out.println("Not synchronized:");
        SyncController syncController = new SyncController(example, false);
        SyncController syncController1 = new SyncController(example, false);
        SyncController syncController2 = new SyncController(example, false);

        try {
            syncController.th.join();
            syncController1.th.join();
            syncController2.th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Synchronized:");
        SyncController syncController3 = new SyncController(example, true);
        SyncController syncController4 = new SyncController(example, true);
        SyncController syncController5 = new SyncController(example, true);

        try {
            syncController3.th.join();
            syncController4.th.join();
            syncController5.th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class SyncController implements Runnable {
    Part3 example;
    Thread th;
    boolean sync;

    public SyncController(Part3 example, boolean sync) {
        this.example = example;
        this.sync = sync;
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        if (sync)
            example.compareSync();
        else
            example.compare();
    }

}
//Create a class with two separate counters.
//Create several equal threads, each of them repeats the following:
//	* compares the value of the counters and prints out the result of the comparison;
//	* increments the first counter;
//	* sleeps for 100 milliseconds;
//	* increments the second counter.
//
//Compare the program functionality provided that the code is synchronized and not synchronized.
//Implement the following scheme:
//	* at first, a not synchronized code gets executed.
//	* after its termination, the synchronized code gets executed.
//The resulting output should be small, not more than a few dozen lines.
