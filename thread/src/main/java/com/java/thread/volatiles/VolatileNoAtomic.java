package com.java.thread.volatiles;

/**
 * volatile虽然增加了实例变量在多个线程之间的可见性，但它并不具备同步性，那么也就不具备原子性。
 * 原子类的测试 {@link AtomicLongTest} 原子类的++可以保证原子性
 * @author xuweizhi
 * @date 2018/12/27 10:51
 */
public class VolatileNoAtomic extends Thread {

    public static volatile int count;

    /**
     * 如果对于测试的变量值count1,在测试方法中加了synchronized关键字，变量前面就不用加volatile关键字
     * 关键字volatile主要使用场合是在多个线程中可以感知实例变量被更改了，并且可以获得最新的值使用，也就是多线程读取
     * 共享变量时可以获取最新值使用。
     * 关键字volatie提示线程每次从共享内存中读取变量，而不是从私有内存中读取变量，这样就保证了同步数据的可见性。
     * 但在这里需要注意的是：如果实例变量中的数据，比如i++,也就是i+=1;这样的操作其实并不是一个原子操作，就是非线程安
     * 全的，表示i++的操作步骤分解如下：
     * 1.从内存中取出i的值。
     * 2.计算i的值。
     * 3.将i的值写到内存中。
     * 假如在第二步计算过程中，另外一个线程也修了i的值，这个时候就出现了脏读（脏读就是指当一个事务正在访问数据，并且对
     * 数据进行了修改，而这种修改还没有提交到数据库中，这时，另外一个事务也访问这个数据，然后使用了这个数据。因为这个数
     * 据是还没有提交的数据，那么另外一个事务读到的这个数据是脏数据，依据脏数据所做的操作可能是不正确的）。解决方法就是
     * 使用synchronized,所以volatile本身不处理数据的原子性，而是强制对数据的读写及时响应到主机内存中。
     */
    public static int count1;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
            System.out.println(Thread.currentThread());
        }
        System.out.println(Thread.currentThread() + "count=" + count);
    }

    /**
     * 这里要提别注意，为什么要加static关键字
     * 加入static关键字后，锁对象是VolatileNotom.class对象，不加的话就是对每个实例变量加锁，加不加锁意义不大
     */
    private synchronized static void addCount1() {
        for (int i = 0; i < 100; i++) {
            count1++;
            System.out.println(Thread.currentThread());
        }
        System.out.println(Thread.currentThread() + "count1=" + count1);
    }

    private static VolatileNoAtomic apply(VolatileNoAtomic volatileNoAtom) {
        return volatileNoAtom = new VolatileNoAtomic();
    }

    @Override
    public void run() {
        //addCount();
        addCount1();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] volatileNoAtoms = new VolatileNoAtomic[100];
        //Arrays.asList(volatileNoAtoms).stream().map(volatileNoAtom -> volatileNoAtom = new VolatileNoAtomic()).forEach(VolatileNoAtomic::start);
        //List<VolatileNoAtomic> atomList = Arrays.stream(volatileNoAtoms).map(VolatileNoAtomic::apply).collect(Collectors.toList());
        //for (int i = 0; i < atomList.size(); i++) {
        //    atomList.get(i).start();
        //}
        for (int i = 0; i < 100; i++) {
            volatileNoAtoms[i] = new VolatileNoAtomic();
        }
        for (int i = 0; i < 100; i++) {
            volatileNoAtoms[i].start();
        }
    }
}
