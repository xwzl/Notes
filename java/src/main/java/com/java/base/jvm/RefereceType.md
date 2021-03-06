#  一、背景

Java的内存回收不需要程序员负责，JVM会在必要时启动Java GC完成垃圾回收。Java以便我们控制
对象的生存周期，提供给了我们四种引用方式，引用强度从强到弱分别为：强引用、软引用、弱引用、
虚引用。

## 二、简介

## 1.强引用 StrongReference

  StrongReference是Java的默认引用形式，使用时不需要显示定义。任何通过强引用所使用的对象
  不管系统资源有多紧张，Java GC都不会主动回收具有强引用的对象。
  
  ```java
public class StrongReferenceTest {

	public static int M = 1024*1024;

	public static void printlnMemory(String tag){
		Runtime runtime = Runtime.getRuntime();
		int M = StrongReferenceTest.M;
		System.out.println("\n"+tag+":");
		System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
	}
	
	public static void main(String[] args){
		StrongReferenceTest.printlnMemory("1.原可用内存和总内存");
		
		//实例化10M的数组并与strongReference建立强引用
		byte[] strongReference = new byte[10*StrongReferenceTest.M];
		StrongReferenceTest.printlnMemory("2.实例化10M的数组,并建立强引用");
		System.out.println("strongReference : "+strongReference);
		
		System.gc();
		StrongReferenceTest.printlnMemory("3.GC后");
		System.out.println("strongReference : "+strongReference);

		//strongReference = null;后,强引用断开了
		strongReference = null;
		StrongReferenceTest.printlnMemory("4.强引用断开后");
		System.out.println("strongReference : "+strongReference);
		
		System.gc();
		StrongReferenceTest.printlnMemory("5.GC后");
		System.out.println("strongReference : "+strongReference);
		}
}

```

运行结果：

```console
1.原可用内存和总内存:
119M(free)/128M(total)

2.实例化10M的数组,并建立强引用:
107M(free)/128M(total)
strongReference : [B@5cb9f472

3.GC后:
46M(free)/64M(total)
strongReference : [B@5cb9f472

4.强引用断开后:
46M(free)/64M(total)
strongReference : null

5.GC后:
17M(free)/24M(total)
strongReference : null
```

## 2.弱引用 WeakReference

如果一个对象只具有弱引用，无论内存充足与否，Java GC后对象如果只有弱引用将会被自动回收。

```java
public class WeakReferenceTest {
	
	public static int M = 1024*1024;
	
	public static void printlnMemory(String tag){
		Runtime runtime = Runtime.getRuntime();
		int M = WeakReferenceTest.M;
		System.out.println("\n"+tag+":");
		System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
	}
	
	public static void main(String[] args){  
		WeakReferenceTest.printlnMemory("1.原可用内存和总内存");

		//创建弱引用
		WeakReference<Object> weakRerference = new WeakReference<Object>(new byte[10*WeakReferenceTest.M]);   
		WeakReferenceTest.printlnMemory("2.实例化10M的数组,并建立弱引用");
		System.out.println("weakRerference.get() : "+weakRerference.get());
		
		System.gc();
		StrongReferenceTest.printlnMemory("3.GC后");
		System.out.println("weakRerference.get() : "+weakRerference.get());
	}   
}

```

