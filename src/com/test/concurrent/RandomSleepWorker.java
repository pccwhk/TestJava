package com.test.concurrent;

import java.util.concurrent.ThreadLocalRandom;

public class RandomSleepWorker implements Runnable {
	
	private final int index;
	
	public RandomSleepWorker(int i){
		this.index = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(ThreadLocalRandom.current().nextLong(10000));
			System.out.println("RamdomSleepWorker finished now, index = " + index);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
