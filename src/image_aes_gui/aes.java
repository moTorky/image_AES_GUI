/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_aes_gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author Mo R Torky
 */
public class aes {
    private final int n = 128;

    //GENERATE AES KEY 
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(n);
    SecretKey key = keyGenerator.generateKey();
    return key;
    } 
    //GENERATE IV
    public static IvParameterSpec generateIv() {
    byte[] iv = new byte[16];
    new SecureRandom().nextBytes(iv);
    return new IvParameterSpec(iv);
    }
    
    
   public static void ECBencryptFile(String algorithm, SecretKey key,
            File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, key);
        FileInputStream inputStream = new FileInputStream(inputFile);
    FileOutputStream outputStream = new FileOutputStream(outputFile);
    int bytesRead;
    byte[] imgHeader = new byte[636];
     int l=  inputStream.read(imgHeader,0,imgHeader.length);
    outputStream.write(imgHeader);
    for (int i = 0; i < imgHeader.length; i++) {
        System.out.print((char)imgHeader[i]);
    }
    byte[] buffer = new byte[64];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        byte[] output = cipher.update(buffer, 0, bytesRead);
        if (output != null) {
            outputStream.write(output);
        }
    }
    byte PNGfooter[]={(byte)0x49,(byte)0x45,(byte)0x4e,(byte)0x44,(byte)0xae,(byte)0x42,(byte)0x60,(byte)0x82};
    byte[] outputBytes = cipher.doFinal();
    if (outputBytes != null) {
        outputStream.write(outputBytes);
    }
    inputStream.close();
    outputStream.close();
}
    
    public static void copy(File inputFile, File outputFile)throws IOException{
         FileInputStream inputStream = new FileInputStream(inputFile);
    FileOutputStream outputStream = new FileOutputStream(outputFile);
    int bytesRead;
    byte[] imgHeader = new byte[8];
          int l=  inputStream.read(imgHeader,0,imgHeader.length);
    outputStream.write(imgHeader);
    for (int i = 0; i < imgHeader.length; i++) {
        System.out.print((char)imgHeader[i]);
    }
        for (int i = 0; i < 100; i++) {
            
        }
    byte[] buffer = new byte[64];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        if (buffer != null) {
            outputStream.write(buffer);
        }
    }

    inputStream.close();
    outputStream.close();
    }
    
    public static void ECBdecryptFile(String algorithm, SecretKey key,
            File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.DECRYPT_MODE, key);
        FileInputStream inputStream = new FileInputStream(inputFile);
    FileOutputStream outputStream = new FileOutputStream(outputFile);
     byte[] imgHeader = new byte[636];
     int l=  inputStream.read(imgHeader,0,imgHeader.length);
    outputStream.write(imgHeader);
    for (int i = 0; i < imgHeader.length; i++) {
        System.out.print((char)imgHeader[i]);
    }
    int bytesRead;
    byte[] buffer = new byte[64];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        byte[] output = cipher.update(buffer, 0, bytesRead);
        if (output != null) {
            outputStream.write(output);
        }
    }
    byte[] outputBytes = cipher.doFinal();
    if (outputBytes != null) {
        outputStream.write(outputBytes);
    }
    inputStream.close();
    outputStream.close();
}
     
    public static void CBCencryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
            File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        FileInputStream inputStream = new FileInputStream(inputFile);
    FileOutputStream outputStream = new FileOutputStream(outputFile);
     byte[] imgHeader = new byte[620];
     int l=  inputStream.read(imgHeader,0,imgHeader.length);
    outputStream.write(imgHeader);
        int bytesRead;
    byte[] buffer = new byte[64];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        byte[] output = cipher.update(buffer, 0, bytesRead);
        if (output != null) {
            outputStream.write(output);
        }
    }
    byte[] outputBytes = cipher.doFinal();
    if (outputBytes != null) {
        outputStream.write(outputBytes);
    }
    inputStream.close();
    outputStream.close();
}   
    public static void CBCdecryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
            File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
    
        Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.DECRYPT_MODE, key, iv);
        FileInputStream inputStream = new FileInputStream(inputFile);
    FileOutputStream outputStream = new FileOutputStream(outputFile);
     byte[] imgHeader = new byte[620];
     int l=  inputStream.read(imgHeader,0,imgHeader.length);
    outputStream.write(imgHeader);
        int bytesRead;
    byte[] buffer = new byte[64];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        byte[] output = cipher.update(buffer, 0, bytesRead);
        if (output != null) {
            outputStream.write(output);
        }
    }
    byte[] outputBytes = cipher.doFinal();
    if (outputBytes != null) {
        outputStream.write(outputBytes);
    }
    inputStream.close();
    outputStream.close();
}
      

}
