package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Encryptor {

    public List<String> textFileArr() {
        List<String> textfile = new ArrayList<>();
        try {
            File file = new File("whale2.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                str = str.replaceAll("[-+.^:,?;'\"!\n&()0-9*]", "");
                str = str.toLowerCase();
                textfile.addAll(Arrays.asList(str.split(" ")));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textfile;
    }

    public List<String> encryptFile() {
        Scanner input = new Scanner(System.in);
        List<String> textFileWords = textFileArr();
        List<String> encryptedFile = new ArrayList<>();
        Set<Character> uniqueChar = new LinkedHashSet<>();
        System.out.println("Input keyword: ");
        String uniqueKeyword = input.nextLine();

        char[] tempCharArr = uniqueKeyword.toCharArray();

        for (Character c : tempCharArr) {
            uniqueChar.add(c);
        }

        VigenereTable table = new VigenereTable();
        table.setupTable();
        char[][] tableArr = table.getTable();
        char[] tempArr;

        //keyletter == col, letter == row
        int colIndex = 0;
        int rowIndex = 0;
        List<Character> uniqueLetterList = new ArrayList<>(uniqueChar);
        List<Character> keyword = new ArrayList<>();
        for (String word : textFileWords) {
            tempArr = word.toCharArray();

            //makes keyword the same length as the word we try and encrypt
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr.length == keyword.size()) {
                    break;
                } else if (i >= uniqueLetterList.size()) {
                    i = 0;
                } else {
                    keyword.add(uniqueLetterList.get(i));
                }
            }

            //Find the correct colum depending on the key letter
            for (int i = 0; i < tempArr.length; i++) {
                for (int j = 0; j < tableArr.length; j++) {
                    if (tableArr[0][j] == keyword.get(i)) {
                        colIndex = j;
                        break;
                    }
                }

                //Find the correct row depending on the letter from the file
                for (int k = 0; k < tableArr.length; k++) {
                    if (tableArr[k][0] == tempArr[i]) {
                        rowIndex = k;
                        break;
                    }
                }
                //add the an encrypted letter from the vigenere table to a list
                encryptedFile.add(String.valueOf(tableArr[colIndex][rowIndex]));
            }
            keyword.clear();
        }

        return encryptedFile;
    }

}
