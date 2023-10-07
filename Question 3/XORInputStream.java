package data.security.lab.pkg1;

import java.io.*;
import java.util.*;

/**
 *
 * @author Mohammad
 */
public class XORInputStream extends InputStream {
    static boolean flag;
    static InputStream Key; //ändrat string
    int count;
    InputStream input;
    
    public XORInputStream(InputStream input){
        this.input = input;
        count = 0;
    }
    
    public static void setKey(InputStream Key){ 
        OTPInputStream.Key = Key;
    }

    public static void setFlag(boolean flag){
        OTPInputStream.flag = flag;
    }

    public static boolean getFlag(){
        return flag;
    }

    
    @Override
    public int read() throws IOException {
        
        int word = input.read();

        if (word == 10) { // if there is space.
            return -1;
        } else if (word == 43) { // Encrypts if +
            setFlag(false);
            word = input.read();
        } else if (word == 45) { // Decrypts if -
            setFlag(true);
            word = input.read();
        }
        if(word < 65 || word > 90){ //only capital letters in ASCII
            return -1;
        }
        if (flag) { // Decrypts
            int c = Key.read();
            count++;
            return word^c;

        }
        int c = Key.read();
        count++;
        
        return word^c;
    }

    public static void main(String[] args) throws IOException {

        InputStream in = new BufferedInputStream(System.in);

        String word = "";
        OTPInputStream ourIn = new OTPInputStream(in);

        Scanner scan = new Scanner(System.in);
        
        System.out.print("write your key: ");
        String command = scan.next();
        InputStream key = new ByteArrayInputStream(command.getBytes());
        OTPInputStream.setKey(key);
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
    /// Users/nivethan-plugg/Desktop/DESE-LABS/TEST.txt

}
