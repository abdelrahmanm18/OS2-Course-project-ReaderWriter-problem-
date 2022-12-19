package com.example.ReaderWriterProblem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class fairness {
    static int readcounter =0;
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();
    static Account account = new Account(500);
    public static class Read implements Runnable{
        @Override
        public void run() {
            readLock.lock();
            try {
            System.out.println(Thread.currentThread().getName() + " checking his balance now");
            System.out.println(Thread.currentThread().getName() + " balance is " + account.getBalance());
            }finally {
            readLock.unlock();
            }
        }
    }

    public static class Write implements Runnable{
        @Override
        public void run(){
            writeLock.lock();
            try {
            System.out.println(Thread.currentThread().getName() + " is despoiting 100$ "  );
            System.out.println(Thread.currentThread().getName()+ " despoited 100$ and ur balance now is " +account.despoit(50));
            }finally {
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fairness.Read read = new fairness.Read();
        fairness.Write write = new fairness.Write();
        Thread t1 = new Thread(write);
        t1.setName("thread1");
        Thread t2 = new Thread(read);
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

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();
//        t5.join();
//        t6.join();
//        t7.join();
//        t8.join();
    }
}
