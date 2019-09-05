package com.cattsoft.random;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {
    public static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static void main(String[] args) {
        RandomString rs = new RandomString();
        System.out.println(rs.generateString(new Random(), SOURCES, 10));
        System.out.println(rs.generateString(new Random(), SOURCES, 10));
        System.out.println(rs.generateString(new SecureRandom(), SOURCES, 10));
        System.out.println(rs.generateString(new SecureRandom(), SOURCES, 15));

    }
    
    public String generateString(Random random, String characters,int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        //System.out.println(text[1]);
        return new String(text);
    }
}