package  Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
需求：给买票程序加上同步代码块
*/
class ImplementThread implements Runnable
{
    private int tick = 100;
    final Object object = new Object();
    public void run(){
        //控制程序一直循环，知道票卖完
        while(true){
            //同步，也就是上锁
            synchronized(object){
                if(tick>0){
                    try
                    {
                        //使用线程中的sleep方法，模拟线程可能出现的问题
                        //因为sleep方法有抛出异常，而run方法不能抛出异常，所以应在内部try处理
                        Thread.sleep(10);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+".....run...."+tick--);
            }
            }
        }
    }
}
class  ThreadDemo4
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new ImplementThread());
        executorService.submit(new ImplementThread());
        executorService.submit(new ImplementThread());
        executorService.submit(new ImplementThread());
        executorService.shutdown();


        //创建Runable接口子类对象
//        ImplementThread it = new ImplementThread();
//        Thread t1 = new Thread(it);
//        Thread t2 = new Thread(it);
//        Thread t3 = new Thread(it);
//        Thread t4 = new Thread(it);
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
    }
}