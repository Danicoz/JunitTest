package com.cattsoft.statictest;

public class Test {
    // ≤‚ ‘return”Ôæ‰   
    public ReturnClass testReturn() {   
        try {   
            return new ReturnClass();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            System.out.println("÷¥––¡Àfinally”Ôæ‰");   
        }   
        return null;   
    }   
  
    // ≤‚ ‘continue”Ôæ‰   
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
                System.out.println("÷¥––¡Àfinally”Ôæ‰");   
            }   
        }   
    }   
  
    // ≤‚ ‘break”Ôæ‰   
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
                System.out.println("÷¥––¡Àfinally”Ôæ‰");   
            }   
        }   
    }   
  
    public static void main(String[] args) {   
        Test ft = new Test();   
        // ≤‚ ‘return”Ôæ‰   
        ft.testReturn();   
        System.out.println();   
        // ≤‚ ‘continue”Ôæ‰   
        ft.testContinue();   
        System.out.println();   
        // ≤‚ ‘break”Ôæ‰   
        ft.testBreak();   
    }   
}   
  
class ReturnClass {   
    public ReturnClass() {   
        System.out.println("÷¥––¡Àreturn”Ôæ‰");   
    }   
}  
