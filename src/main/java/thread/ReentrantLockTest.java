package thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: lin.zhao
 * Date: 15/10/21
 * Time: 18:56
 */
public class ReentrantLockTest {

    private String data;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private void read() {
        try {
            Thread.sleep(500);
            //读锁开启，读线程均可进入
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "read data= " + data);
        } catch (Exception e) {
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    private void write(String data) {
        try {
            Thread.sleep(1000);
            //写锁开启，这时只有一个写线程进入
            readWriteLock.writeLock().lock();
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "write data=" + data);
        } catch (Exception e) {
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest lockTest = new ReentrantLockTest();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        lockTest.read();
                    }
                }
            });
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        lockTest.write(String.valueOf(new Random().nextInt(10)));
                    }
                }
            });
            thread.start();
        }
    }

}
