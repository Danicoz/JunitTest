package com.cattsoft.hanio;

public class HanioTest {
	
	static StringBuffer str = new StringBuffer();  
	public static void main(String[] args) {
		HanioTest.hanio(3, "A", "B", "C");

	}
	
	 public static String hanio(int n, Object x, Object y, Object z) {  
	        if(1 == n)   
	            str.append(move(x, n, z) + "\n");  
	        else {  
	            hanio(n-1, x, z, y);  
	            str.append(move(x, n, z) + "\n") ;  
	            hanio(n-1, y, x, z);  
	        }  
	        return str.toString();  
	    }  
	    private static String move(Object x, int n, Object y) {  
	    	System.out.println("Move  " + n + "  from  " + x + "  to  " + y); 
	        return "Move  " + n + "  from  " + x + "  to  " + y;  
	    }  

}
