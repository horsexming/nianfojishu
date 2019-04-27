package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DiGui {
	//0 1 1 2 3 5
	public static int gain(int index){
		if(index==1){
			return 0;
			
		}else if(index==2){
			return 1;
		}else{
			
			return gain(index-2)+gain(index-1);
		}		
		
	}
	//0 1 1 2 3 5
	public static int gain1(int index){
//		int[] arr=new int[100];
//		arr[0]=0;
//		arr[1]=1;
//		for(int i=2;i<index;i++){
//			arr[i]=arr[i-1]+arr[i-2];
//		}
//		return arr[index-1];
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		System.out.print("0 ,1 ,");
		for(int i=2;i<index;i++){
			if(i%5==0){
				System.out.println();
			}
			
			
			list.add((list.get(i-1)+list.get(i-2)));
			System.out.print(list.get(i)+" ,");
		}
		
		return list.get(index-1);
	}
	
	
	//HashMap 统计各个元素在数组出现的次数
	public static void testHashMap(){
		int [] a = {2,1,3,2,0,4,2,1,2,3,1,5,6,2,2,3};    
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();    
        for(int i=0; i<a.length; i++){    
            if(map.containsKey(a[i])){    
                int tmp = map.get(a[i]);    
                tmp+=1;    
                map.put(a[i], tmp);    
            }else{    
                map.put(a[i], 1);    
            }    
        }    
        Set<Integer> set = map.keySet();          
        for (Integer s : set) {    
            if(map.get(s)>=a.length/2){    
                System.out.println(s);    
            }    
        }  
	}
	public static  void gainCishu(String[] str){
		Map<String,Integer> map=new HashMap<String, Integer>();
		
		for(String s:str){
			map.put(s, map.get(s)==null?1:map.get(s)+1);
//			if(map.containsKey(s)){
//				
//				(s,map.get(s)+1 );
//			}else{map.put
//				map.put(s,1);
//			}
			
			
		}
		
		Set set=map.entrySet();
		Iterator it=set.iterator();
		while(it.hasNext()){
			Entry<String,Integer>  entry= (Entry<String, Integer>) it.next();
			
			System.out.println("单词"+entry.getKey()+"次数："+entry.getValue());
		}
		
		
		Iterator it1=map.keySet().iterator();
		while(it1.hasNext()){
			Object key=it1.next();
			System.out.println("单词"+key+"次数："+map.get(key));
		}
		
		
	}
	
	public static void main(String[] args) {
		gainCishu(new String[]{"aaa", "bbb", "ccc", "ddd", "ddd", "aaa"});
		
	}
}
