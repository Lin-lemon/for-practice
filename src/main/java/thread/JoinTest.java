package thread;

/**
 * Created with IntelliJ IDEA.
 * User: lin.zhao
 * Date: 15/10/20
 * Time: 16:50
 * <p/>
 * <p/>
 * fixme join()作用：
 * <p/>
 * 保证当前线程停止执行，直到该线程所加入的线程完成为止。然而，如果它加入的线程没有存活，则当前线程不需要停止
 * 使用join方法后，直到这个线程退出，程序才会往下执行
 * 使异步执行的线程变成同步执行
 */
public class JoinTest {

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " start");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " end");
                    } catch (Exception e) {

                    }
                }
            });

            thread.start();
            thread.join();

            System.out.println(Thread.currentThread().getName() + " end");
        } catch (Exception e) {

        }
    }

}
