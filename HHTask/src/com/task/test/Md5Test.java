package com.task.test;

public class Md5Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String[] str = { "a", "b", "c" , "d" , "e" };
		for (int i = 0; i < str.length; i++) {
			String str1 = str[i];
			System.out.println(str1);
			for (int j = 0; j < str.length; j++) {
				String str2 = str[j];
				System.out.println(str1 + str2);
				for (int k = 0; k < str.length; k++) {
					String str3 = str[k];
					System.out.println(str1 + str2 + str3);
				}
			}
		}
	}
}
