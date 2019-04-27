package com.task.test;

public class DiguiTest {

	/*
	 * public static void func() { func(); } public static void main(String[]
	 * args) { func(); }
	 */

	public static void main(String[] args) {
		//yueshu(1,2);//求最大公约数
		binary(100);
	}

	  //求最大公约数
	public static void yueshu(int num1, int num2) {
		if (num1 == num2) {
			System.out.println(num1);
		} else {
			yueshu(Math.abs(num1 - num2), Math.min(num1, num2));
		}
	}
	
	 
	   //求二进制
	   public static void binary(int num)
	   {
	    if(num > 0)
	    {
	     binary(num / 2);
	        System.out.print (num % 2);
	    }
	  }
}
