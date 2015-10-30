package com.test.ext;

import java.util.concurrent.*;
import java.util.*;

public class TestConcurrent {

	public static void main(String[] args) {
		testHashMap();
		//testConcurrentHashMap();
	}
	

	public static void testConcurrentHashMap(){
		ConcurrentMap<String, String> m = new ConcurrentHashMap<>();
		m.put("a", null); // exception
		m.put(null, "a"); // exception
	}

	public static void testHashMap(){
		Map<String, String> m = new HashMap<>();
		m.put("a", null); // ok
		m.put(null, "a"); // ok
	}
}
