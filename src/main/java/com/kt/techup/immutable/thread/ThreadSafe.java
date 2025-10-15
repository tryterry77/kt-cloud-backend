package com.kt.techup.immutable.thread;

public class ThreadSafe {

	public static void main(String[] args) {
		MutablePoint immutablePoint = new MutablePoint(1, 2);

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				immutablePoint.x++;
				immutablePoint.y++;
				String msg = immutablePoint.x + ", " + immutablePoint.y;
				System.out.println("일꾼1 = " + msg);
			}
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				immutablePoint.x++;
				immutablePoint.y++;
				String msg = immutablePoint.x + ", " + immutablePoint.y;
				System.out.println("일꾼2 = " + msg);
			}
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				immutablePoint.x++;
				immutablePoint.y++;
				String msg = immutablePoint.x + ", " + immutablePoint.y;
				System.out.println("일꾼3 = " + msg);
			}
		}).start();

	}
}
