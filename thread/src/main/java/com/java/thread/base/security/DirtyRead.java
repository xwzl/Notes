package com.java.thread.base.security;

/**
 * 脏读，是指读取实例变量时，此值已经被其他线程修改了
 *
 * @author xuweizhi
 * @date 2018/12/25 17:24
 */
public class DirtyRead {

    private String username = "a";

    private String age = "AA";

    private synchronized void setValue(String username, String age) {
        this.username = username;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.age = age;
        System.out.println("setValue method thread name=" + Thread.currentThread().getName() + " username=" + this.username + " age=" + this.age);
    }

    /**
     * 加入关键字解决脏读问题 引起脏读的原因是因为getValue值已近改变
     */
    private void getValue(){
        System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + this.username + " age=" + this.age);
    }

    public static void main(String[] args){
      DirtyRead dirtyRead = new DirtyRead();
      Thread thread = new Thread(() -> {
          dirtyRead.setValue("B", "VV");
      });
      thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dirtyRead.getValue();
    }

}
