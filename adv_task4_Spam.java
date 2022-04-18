package ua.advanced.task4;

import java.util.Scanner;

public class Spam {
    private Thread[] threads;

    public Spam(String[] messages, int[] times) {
        this.threads = new Thread[messages.length];
        for(int i = 0; i < threads.length; ++i)
            this.threads[i] = new Worker(messages[i], times[i]);
    }

    public void start() {
        for(int i = 0; i < threads.length; ++i)
            threads[i].start();
    }

    public void stop() {
        for(int i = 0; i < threads.length; ++i)
            threads[i].stop();
    }

    private static class Worker extends Thread {
        String message;
        int time;

        public Worker(String message, int time) {
            this.message = message;
            this.time = time;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(time);
                System.out.println(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        String[] messages = new String[] { "@@@", "bbbbbbb", "qwerty" };
        int[] times = new int[] { 333, 222, 3000 };

        Spam spam = new Spam(messages, times);
        spam.start();

        Scanner in = new Scanner(System.in);
        if (in.nextLine().equals(""))
            spam.stop();
    }

}
//Create Spam class that accepts an array of messages and a сoherent array of time intervals in milliseconds in its constructor, and simultaneously prints out the corresponding messages in the given time intervals. On Enter click, the application should terminate its work (this functionality should be placed in the Spam.main method).
//
//Recommended structure of the Spam class:
//————————————————
//public class Spam {
//  private Thread[] threads;
//  public Spam(String[], int[]) {…}
//  public void start() {…}
//  public void stop() {…}
//  private static class Worker extends Thread {…}
//  public static void main(String[] args) {...}
//}
//————————————————
//
//During demonstration of the functionality, mock Enter click in every 2 seconds (this functionality is to be placed in the Part2.main method).
//
//Input data (an array of messages and an array of intervals) are to be written in the code of the Spam class. The quantity of the elements in each of the arrays should be at least 2, you can take them from the example:
//
//Example of the input data (Spam class)
//————————————————
//String[] messages = new String[] { "@@@", "bbbbbbb" };
//int[] times = new int[] { 333, 222 };
