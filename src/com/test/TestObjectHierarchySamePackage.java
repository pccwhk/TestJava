package com.test;

import com.test.BaseClassA;
import com.test.DerivedClassB;

public class TestObjectHierarchySamePackage {

	
	public static void main(String[] args) {
		DerivedClassB b = new DerivedClassB();
		BaseClassA a1 = b;
		a1.myMethod();

		BaseClassA a2 = new BaseClassA();
		a2.myMethod();
	}
}
