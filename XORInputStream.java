package data.security.lab.pkg1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author mohammad
 */
public class XORInputStream extends InputStream{
        @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static String key, UserInput;

    public static void encrypting() throws IOException {
		
    char[] keys = new char[key.length()]; 
    int[] intkey = new int[UserInput.length()];
    char[] msg = new char[UserInput.length()];
    int[] intmsg = new int[key.length()];

    char[] results = new char[key.length()];
		
    for (int i = 0; i < key.length(); i++) {		
        keys[i] = key.charAt(i);
    }
    
    for (int i = 0; i < keys.length; i++) {
        int temprory = (int) keys[i];
        int num = temprory;
        intkey[i] = num;
    }
    for (int i = 0; i < UserInput.length(); i++) {
        msg[i] = UserInput.charAt(i);
    }
    
    for (int i = 0; i < intmsg.length; i++) {
        int temp = (int) msg[i];
        intmsg[i] = temp;
    }
    
    for (int i = 0; i < msg.length; i++) {
        int message = intmsg[i];	
        int key = intkey[i];
        int sum = 0;
        
        if (message >= 65  && message <= 90) {
            int msgg = message - 65;
            int key1 = key - 65;

            sum = (key1 ^ msgg) + 65;
            results[i] = (char)(sum);
        }
    }
    String result = new String(results);
    System.out.println(result);
    }
    
    public static void main(String[] args) throws IOException {
    System.out.println("Enter plaintext/ciphertext: ");
    Scanner scn = new Scanner(System.in);
    String user = scn.next();
    UserInput = user;
		
    System.out.println("Enter key: ");
    Scanner sc = new Scanner(System.in);
    key = sc.next();
		
    encrypting();
    }
	 
    
}
