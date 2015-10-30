package com.test.ext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import com.test.concurrent.LatchCountDownWorker;
import com.test.concurrent.RandomSleepWorker;

public class TestCountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int threadCount = 50;
		int maxSleep = 50000;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(threadCount);

	     for (int i = 0; i < 50; ++i) {// create and start threads
	       new Thread(new LatchCountDownWorker(i, startSignal, doneSignal)).start();
	       new Thread(new RandomSleepWorker(i)).start();
	     }
	     System.out.println("Main - All thread just created now....");
	     randomSleep(maxSleep);            // don't let run yet
	     System.out.println("Main - Activating all threads now....");
	     startSignal.countDown();      // let all threads proceed
	     doneSignal.await();           // wait for all to finish
	     System.out.println("Main - I should be the last one to finish....");
	}
	
	public static void randomSleep(int maxSleep) throws InterruptedException{
		Thread.sleep(ThreadLocalRandom.current().nextLong(maxSleep));
	}

}
