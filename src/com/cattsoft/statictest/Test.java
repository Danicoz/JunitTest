package com.cattsoft.statictest;

public class Test {
    // ����return���   
    public ReturnClass testReturn() {   
        try {   
            return new ReturnClass();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            System.out.println("ִ����finally���");   
        }   
        return null;   
    }   
  
    // ����continue���   
    public void testContinue() {   
        for (int i = 0; i < 3; i++) {   
            try {   
                System.out.println(i);   
                if (i == 1) {   
                    continue;   
                }   
            } catch (Exception e) {   
                e.printStackTrace();   
            } finally {   
                System.out.println("ִ����finally���");   
            }   
        }   
    }   
  
    // ����break���   
    public void testBreak() {   
        for (int i = 0; i < 3; i++) {   
            try {   
                System.out.println(i);   
                if (i == 1) {   
                    break;   
                }   
            } catch (Exception e) {   
                e.printStackTrace();   
            } finally {   
                System.out.println("ִ����finally���");   
            }   
        }   
    }   
  
    public static void main(String[] args) {   
        Test ft = new Test();   
        // ����return���   
        ft.testReturn();   
        System.out.println();   
        // ����continue���   
        ft.testContinue();   
        System.out.println();   
        // ����break���   
        ft.testBreak();   
    }   
}   
  
class ReturnClass {   
    public ReturnClass() {   
        System.out.println("ִ����return���");   
    }   
}  
