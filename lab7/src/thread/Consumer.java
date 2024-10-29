package thread;

public class Consumer extends Thread {
    private Fifo fifo;
    private String string;
    private int nInt;

    public Consumer(Fifo f, String s, int n) {
        fifo = f;
        string = s;
        nInt = n;
    }

    private void go() throws InterruptedException {
        long ido;
        while (true) {
            ido = System.currentTimeMillis();
            System.out.println("consumed " + string + " " + fifo.get() + " " + ido % 100000);
            Thread.sleep(nInt);
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
