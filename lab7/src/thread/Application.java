package thread;

public class Application {
    public static void main(String[] args) {
        // String[] strlist = {"hello", "ez", "egy", "jó" , "nap", "(így háromnegyed 12kor éjjel haha)"};
        Fifo fifo = new Fifo();
        /* Ez akkor van, ha a Producer az a Thread-et örökli
        Producer pr1 = new Producer("hello1", fifo, 1000);
        Producer pr2 = new Producer("hello2", fifo, 500);
        Consumer con = new Consumer(fifo, "hiiii", 1000);

        pr1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pr2.start();
        con.start();
        /* */

        // Ez pedig akkor van, ha a Producer a Runable-t implementálja
        Thread[] szalak = new Thread[7];

        for(int i = 0; i < 3; i++) {
            szalak[i] = new Thread(new Producer("hello", fifo, 1000));
        }
        for(int i = 3; i < 7; i++) {
            szalak[i] = new Thread(new Consumer(fifo, "hiiii" + i, 100));
        }

        for(int i = 0; i < 7; i++) {
            szalak[i].start();
        }
    }
}