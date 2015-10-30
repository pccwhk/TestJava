package com.test.ext;

import com.test.BaseClassA;
import com.test.DerivedClassB;

public class TestObjectHierarchy {

	
	public static void main(String[] args) {
		DerivedClassB b = new DerivedClassB();
		BaseClassA a1 = b;
		b.myMethod();
		
		// external package cannot access protected
		//BaseClassA a2 = new BaseClassA();
		//a2.myMethod();
	}
}
