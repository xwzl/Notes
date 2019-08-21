# lock 相关操作等价于 synchronize 关键字

- lock():获得锁，如果锁已经被占用，则等待；
- lockInterruptibly():获得锁，但优先响应中断；
- tryLock():尝试获得锁，如果成功，返回 true , 失败则返回 false.该方法不等待，立即返回。
- tryLock(long time,TimeUnit unit):在给定时间内获得锁
- unlock()：释放锁

# lock 与之相关的 conditional 实例等价于 await 

- await()方法会使当前线程等待，同时释放当前锁，当其他线程中使用 signal() 方法或者 signalAll() 方法时，
线程会重新获得锁并继续执行。或者当线程被中断时，也能跳出等待。
- awaitUninterruptibly() 方法与 await() 方法基本相同，但是它并不会等待过程中响应中断。
- singal() 方法用于唤醒一个在等待中的线程，singalAll()方法会唤醒所有在等待找那个的线程。

 
