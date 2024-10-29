package thread;

public class Producer /* extends Thread */ implements Runnable {
    private String string;
    private Fifo fifo;
    private int sleep;
    private int i = 0;

    public Producer(String str, Fifo fifo, int sleepTime) {
        string = str;
        this.fifo = fifo;
        sleep = sleepTime;
    }

    private void go() throws InterruptedException {
        long ido;
        
        while (true) {
            ido = System.currentTimeMillis();
            fifo.put(string + " " + i++);
            System.out.println("produced " + string + " " + i + " " + ido % 100000);
            Thread.sleep(sleep);
        }
    }

    @Override
    public void run() {
        try {
            this.go();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
