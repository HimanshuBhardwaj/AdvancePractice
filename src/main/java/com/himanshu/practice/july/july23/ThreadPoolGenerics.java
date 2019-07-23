package com.himanshu.practice.july.july23;

import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ThreadPoolGenerics {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(3);
        threadPool.startThreadPool();
        Thread.sleep(200);
        Callable callable1 = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getId() + "\t" + "Himanshu");
                return null;
            }
        };

        Callable callable2 = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getId() + "\t" + "Sai");
                return null;
            }
        };



        Callable callable3 = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getId() + "\t" + "Rajneesh");
                return null;
            }
        };

        Callable callable4 = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getId() + "\t" + "Timepass");
                return null;
            }
        };


        threadPool.submitTask(callable1);
        threadPool.submitTask(callable2);
        threadPool.submitTask(callable3);
        threadPool.submitTask(callable4);
        Thread.sleep(4000);
        threadPool.stopThreadPool();
    }
}


class ThreadPool {
    private ArrayList<ThreadImpl> waitingThread;
    ArrayList<Callable> tasks;
    private ThreadPoolHelper threadPoolHelper;

    public ThreadPool(int n) {
        waitingThread = new ArrayList<>(n);
        tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            waitingThread.add(new ThreadImpl(waitingThread));
        }
        threadPoolHelper = new ThreadPoolHelper(this);
        threadPoolHelper.setDaemon(true);

    }


    void startThreadPool() {
        threadPoolHelper.start();
    }

    void stopThreadPool() {
        threadPoolHelper.interrupt();
    }

    void submitTask(Callable task) {
        if (task != null) {
            synchronized (tasks) {
                tasks.add(task);
            }
        }
    }

    private void scheduleTask() {
        synchronized (tasks) {
            if (!tasks.isEmpty()) {
                if (!waitingThread.isEmpty()) {
                    ThreadImpl processThread = waitingThread.get(waitingThread.size() - 1);
                    waitingThread.remove(waitingThread.size() - 1);
                    processThread.setTask(tasks.get(tasks.size() - 1));
                    tasks.remove(tasks.size() - 1);
                    processThread.start();
                }
            }
        }
    }

    private static class ThreadPoolHelper extends Thread {
        ThreadPool threadPool;
        int SLEEP_TIME = 1000;

        public ThreadPoolHelper(ThreadPool threadPool) {
            this.threadPool = threadPool;
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getId()+"\t Polling to schedule task");
                try {
                    Thread.sleep(SLEEP_TIME);
                    threadPool.scheduleTask();
                } catch (InterruptedException e) {
                    System.out.println("Interrepted");
                }
            }
        }

    }


}

@Setter
class ThreadImpl extends Thread {
    private Callable task;
    private ArrayList<ThreadImpl> waitingThread;

    public ThreadImpl(ArrayList<ThreadImpl> waitingThread) {
        this.waitingThread = waitingThread;
    }

    public void run() {
        if (task != null) {
            try {
                task.call();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                task = null;
                synchronized (waitingThread) {
                    Thread.currentThread().stop();
                    waitingThread.add(this);
                }
            }
        }
    }
}
