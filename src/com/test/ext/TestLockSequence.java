package com.test.ext;

public class TestLockSequence {

	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		new Thread(new MyThreadObject(a, b)).start();
		new Thread(new MyThreadObject(b, a)).start();
	}
	
	
}

class MyThreadObject implements Runnable{

	private Object myLock1;
	private Object myLock2;
	
	public MyThreadObject(Object lock1, Object lock2){
		this.myLock1 = lock1;
		this.myLock2 = lock2;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(myLock1){
			// do something 
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(myLock2){
				// do something
				System.out.println(myLock1.hashCode() + myLock2.hashCode() + System.currentTimeMillis());
			}
		}
	}
	
}
