package thread;

/**
 * Created with IntelliJ IDEA.
 * User: lin.zhao
 * Date: 15/10/21
 * Time: 16:43
 */
public class SynchronizedTest {

    private int count = 0;

    private static int staticCount = 0;

    /**
     * 实例方法同步
     * <p/>
     * 他锁定的是调用这个同步方法对象
     * <p/>
     * 如果一个对象有多个synchronized方法，只要一个线程访问了其中的一个synchronized方法，其它线程不能同时访问这个对象中任何一个synchronized方法
     */
    private synchronized void add1() {
        for (int i = 0; i < 5; i++) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 方法同步
     * <p/>
     * 同上
     */
    private void add2() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() + " count=" + count);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }

    private void add3() {
        for (int i = 0; i < 5; i++) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }


    private synchronized void sync2() {
        System.out.println(Thread.currentThread().getName() + " sync2" + " count=" + count);
    }

    /**
     * 静态方法同步
     * <p/>
     * 他锁定的是当前调用这个方法的对象所属的类
     */
    private synchronized static void staticAdd1() {
        for (int i = 0; i < 5; i++) {
            staticCount++;
            System.out.println(Thread.currentThread().getName() + " count=" + staticCount);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 静态方法同步
     * <p/>
     * 同上
     */
    private static void staticAdd2() {
        synchronized (SynchronizedTest.class) {
            for (int i = 0; i < 5; i++) {
                staticCount++;
                System.out.println(Thread.currentThread().getName() + " count=" + staticCount);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }

    private static void staticAdd3() {
        for (int i = 0; i < 5; i++) {
            staticCount++;
            System.out.println(Thread.currentThread().getName() + " count=" + staticCount);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    private static void test1() {
        final SynchronizedTest obj = new SynchronizedTest();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    obj.add1();
                    obj.sync2();
                }
            });
            thread.start();
        }
    }


    private static void test2() {
        final SynchronizedTest obj = new SynchronizedTest();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    obj.add2();
                    obj.sync2();
                }
            });
            thread.start();
        }
    }

    private static void test3() {
        final SynchronizedTest obj = new SynchronizedTest();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    obj.add3();
                    obj.sync2();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
    }

}
