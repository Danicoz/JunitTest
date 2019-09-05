package com.cattsoft;

public class JoinTest
{

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("main start");

        Thread t1 = new Thread(new Worker("thread-1"));
        Thread t2 = new Thread(new Worker("thread-2"));
        
        t1.start();//
        t2.start();t1.join();//t2.join();
        //
        //
        

        System.out.println("main end");
    }
}

class Worker implements Runnable
{

    private String name;

    public Worker(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(1l);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(name);
        }
    }

}
