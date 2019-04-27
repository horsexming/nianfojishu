package com.test;

import java.util.HashMap;
import java.util.Map;

public class Hash {
	public static void main(String[] args) {
		 // 按照结点的定义新建一个链表
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(3);
        ListNode n7 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = null;
        //调用delete函数，传入n1的值，当成头结点
        ListNode result = delete(n1);
        print(result);
	}
	
	
	//输入一个正数n，输出所有和为n的连续正数序列
	public static void getAns(int n) {
	    int min = 1;
	    int sum = 1;
	    int max = 1;
	    while(min <= n/2+1) {
	        if(sum == n) {
	            for(int k = min; k<=max; k++) {         
	            System.out.println(k+"");
	            }
	            System.out.println();
	            sum = sum - min;
	            min ++;
	            max ++;
	            sum = sum+max;
	        }
	        if(sum > n) {
	            sum = sum - min;
	            min ++;
	        } else {
	            max ++;
	            sum = sum + max;
	        }
	    }
	}
	//输入一个正数n，输出所有和为n的连续正数序列。
	public static void gainConstent(int num){
		int min=1,max=1,sum=1;
		while(min<=num/2+1){
			if(sum==num){
				for(int i=min;i<=max;i++){
					System.out.println(i);
				}
				System.out.println("\n\n");
				sum-=min;
				min++;
				max++;
				sum+=max;
			}
			
			if(sum>num){
				sum-=min;
				min++;
				
			}
			if(sum<num){
				max++;
				sum+=max;
			}
			
		}
	}
	
	 // 结点定义，包括当前结点的值和next指向
    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public String toString() {
            return val + "";
        }
    }
    
    
 // 删除节点的函数
    public static ListNode delete(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        // 定义一个临时的头结点，因为头结点也可能被删除
        ListNode root = new ListNode();
        root.next = head;
        ListNode prev = root;
        ListNode node = head;

        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
            //若有连续相同的结点，则node要一直++
      while (node.next != null && node.next.val == node.val)
                    node = node.next;
                prev.next = node.next;
            } else {
                prev.next = node;
                prev = prev.next;
            }
            node = node.next;
        }
        return root.next;
    }
    //打印出来删除后的结果
    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head + "->");
            head = head.next;
        }
        System.out.println("null");
    }

}
