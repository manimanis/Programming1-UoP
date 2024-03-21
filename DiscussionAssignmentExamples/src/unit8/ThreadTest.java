
package unit8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> arr = new ArrayList<>();
        ProducerThread pt = new ProducerThread(arr);
        ConsumerThread[] ct = new ConsumerThread[10];
        for (int i = 0; i < ct.length; i++) {
            ct[i] = new ConsumerThread(arr, i);
            ct[i].start();
        }
        pt.start();
        Thread monitor = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Monitor thread started!");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadTest.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                pt.stopThread();
                for (int i = 0; i < ct.length; i++) {
                    ct[i].stopThread();
                }
                try {
                    pt.join();
                    for (int i = 0; i < ct.length; i++) {
                        ct[i].join();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Monitor thread ended!");
            }
        });
        monitor.start();
        monitor.join();
        System.out.print("Collection content: ");
        System.out.println(arr);
    }
}

class ProducerThread extends Thread {
    private Collection<Integer> collection;
    private static Random rand = new Random();
    private boolean running = true;
    
    public ProducerThread(Collection<Integer> collection) {
        this.collection = collection;
    }
    
    public synchronized void stopThread() {
        running = false;
    }

    @Override
    public void run() {
        System.out.println("Producer thread started!");
        while (running) {
            collection.add(rand.nextInt(1, 99));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProducerThread.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Producer thread ended!");
    }
}

class ConsumerThread extends Thread {
    private List<Integer> collection;
    private int remainder;
    private boolean running = true;
    private int count = 0;

    public ConsumerThread(List<Integer> collection, int remainder) {
        this.collection = collection;
        this.remainder = remainder;
    }
    
    public synchronized void stopThread() {
        running = false;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread " + remainder + " started!");
        while (running) {
            for (int i = collection.size() - 1; i >= 0; i--) {
                int val = collection.get(i);
                if (val % 10 == remainder) {
                    System.out.println(val + " % 10 == " + remainder);
                    collection.remove(i);
                    count++;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProducerThread.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Consumer thread consumed " + count);
    }
}
