package net.ueye.module.common;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 100000000	256
		
		// 101000000	320
		// 101010000	336
		// 100000010   	258		
		
		// 101010010	338		
		
		int a=256;
		
		int b=320;
		
		int c=336;
		
		int d=258;
		
		int f=a|b|c|d;
				
		p(f);
		
		
	}
	
	static void p(Object value){
		System.out.println(value);
	}

}
