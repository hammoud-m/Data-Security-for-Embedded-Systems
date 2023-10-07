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

import javax.crypto.Cipher;

import javax.crypto.spec.IvParameterSpec;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AES{
    public static void decryptingAES() throws Exception{
        String key = "0123456789abcdef";    
        String iv = "0000000000000000";
        String data = "1ff4ec7cef0e00d81b2d55a4bfdad4ba";
        
        byte[] arrKey = key.getBytes();
        byte[] arrIv = iv.getBytes();
        
        SecretKeySpec seckey = new SecretKeySpec(arrKey, "AES");
        Cipher cp = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cp.init(Cipher.DECRYPT_MODE, seckey, new IvParameterSpec(arrIv));
        byte[] plainTxt = hex(data);
        System.out.println("AES Decrypted message: " + new String(cp.doFinal(plainTxt)));
    }
        
    public static byte[] hex(String data){
        byte[] arr = DatatypeConverter.parseHexBinary(data);
        return arr;
    }
        
    public static void main(String[] args) throws Exception {
        decryptingAES();
    }
}