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
public class OTPInputStream extends InputStream {
    
    //have to make them static so we can use them in a static methods
    static boolean flag;

    static InputStream Key; //changed string
    int count;
    InputStream input;

    //cunstructor
    public OTPInputStream(InputStream input){
        this.input = input;
        count = 0;
    }
    // have to make these methods static so we can call them in the main method
    public static void setFlag(boolean flag){
        //this.flag = flag;
        OTPInputStream.flag = flag;
    }
   
    public static boolean getFlag(){
        return flag;
    }
      
    //public static void setKey(String Key){ //changed
    public static void setKey(InputStream Key){ //changed
        OTPInputStream.Key = Key;
    }
    
    @Override
    public int read() throws IOException {
        int word = input.read();
        
        if(word == 10)
            return -1;
        // for encrypt
        else if((word == 43) || (word == 42)){
            setFlag(false);
            word = input.read();
        } //for decrypt
        else if ((word == 45) || (word == 47)) {
            setFlag(true);
            word = input.read();
        }

        if(flag){
            if(word < 65 || word > 90) //only capital letters in ASCII
             return -1;
            
            if (flag) { // Decrypts
                //int c = Key.charAt(count);
                int c = Key.read(); //changed
                count++;
                return ((((word - c) % 26) + 26) % 26) + 65;
            }
        }
        //int c = Key.charAt(count); // Encrypts
        int c = Key.read(); //changed
        count++;
        return ((((word + c) % 26) + 26) % 26) + 65; 
    }
    
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
        //OTPInputStream key = new OTPInputStream(new ByteArrayInputStream(command.getBytes())); //changed
        InputStream key = new ByteArrayInputStream(command.getBytes()); //changed
        OTPInputStream.setKey(key);
        System.out.println("Write + 'word' for encrypt & - 'word' to decrypt. \n or Write * 'word' for XOR encrypt & / 'word' for XOR decrypt");
        int sum = ourIn.read();
        while (sum != -1) {
            word += (char) sum;
            sum = ourIn.read();
            System.out.println(sum);
        }
        if (OTPInputStream.getFlag()) {
            System.out.println("Decrypted: " + word);
        }else
            System.out.println("Encrypted: " + word);
    }
    
}
