package com.test.ext;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class TestDeadLock {

	ExecutorService es = Executors.newFixedThreadPool(50);

	public static AtomicLong l = new AtomicLong(1);
	
	public static void main(String[] args) throws InterruptedException {
		// doing a task inside a critical section of codes to wait for a result
		// but
		// it create a new thread, which in turn try to get the mutual access
		// of the original object
		TestDeadLock t = new TestDeadLock();
		MyLocalObject a = t.newMyLocalObject();
		int threadCount = 5;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(threadCount);

		int f = 0;
		
		for (int i = 0; i < threadCount; ++i) {// create and start threads
			new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						startSignal.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Thread is running now " + (l.incrementAndGet()));
					System.out.println(a.doCriticaljob());
					System.out.println("Thread is running now after do job" + (l.incrementAndGet()));
					doneSignal.countDown();
				}
				
			}).start();
		}
		System.out.println("Main - Activating all threads now....");
		startSignal.countDown();
		doneSignal.await();
		System.out.println("Main - I shoould be last to finish now....");
	}

	private MyLocalObject newMyLocalObject() {
		// TODO Auto-generated method stub
		return new MyLocalObject();
	}

	class MyLocalObject {

		public synchronized int doCriticaljob2() {
			return ThreadLocalRandom.current().nextInt();
		}

		public synchronized int doCriticaljob() {

			int r = 0;
			Future<Integer> f = es.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					return doCriticaljob2();

				}

			});
			try {
				r = f.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return r;
			
		}
	}
}
