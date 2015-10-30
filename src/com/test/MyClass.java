package com.test;

public class MyClass {
	private String myName;
	private String yourNAme;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myName == null) ? 0 : myName.hashCode());
		result = prime * result
				+ ((yourNAme == null) ? 0 : yourNAme.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyClass other = (MyClass) obj;
		if (myName == null) {
			if (other.myName != null)
				return false;
		} else if (!myName.equals(other.myName))
			return false;
		if (yourNAme == null) {
			if (other.yourNAme != null)
				return false;
		} else if (!yourNAme.equals(other.yourNAme))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MyClass [myName=" + myName + ", yourNAme=" + yourNAme + "]";
	}
	
}
