package com.example.ReaderWriterProblem;
import java.util.concurrent.Semaphore;

class WriterReadersFirst {

    static int readCount = 0;// number of readers
    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1); // for controlling the writing

    static class Read implements Runnable {
        @Override
        public void run() {
            try {
                readLock.acquire();//ensure mutual exclusion
                readCount++;
                if (readCount == 1) writeLock.acquire();
                readLock.release();


                //Read resource here
                System.out.println(Thread.currentThread().getName() + " is READING");
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName() + " has FINISHED READING");


                readLock.acquire();//ensure mutual exclusion
                readCount--;
                if (readCount == 0) writeLock.release();
                readLock.release();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable {
        @Override
        public void run() {
            try {
                writeLock.acquire();
                System.out.println(Thread.currentThread().getName() + " is WRITING");
                Thread.sleep(2500);
                System.out.println(Thread.currentThread().getName() + " has finished WRITING");
                writeLock.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Write write = new Write();
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
    }
}