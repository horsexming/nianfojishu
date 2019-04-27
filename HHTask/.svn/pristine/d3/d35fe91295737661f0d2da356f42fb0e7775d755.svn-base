package com.task.test;

public class ExcelTest {

	public static void main(String[] args) {
		String[] excelLie = { "A", "B", "C", "D", "AA", "AB", "BA" };
		for (int i = 0; i < excelLie.length; i++) {
			String lie = excelLie[i];
			int num = 0;
			int length = lie.getBytes().length;
			if (length == 1) {
				num = lie.getBytes()[0] - 65;
			} else if (length == 2) {
				num = lie.getBytes()[0] + (lie.getBytes()[0] - 65 + 1) * 25
						+ lie.getBytes()[1] - 129;
			}
			System.out.println(num);
			num = 0;
		}
	}
}
