package com.cattsoft.breakcontinue;

import org.junit.Test;

public class BreakContinue {

	@Test
	public void testBreak(){
		
		out://必须在for之前
		for(int i = 0; i < 3; i++){
			
			for(int j = 0; j < 3; j++){
				System.out.println("i=" + i + "---" + "j" + j);
				if(j==2){
					System.out.println("i==j");
					break;//结束内部循环
				}
				if(i == 1 && j == 1){
					break out;//结束外部循环
				}
				
				if(i == 1){
					System.out.println("continue");
					continue;
				}
			}
		}
		
	}
	
	
}
