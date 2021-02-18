package app;

import java.util.List;

public class RunEncryption {

    public static void main(String[] args) {

        Encryptor encryptor =  new Encryptor();

        List<String> encryptedFile = encryptor.encryptFile();

        for (String word:encryptedFile) {
            System.out.printf(word);
        }

    }
}