## 3.软引用 SoftReference

 软引用和弱引用的特性基本一致， 主要的区别在于软引用在内存不足时才会被回收。如果一个对象只具有软引用，
 Java GC在内存充足的时候不会回收它，内存不足时才会被回收。
 
 ```java
public class SoftReferenceTest {
	
	public static int M = 1024*1024;
	
	public static void printlnMemory(String tag){
		Runtime runtime = Runtime.getRuntime();
		int M = StrongReferenceTest.M;
		System.out.println("\n"+tag+":");
		System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
	}
	
	public static void main(String[] args){
		SoftReferenceTest.printlnMemory("1.原可用内存和总内存");
		
		//建立软引用
		SoftReference<Object> softRerference = new SoftReference<Object>(new byte[10*SoftReferenceTest.M]);
		SoftReferenceTest.printlnMemory("2.实例化10M的数组,并建立软引用");
		System.out.println("softRerference.get() : "+softRerference.get());
	  
		System.gc();  
		SoftReferenceTest.printlnMemory("3.内存可用容量充足，GC后");
		System.out.println("softRerference.get() : "+softRerference.get());  

		//实例化一个4M的数组,使内存不够用,并建立软引用
		//free=10M=4M+10M-4M,证明内存可用量不足时，GC后byte[10*m]被回收
		SoftReference<Object> softRerference2 = new SoftReference<Object>(new byte[4*SoftReferenceTest.M]);
		SoftReferenceTest.printlnMemory("4.实例化一个4M的数组后");
		System.out.println("softRerference.get() : "+softRerference.get());
		System.out.println("softRerference2.get() : "+softRerference2.get());  
	 } 
}

```
## 4.虚引用 PhantomReference

 从PhantomReference类的源代码可以知道，它的get()方法无论何时返回的都只会是null。所以单独使用虚引用时，没有什么意义，需要和引用队列ReferenceQueue类联合使用。当执行Java GC时如果一个对象只有虚引用，就会把这个对象加入到与之关联的ReferenceQueue中。

```java
public class PhantomReferenceTest {

	public static int M = 1024*1024;

	public static void printlnMemory(String tag){
		Runtime runtime = Runtime.getRuntime();
		int M = PhantomReferenceTest.M;
		System.out.println("\n"+tag+":");
		System.out.println(runtime.freeMemory()/M+"M(free)/" + runtime.totalMemory()/M+"M(total)");
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		PhantomReferenceTest.printlnMemory("1.原可用内存和总内存");
		byte[] object = new byte[10*PhantomReferenceTest.M];		
		PhantomReferenceTest.printlnMemory("2.实例化10M的数组后");
		
	    //建立虚引用
	    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
	    PhantomReference<Object> phantomReference = new PhantomReference<Object>(object,referenceQueue);  
	    
	    PhantomReferenceTest.printlnMemory("3.建立虚引用后");
	    System.out.println("phantomReference : "+phantomReference); 
	    System.out.println("phantomReference.get() : "+phantomReference.get());
	    System.out.println("referenceQueue.poll() : "+referenceQueue.poll());
	    
	    //断开byte[10*PhantomReferenceTest.M]的强引用
	    object = null;  
	    PhantomReferenceTest.printlnMemory("4.执行object = null;强引用断开后");
	    
	    System.gc();
	    PhantomReferenceTest.printlnMemory("5.GC后");
	    System.out.println("phantomReference : "+phantomReference); 
	    System.out.println("phantomReference.get() : "+phantomReference.get());
	    System.out.println("referenceQueue.poll() : "+referenceQueue.poll());	    
	   
	    //断开虚引用
	    phantomReference = null;
		System.gc(); 
		PhantomReferenceTest.printlnMemory("6.断开虚引用后GC");
	    System.out.println("phantomReference : "+phantomReference);
	    System.out.println("referenceQueue.poll() : "+referenceQueue.poll());	    	
	}
}

```

# 三、小结
  强引用是 Java 的默认引用形式，使用时不需要显示定义，是我们平时最常使用到的引用方式。不管系统资源有多紧张，Java GC都不会主动回收具有强引用的对象。
  弱引用和软引用一般在引用对象为非必需对象的时候使用。它们的区别是被弱引用关联的对象在垃圾回收时总是会被回收，被软引用关联的对象只有在内存不足时才会被回收。
  虚引用的get()方法获取的永远是null，无法获取对象实例。Java GC会把虚引用的对象放到引用队列里面。可用来在对象被回收时做额外的一些资源清理或事物回滚等处理。
  由于无法从虚引获取到引用对象的实例。它的使用情况比较特别，所以这里不把虚引用放入表格进行对比。这里对强引用、弱引用、软引用进行对比
