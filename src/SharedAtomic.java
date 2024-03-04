import java.util.concurrent.atomic.AtomicInteger;
public class SharedAtomic{
    private static volatile AtomicInteger x = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Adder());
        Thread t2 = new Thread(new Adder());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("x = "+ x);

    }
    public static class Adder implements Runnable{
        public void run(){
            for(int i=0; i<500000; i++){
                x.addAndGet(1);
            }
            System.out.println("FINAL");

        }
    }
}