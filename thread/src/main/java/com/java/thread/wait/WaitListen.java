package com.java.thread.wait;

/**
 * 方法wait()的作用是使当前执行代码的线程进行等待,wait()方法是Object类的方法,该方法用来将当前线程置入“预执行队列”中,
 * 并且在 wait()所在的代码行处停止执行,直到接到通知或被中断为止。在调用wait()之前,线程必须获得该对象的对象级别锁,即
 * 只能在同步方法或同步块中调用wait()方法。在执行wait()方法后,当前线程释放锁。在从wait()返回前,线程与其他线程竞争
 * 重新获得锁。如果调用wait()时没有持有适当的锁,则抛出IllegalMonitorState Exception,它是RuntimeException的
 * 一个子类,因此,不需要try catch语句进行捕捉异常。
 * 方法notify()也要在同步方法或同步块中调用,即在调用前,线程也必须获得该对象的对象级别锁。如果调用notify()时没有持有适
 * 当的锁,也会抛出IllegalMonitorState Exception。该方法用来通知那些可能等待该对象的对象锁的其他线程,如果有多个
 * 线程等待,则由线程规划器随机挑选出其中一个呈wait()状态的线程,对其发出通知notify(),并使它等待获取该对象的对象锁。需
 * 要说明的是,在执行notify()方法后,当前线程不会马上释放该对象锁,呈wait状态的线程也并不能马上获取该对象锁,要等到执行
 * notify()方法的线程将程序执行完,也就是出synchronized代码块后，当前线程才会释放锁，而呈wait状态所在的线程才可以获取该
 * 对象锁。当第一个获得了该对象锁的wait线程运行完毕以后，它就会释放掉该对象锁，此时如果该对象没有再次使用notify语句,则即便
 * 该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，还会继续阻塞在wait状态，直到这个对象发出一个notify或者
 * notifyAll;
 * 用一句话来进行总结：wait调用同步方法的线程终止，notify是持有该锁对象停止的线程继续运行。
 * <p>
 * #2.线程状态切换 代码实例参考{@link PrintGame} and @see thread/W.png
 * <p>
 * 日志信息wait end在最后输出,这也说明notify方法执行后并不立即释放锁。<br>
 * <p>
 * 关键字synchronized可以将任何一个Object对象作为同步对象来看待,而Java为每个Object都实现了wait和notify方法.
 * 它们必须用在被synchronized同步的Object的临界区内。通过调用wait()方法可以使处于临界区内的线程进入等待状态,同时释
 * 放被同步对象的锁。而notify操作可以唤醒一个因调用了wait操作而处于阻塞状态中的线程,使其进入就绪状态。被重新换醒的线程
 * 会试图重新获得临界区的控制权,也就是锁,并继续执行临界区内wait之后的代码。如果发出notify操作时没有处于阻塞状态中的线
 * 程,那么该命令会被忽略.<br>
 * <p>
 * wait()方法可以使调用该方法的线程释放共享资源的锁,然后从运行状态退出,进入等待队列,直到被再次唤醒。
 * <p>
 * notify()方法可以随机唤醒等待队列中等待同一共享资源的“一个”线程,并使该线程退出等待队列,进入可运行状态,也就是notify
 * 方法仅通知“一个”线程。
 * <p>
 * notifyAll()方法可以使所有正在等待队列中等待同一共享资源的“全部”线程从等待状态退出,进入可运行状态。此时,优先级最高
 * 的那个线程最先执行,但也有可能是随机执行，因为这要取决于JWM虚拟机的实现。
 * <p>
 * 在前面的章节中已经介绍了与 Thread有关的大部分API,这些API可以改变线程的状态.
 * <p>
 * 1) 创建一个新的线程对象后,再调用它的star()方法,系统会为此线程分配CPU资源,使其处于Runnable(可运行)状态,这是一个
 * 准备运行的阶段。如果线程抢占到CPU资源,此线程就处于Running(运行)状态。
 * 2) Runnable状态和 Running状态可相互切换,因为有可能线程运行一段时间后,有其他高优先级的线程抢占了CPU资源,这时此线
 * 程就从Running状态变成Runnable状态
 * 线程进入Runnable状态大体分为如下5种情况
 * 口调用seeo方法后经过的时间超过了指定的休眠时间。<br>
 * 口线程调用的阻塞1O已经返回,阻塞方法执行完毕。<br>
 * 口线程成功地获得了试图同步的监视器。<br>
 * 口线程正在等待某个通知,其他线程发出了通知。<br>
 * 口处于挂起状态的线程调用了 resume 1复方法。<br>
 * 3)Blocked是阻塞的意思,例如遇到了一个IO操作,此时CPU处于空闲状态,可能会转而把CPU时间片分配给其他线程,这时也可以称为“暂停”
 * 状态.Blocked状态结束后,进入Runnable状态,等待系统重新分配资源。<br>
 * 出现阻塞的情况大体分为如下5种<br>
 * 口线程调用sep方法,主动放弃占用的处理器资源。<br>
 * 口线程调用了阻塞式IO方法,在该方法返回前,该线程被阻塞。<br>
 * 口线程试图获得一个同步监视器,但该同步监视器正被其他线程所持有。<br>
 * 口线程等待某个通知<br>
 * 口程序调用了 suspend方法将该线程挂起。此方法容易导致死锁,尽量避免使用该方法。<br>
 * 4) run()方法运行结束后进入销毁阶段,整个线程执行完毕。<br>
 * 每个锁对象都有两个队列,一个是就绪队列,一个是阻塞队列。就绪队列存储了将要获得锁的线程,阻塞队列存储了被阻塞的线程。一个线程
 * 被唤醒后,才会进入就绪队列,等待CPU的调度;反之,一个线程被wait后,就会进入阻塞队列,等待下一次被唤醒
 *
 * @author xuweizhi
 * @date 2018/12/27 14:21
 */
public class WaitListen {

    public static void main(String[] args) {
        //waitDemo();
        Object lock = new Object();
        new Wait1(lock).start();
        new Wait1(lock).start();
        new Wait1(lock).start();
        new Wait1(lock).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Wait2(lock).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Wait2(lock).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Wait2(lock).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Wait2(lock).start();
    }

    private static void waitDemo() {
        try {
            String s = "11";
            //s.wait(); 出现异常没有加同步锁
            System.out.println("sync上面");
            synchronized (s) {
                System.out.println("sync第一行代码");
                s.wait();
                System.out.println("wait下面的代码");
            }
            System.out.println("sync下面");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Wait1 extends Thread {

    private Object object;

    public Wait1(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (object) {
                System.out.println("开始wait time=" + System.currentTimeMillis());
                object.wait();
                System.out.println("结束wait time=" + System.currentTimeMillis());
                System.out.println("");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Wait2 extends Thread {

    private Object object;

    public Wait2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println("开始notify time=" + System.currentTimeMillis());
            object.notify();
            System.out.println("结束notify time=" + System.currentTimeMillis());
            System.out.println("");
        }
    }

}