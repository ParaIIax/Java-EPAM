package ua.advanced.task4;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {
    private static class MyInputStream extends InputStream {
        boolean selector = true;

        @Override
        public int read() throws IOException {
            if (selector) {
                selector = false;
                try {
                    Thread.sleep(2000);
                    return 10;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InputStream VALUE_OF_SYSTEM_IN = System.in;
        System.setIn(new MyInputStream());
        Thread t = new Thread() { public void run() {Spam.main(null);}};
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.setIn(VALUE_OF_SYSTEM_IN);
    }

}
//Additional task information.
//(1) Do not use daemon threads as the execution of Part2 goes along with the other tasks in the package, daemons threads
//will not terminate their work until the Demo.main method finishes its execution.
//(2) In order to track the Enter click, it’s enough to read the console input and analyze the content. If the reading is
//implemented using Scanner / BufferedReader classes, then an empty line will be the sign of the Enter click that is returned,
//respectively, by Scanner#nextLine() / BufferedReader#readLine() methods.
//(3) The algorithm of the console input mock (Part2.main method):
//————————————————
//(a) substitute the system input stream for your own:
//	System.setIn(YOUR_OWN_INPUT_STREAM);
//(b) call Spam.main in a separate thread:
//	Thread t = new Thread() { public void run() {Spam.main(null);}};
// 	t.start();
//(c) wait until Spam.main terminates its work:
//	t.join();
//(d) restore the system stream:
//	System.setIn(CAСHED_VALUE_OF_SYSTEM_IN)
//————————————————
//(4) For implementation of your own input stream it’s appropriate to create a class that extends java.io.InputStream abstract class.
// However, you will need to implement the only abstract method of this class:
//————————————————
//public abstract int read() throws IOException;
//All the methods from the InputStream class (along with all its child classes methods) that read bytes from some data resource,
//in the end, call the ‘read’ method. It’s enough to implement a pause in this method during the first call that will make
//the execution thread, calling the ‘read’ method, wait. The ‘read’ method should sequentially return bytes that correspond
//to the line terminator, after that it needs to return -1 only (the sign that there is no data in the input stream anymore).
