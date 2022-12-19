package com.example.ReaderWriterProblem;

import java.util.concurrent.Semaphore;

public class WriterReaderThirdV2 {

    static int readCount = 0;// number of readers
    static int writeCount = 0; // number of writers
//    static Semaphore readLock = new Semaphore(1);
    static Semaphore resource = new Semaphore(1);//for handling the writing
    static Semaphore mutex = new Semaphore(1);// ensure mutual exclusion for writers counter
//    static Semaphore mutex2 = new Semaphore(1);// ensure mutual exclusion for readers counter


    static class Read implements Runnable {
        @Override
        public void run() {
            try {
                mutex.acquire();// ensure mutual exclusion
                if(writeCount > 0 || readCount == 0) {
                    mutex.release();
                    resource.acquire();
                    mutex.acquire();
                }
                readCount++;
                mutex.release();

                //read resouce here
                System.out.println(Thread.currentThread().getName() + " is READING");
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName() + " has FINISHED READING");

                mutex.acquire();//ensure mutual exclusion
                readCount--;
                if(readCount == 0) resource.release();
                mutex.release();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable {
        @Override
        public void run() {
            try {
                mutex.acquire();// ensure mutual exclusion
                writeCount++;
                mutex.release();
                resource.acquire();

                //write resource here
                System.out.println(Thread.currentThread().getName() + " is WRITING");
                Thread.sleep(2500);
                System.out.println(Thread.currentThread().getName() + " has finished WRITING");

                mutex.acquire();
                writeCount--;
                mutex.release();
                resource.release();


            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Write write = new Write();
        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(write);
        t2.setName("thread2");
        Thread t3 = new Thread(write);
        t3.setName("thread3");
        Thread t4 = new Thread(read);
        t4.setName("thread4");
        Thread t5 = new Thread(write);
        t5.setName("thread5");
        Thread t6 = new Thread(read);
        t6.setName("thread6");
        Thread t7 = new Thread(read);
        t7.setName("thread7");
        Thread t8 = new Thread(read);
        t8.setName("thread8");
        Thread t9 = new Thread(write);
        t9.setName("thread9");
        Thread t10 = new Thread(write);
        t10.setName("thread10");
        Thread t11 = new Thread(write);
        t11.setName("thread11");
        Thread t12 = new Thread(read);
        t12.setName("thread12");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();
        t11.join();
        t12.join();
    }
}