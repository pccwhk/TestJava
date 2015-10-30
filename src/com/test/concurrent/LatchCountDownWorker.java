package com.test.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class LatchCountDownWorker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	private final int index;
	
	public LatchCountDownWorker(int index, CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		this.index = index;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
			System.out.println("CountDownWorker = I am done now = " + index); 
		} catch (InterruptedException ex) {
		} // return;
	}

	void doWork() throws InterruptedException {
		Thread.sleep(ThreadLocalRandom.current().nextLong(10000));
	}
}
