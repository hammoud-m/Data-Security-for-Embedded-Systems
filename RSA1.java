/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.security.lab.pkg1;   

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import javax.crypto.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 *
 * @author Mohammad
 */

public class RSA1 {
    
    private BigInteger n, e, d;
    
    //constructor
    public RSA1(String p, String q, String e) {
        n = new BigInteger(p).multiply(new BigInteger(q));
        BigInteger w = new BigInteger(p).subtract(BigInteger.ONE).multiply(new BigInteger(q).subtract(BigInteger.ONE));
        this.e = new BigInteger(e);
        d = this.e.modInverse(w);
    }
    
    public byte[] encrypting(PublicKey key) throws Exception {
        Cipher RSA = Cipher.getInstance("RSA", "BC");
        RSA.init(Cipher.ENCRYPT_MODE, key);
        BigInteger S = new BigInteger ("152");
        byte[] cpTxt = RSA.doFinal(S.toByteArray());
        System.out.println("Message = " + S);
        System.out.println("RSA cipher text: " + formatBytes(cpTxt, ":"));
        return cpTxt;
    }
    
    public BigInteger decrypting(BigInteger bi) {
        BigInteger x = bi.modPow(d, n);
        return x; 
    }
    
    public String toString() {
        String str = "PrivateKey=["+d+", "+n+"], PublicKey=["+e+", "+n+"]";
        return str;
    }
    
    public static String formatBytes(byte[] Byte, String sp) {
        if (sp == null)
            sp = "";
        
        String str = "";
        for (byte b : Byte)
            str += String.format("%02X", b) + sp;
        
        if (sp.length() > 0)
            str = str.substring(0, str.length() - sp.length());
        
        return str;
    }
    
    public static void main(String[] args) throws Exception {
        
        Security.addProvider(new BouncyCastleProvider());
        
        KeyPairGenerator KG = KeyPairGenerator.getInstance("RSA", "BC");
        KG.initialize(1024, SecureRandom.getInstance("SHA1PRNG"));
        KeyPair kp = KG.generateKeyPair();
        
        RSAPrivateCrtKey pkey = (RSAPrivateCrtKey)kp.getPrivate();
        String P = pkey.getPrimeP().toString();
        String Q = pkey.getPrimeQ().toString();
        String E = pkey.getPublicExponent().toString();
        
        PublicKey pub = kp.getPublic();
        RSA1 RSA = new RSA1(P, Q, E);
        byte[] msg = RSA.encrypting(pub);
        BigInteger testing = new BigInteger(msg);
        System.out.println("decrypted message = " + RSA.decrypting(testing));
    }
}
