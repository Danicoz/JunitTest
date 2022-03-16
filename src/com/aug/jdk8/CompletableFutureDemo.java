package com.aug.jdk8;
/*
 *@Author lyj
 *@Date 2022/3/1 14:13
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {


    //参考：https://blog.csdn.net/qq_31865983/article/details/106137777

    /****
     * supplyAsync：
     * 创建带返回值的异步任务的，相当于ExecutorService submit(Callable<T> task) 方法
     * runAsync：
     * 创建无返回值的异步任务，相当于ExecutorService submit(Runnable task)方法
     * thenApply：
     * 某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法
     * thenApplyAsync：
     * 功能与thenApply功能一样，另起一个线程运行
     * thenAccept:
     * 接收上一个任务的返回值作为参数，无返回值
     * thenRun:
     * 没有入参，也没有返回值
     * whenComplete：
     * 执行结果或者执行期间抛出的异常传递给回调方法，调用形式为whenComplete(a,b),b为异常信息，获取结果为上个任务执行的结果
     * handle：
     * 功能与whenComplete一致，有返回值，该返回值与上次任务的执行结果无关
     * thenCombine：
     * 两个任务的执行结果作为方法入参传递到指定方法中，且该方法有返回值
     * thenAcceptBoth：
     * 将两个任务的执行结果作为方法入参，但是无返回值
     * runAfterBoth：
     * 没有入参，也没有返回值
     * thenCompose：
     * 上次任务的执行结果入参，返回一个新的CompletableFuture实例,相当于后面有函数式接口实现
     * allOf：
     * 多个CompletableFuture 任务都正常执行完，才会执行下一步
     * anyOf:
     * 任一个CompletableFuture 任务正常执行，才会执行下一步
     */

    @Test
    public void test1(){
        CompletableFuture.supplyAsync(() -> "Hello ")
                .thenApply(s -> s + "World ")
                .thenApply(String::toString)
                .thenCombine(CompletableFuture.completedFuture("Java"), (s1, s2) -> s1 + s2)
                .thenAccept(System.out::println);
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

         /*
        * 输出：
        * Thread[pool-1-thread-2,5,main]cf2:startTime1646119635555
        * Thread[pool-1-thread-1,5,main]cf1:startTime1646119637552
        * cf:1  cf2:2
        * 总结：cf2先输出，说明异步执行
        * */
         //返回值的异步方法，相当于callable
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
            }
            System.out.println(Thread.currentThread() + "cf1:startTime" + System.currentTimeMillis());
            return new Integer(1);
        }, executorService);

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
            }
            System.out.println(Thread.currentThread() + "cf2:startTime" + System.currentTimeMillis());
            return new Integer(2);
        }, executorService);

        CompletableFuture.allOf(cf, cf2);
        System.out.println("cf:" + cf.get() + "  cf2:"  + cf2.get());

        //runAsync不返回值异步方法，相当于runnable
        CompletableFuture cf3 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
            }
            System.out.println(Thread.currentThread() + "cf3:startTime" + System.currentTimeMillis());
        }, executorService);

        CompletableFuture.allOf(cf, cf3);
        System.out.println("cf:" + cf.get() + "  cf3:"  + cf2.get());

    }

    /**
     * thenApply 方法返回值作为入参传递到回调方法中
     * 与thenApplyAsync 区别
     * thenApplyAsync 是另起一个线程去执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer>cf1 = CompletableFuture.supplyAsync(() ->  new Integer(1));
        CompletableFuture<Integer>cf2 = cf1.thenApply(s -> {
            Integer num = s+12;
            return num;
        });
        System.out.println("cf1=====" + cf1.get() + " cf2=====" +  + cf2.get());

        //合在一起
        CompletableFuture<Integer>cf3 = CompletableFuture.supplyAsync(() -> new Integer(1))
                .thenApply(s -> {
                    Integer num = s + 2;
                    return num;
                });
        System.out.println("cf3=====" + cf3.get());
    }

    /**
     * thenAccept 接收参数，但无返回值
     * thenRun 无参数无返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test4() throws ExecutionException, InterruptedException {

        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<String>();
            list.add("a");
            list.add("b");
            list.add("c");
            return list; })
                .thenApply(s -> {s.add("f");
                    System.out.println(s.size());
                return s;})
                .thenAccept((o) -> {
                    for (String str:o) { System.out.println(str.toString()); }
                    o.stream().forEach(y -> System.out.println("====" + y));
                })
                .thenRun(() -> {
                    System.out.println("thenRun()");
                    return;
                });
        cf.get();
    }


    /**
     * whenComplete 传入结果和异常信息，异常不为null，将上面的异常信息传入
     * whenComplete(a,b): a:结果，b为异常信息。
     */
    @Test
    public void test5(){
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("startTime:" + System.currentTimeMillis());

            if(true){
                throw new RuntimeException("test");
            }else{
                System.out.println("运行成功：" + System.currentTimeMillis());
            }
            return "a";
        }).whenComplete((a,b) -> {
            if(b != null){
                System.out.println("error:");
                b.printStackTrace();
            }else{
                System.out.println("run result:" + a);
            }
        });
    }

    /**
     * henCombine会将两个任务的执行结果作为方法入参传递到指定方法中，且该方法有返回值；
     * thenAcceptBoth同样将两个任务的执行结果作为方法入参，但是无返回值；
     * runAfterBoth没有入参，也没有返回值
     * */
    @Test
    public void test6() throws ExecutionException, InterruptedException {

        //cf1和cf2 是创建异步
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf1...");
            return new Integer(1);
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf2...");
            return new Integer(2);
        });

        //thenCombine 执行完cf1和cf2，再对结果操作执行，
        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (a,b) -> {
            System.out.println("cf3...");
            return a+b;
        });

        try {
            System.out.println("cf1=" + cf1.get() + "\t" + "cf2=" + cf2.get() + "\t" + "cf3=" + cf3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //有参数无返回值
        CompletableFuture cf4 = cf1.thenAcceptBoth(cf2,(a,b) -> {
            System.out.println("cf4" + " a=" + a);
            return;
        });

        //运用的话：日志提示
        CompletableFuture cf5 = cf1.runAfterBoth(cf2,() -> {
            System.out.println("cf5...");
            return;
        });

        //合并后再次执行操作
        CompletableFuture<Integer> cf6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf6...");
            return new Integer(6);
        });
        CompletableFuture<Integer> cf7 = cf3.thenCombine(cf6,(a,b) -> {
            System.out.println("cf7...");
            return a+b;
        });
        System.out.println("cf7=" + cf7.get());
    }


    /***
     * thenCompose 上次结果作为参数入参，返回CompletableFuture对象
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test7() throws ExecutionException, InterruptedException {
        //cf1和cf2 是创建异步
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf1...");
            return new Integer(1);
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf2...");
            return new Integer(2);
        });

        CompletableFuture<Integer> cf3 = cf1.thenCompose((param) -> {
            return CompletableFuture.supplyAsync(() -> param + 2);
        });
        System.out.println(cf1.join());
        System.out.println(cf3.get());
    }

    /***
     * allOf返回的CompletableFuture是多个任务都执行完成后才会执行
     * anyOf返回的CompletableFuture是多个任务只要其中一个执行完成就会执行
     */
    @Test
    public void test8(){

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {

            System.out.println("cf1...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Integer(1);
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf2...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Integer(2);
        });

        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf3...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Integer(3);
        });

        //任一线程运行完即可
        CompletableFuture cf4 = CompletableFuture.anyOf(cf1, cf2, cf3)
                .whenComplete((a,b) -> {
            if (b != null){
                System.out.println("error run result...");
                b.printStackTrace();
            }else{
                System.out.println("result=" + a);
            }
                });

        System.out.println(cf4.join());

        //所有线程运行完
        CompletableFuture.allOf(cf1, cf2, cf3);
        System.out.println(cf3.join());

    }


    @Test
    public void test9(){

        System.out.println("小白进入餐厅...");
        System.out.println("小白看了菜单点了番茄炒蛋和蒸鱼...");


        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("厨师在炒鸡蛋...");
                Thread.sleep(100);
                System.out.println("厨师在蒸鱼...");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "厨师已经做好菜...";
        });

        System.out.println("小白在打王者...");
        System.out.println("小白开吃了..." + completableFuture.join());

    }



}
