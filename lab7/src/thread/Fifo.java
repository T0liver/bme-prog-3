package thread;

import java.util.Arrays;
import java.util.LinkedList;

public class Fifo {
    private LinkedList<String> fifoList;

    public Fifo() {
        fifoList = new LinkedList<>();
    }

    public Fifo(String[] strs) {
        fifoList = new LinkedList<>(Arrays.asList(strs));
    }

    public synchronized void put(String data) throws InterruptedException {
        System.out.println("fifoput " + Thread.currentThread());

        while (fifoList.size() >= 10) {
            wait();
        }
        notify();
        fifoList.add(data);
    }

    public synchronized String get() throws InterruptedException {
        while (fifoList.size() <= 0) {
            wait();
        }

        String ret = fifoList.get(0);
        fifoList.remove(0);

        System.out.println("fifoget " + Thread.currentThread());

        notify();

        return ret;
    }
}
