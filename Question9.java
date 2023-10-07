/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.security.lab.pkg1;

/**
 *
 * @author nkill
 */
public class Question9 {
    public static void main(String[] args) {
        // The input I got
        long cardPublicKey = 12090988;
        long doorPublicKey = 240583;

        long doorLoops = findLoop(doorPublicKey);
        long cardLoops = findLoop(cardPublicKey);

        System.out.println(trans(doorLoops, cardPublicKey));
        System.out.println(trans(cardLoops, doorPublicKey));

    }

    public static long findLoop(long key) {
        long value = 1;
        int subjectNumber = 7;
        int divisor = 20201227;
        long theNumberOfLoops = 0;

        while (value != key) {
            value = value * 7;
            value = value % 20201227;
            theNumberOfLoops++;
        }
        return theNumberOfLoops;
    }

    public static long trans(long loop, long key) {
        long value = 1;
        for (int i = 0; i < loop; i++) {
            value = value * key;
            value = value % 20201227;
        }
        return value;
    }
}

