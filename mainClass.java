/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.security.lab.pkg1;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Mohammad
 */
public class mainClass {
    
    public static void main(String[] args) throws IOException {
       /*
        key XMCKL
        cipher EQNVZ
        plain text HELLO
       */
        String word = "";
        OTPInputStream ourIn = new OTPInputStream(new BufferedInputStream(System.in));

        Scanner scan = new Scanner(System.in);
        System.out.print("write the key: ");
        String command = scan.next();
        OTPInputStream.setKey(command);
        
        System.out.println("Write + 'word' for encrypt & -'word' to decrypt. ");
        int sum = ourIn.read();
        while (sum != -1) {
            word += (char) sum;
            sum = ourIn.read();
            System.out.println(sum);

        }
        if (OTPInputStream.getFlag()) {
            System.out.println("Decrypted: " + word);
        } else {
            System.out.println("Encrypted: " + word);
        }
       

    }
}
