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

    public synchronized void compareSync() {
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

    public static void main(String[] args) {
        Part3 target = new Part3();
        Thread th1, th2, th3;
        System.out.println("Not synchronized:");
        th1 = new Thread(target::compare);
        th2 = new Thread(target::compare);
        th3 = new Thread(target::compare);
        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread th4, th5, th6;
        System.out.println("Synchronized:");
        th4 = new Thread(target::compareSync);
        th5 = new Thread(target::compareSync);
        th6 = new Thread(target::compareSync);
        th4.start();
        th5.start();
        th6.start();

        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
            th5.join();
            th6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
