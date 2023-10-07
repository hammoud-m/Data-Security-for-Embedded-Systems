/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.security.lab.pkg1;

import java.io.*;

/**
 *
 * @author Mohammad
 */
public class OTPInputStream extends InputStream {
    
    //have to make them static so we can use them in a static methods
    static boolean flag;
    static String Key;
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
    
    public static void setKey(String Key){
        OTPInputStream.Key = Key;
    }
    
    @Override
    public int read() throws IOException {
        int word = input.read();
        
        if(count == Key.length())
            return -1;
        
        if(word == 10)
            return -1;
        else if(word == 43){
            setFlag(false);
            word = input.read();
        } else if (word == 45) { // Decrypts if -
            setFlag(true);
            word = input.read();
        }
        
        if(word < 65 || word > 90) //only capital letters in ASCII
            return -1;
        if (flag) { // Decrypts
            int c = Key.charAt(count);
            count++;
            return ((((word - c) % 26) + 26) % 26) + 65;
        }

        int c = Key.charAt(count); // Encrypts
        count++;
        return ((((word + c) % 26) + 26) % 26) + 65;
    }
    
}
