package enigmapackage;

import java.awt.Robot;
import java.util.Arrays;

public class Playfair {
    public static void playfairCipher(String text, String mode) {
        String abc = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String outPutText = "";
        String cipherKey = "MONARCHY"; // key may be default or not
        text = text.replaceAll("j","i").toUpperCase();
        int abcLength = abc.length();
        String charsToRemove = "., !:;?";
        String[][] cipherTable = new String[5][5];
        // generating cipher key
        for (int i = 0; i < abcLength; i++) {
            if (!cipherKey.contains(Character.toString(abc.charAt(i)))) {
                cipherKey += abc.charAt(i);
            }
        }
        // creating table 5x5
        generateCipherMatrix(cipherTable, cipherKey);
        // removing punctuation and empty chars
        for (int i = 0; i < charsToRemove.length(); i++) {
            if (text.contains((Character.toString(charsToRemove.charAt(i))))) {
                text = text.replace(Character.toString(charsToRemove.charAt(i)), "");
            }
        }

        // adding 'x' at the end if needed
        if(mode.equals("-e")){
            if (text.length() % 2 != 0) {
                text += "X";
            }
        }
        // dividing into pairs
        String[] textPairs = splitTextIntoPairs(text);

        // main algorythm
        if(mode.equals("-e")){
            // replacing second double char with 'x'
            for(String pair: textPairs) {
                if(pair.charAt(0) == pair.charAt(1)){
                    String newPair = "" + pair.charAt(0) + 'X';
                    textPairs[Arrays.asList(textPairs).indexOf(pair)] = newPair;
                }
            }
            // Encrypt
            encrypt(textPairs, cipherTable);
        }
        else{
            // Decrypt
            decrypt(textPairs, cipherTable);
        }
    }

    private static void generateCipherMatrix(String[][] cipherTable, String cipherKey) {
        for (int row = 0, counter = 0; row < 5; row++) {
            for (int cell = 0; cell < 5; cell++, counter++) {
                cipherTable[row][cell] = Character.toString(cipherKey.charAt(counter));
            }
        }
    }

    private static String[] splitTextIntoPairs(String text) {
        String[] textPairs = new String[text.length() / 2];
        
        for (int i = 0, counter = 0; i < text.length() / 2; i++, counter += 2) {
            textPairs[i] = text.substring(counter, counter + 2);
        }
        return textPairs;
    }

    private static void decrypt(String[] letterPairs, String[][] cipherTable) {
        String result = "";
        for (String pair: letterPairs){
            String char1 = "" + pair.charAt(0);
            String char2 = "" + pair.charAt(1);

            if((!checkRow(char1, char2, cipherTable)) && (!checkColumn(char1, char2, cipherTable))){
                result += findRow(char1, cipherTable)[Arrays.asList(findRow(char2, cipherTable)).indexOf(char2)];
                result += findRow(char2, cipherTable)[Arrays.asList(findRow(char1, cipherTable)).indexOf(char1)];
            } else if(checkRow(char1, char2, cipherTable)){
                result += findRow(char1, cipherTable)[calculateIndex("-d", findRow(char1, cipherTable), char1)];
                result += findRow(char2, cipherTable)[calculateIndex("-d", findRow(char2, cipherTable), char2)];
            } else if(checkColumn(char1, char2, cipherTable)){
                result += findColumn("-d", char1, cipherTable)[Arrays.asList(findRow(char1, cipherTable)).indexOf(char1)];
                result += findColumn("-d", char2, cipherTable)[Arrays.asList(findRow(char2, cipherTable)).indexOf(char2)];
            }
        }
        // Remove X from the end
        if (result.charAt(result.length()-1) == 'X') {
            result = result.substring(0, result.length() - 1);
        }
        // Restore double letters
        if (result.indexOf("X") != -1) {
            result = result.replace('X', result.charAt(result.indexOf("X")-1));
        }

        System.out.println("Decoded text: " + result);
    }

    private static void encrypt(String[] letterPairs, String[][] cipherTable) {
        String result = "";
        for (String pair: letterPairs){
            String char1 = "" + pair.charAt(0);
            String char2 = "" + pair.charAt(1);

            if((!checkRow(char1, char2, cipherTable)) && (!checkColumn(char1, char2, cipherTable))){
                result += findRow(char1, cipherTable)[Arrays.asList(findRow(char2, cipherTable)).indexOf(char2)];
                result += findRow(char2, cipherTable)[Arrays.asList(findRow(char1, cipherTable)).indexOf(char1)];
            } else if(checkRow(char1, char2, cipherTable)){
                result += findRow(char1, cipherTable)[calculateIndex("-e", findRow(char1, cipherTable), char1)];
                result += findRow(char2, cipherTable)[calculateIndex("-e", findRow(char2, cipherTable), char2)];
            } else if(checkColumn(char1, char2, cipherTable)){
                result += findColumn("-e", char1, cipherTable)[Arrays.asList(findRow(char1, cipherTable)).indexOf(char1)];
                result += findColumn("-e", char2, cipherTable)[Arrays.asList(findRow(char2, cipherTable)).indexOf(char2)];
            }
        }
        System.out.println("Encoded text: " + result);
    }

    private static String[] findRow(String character, String[][] cipherTable) {
        for(String[] row: cipherTable){
            if(Arrays.asList(row).indexOf(character) != -1) {
                return row;
            }  
        }
        String[] empty = {""};
        return empty;
    }

    private static String[] findColumn(String mode, String character, String[][] cipherTable) {
        switch(mode){
            case "-e":
                if((Arrays.asList(cipherTable).indexOf(findRow(character, cipherTable)) + 1) < cipherTable.length){
                    return cipherTable[Arrays.asList(cipherTable).indexOf(findRow(character, cipherTable)) + 1];
                } else {
                    return cipherTable[0];
                }
            case "-d":
                if(Arrays.asList(cipherTable).indexOf(findRow(character, cipherTable)) != 0){
                    return cipherTable[Arrays.asList(cipherTable).indexOf(findRow(character, cipherTable)) - 1];
                } else {
                    return cipherTable[4];
                }
        }
        String[] empty = {""};
        return empty;
    }



    private static int calculateIndex(String mode, String[] row, String character) {
        switch(mode){
            case "-e":
                if(Arrays.asList(row).indexOf(character) + 1 < row.length){
                    return Arrays.asList(row).indexOf(character) + 1;
                } else {
                    return 0;
                }
            case "-d":
                if(Arrays.asList(row).indexOf(character) != 0){
                    return Arrays.asList(row).indexOf(character) - 1;
                } else {
                    return 4;
                }
        }
        return -1;
    }


    private static boolean checkRow(String char1, String char2, String[][] cipherTable){
        for(String[] row: cipherTable){
            if((Arrays.asList(row).indexOf(char1) != -1) && (Arrays.asList(row).indexOf(char2) != -1)){
                return true;
            }  
        }
        return false;
    }

    private static boolean checkColumn(String char1, String char2, String[][] cipherTable){
        int indexChar1 = 0;
        int indexChar2 = 0;
        for(String[] row: cipherTable){

            if(Arrays.asList(row).indexOf(char1) != -1){
                indexChar1 = Arrays.asList(row).indexOf(char1);
            }
            if(Arrays.asList(row).indexOf(char2) != -1){
                indexChar2 = Arrays.asList(row).indexOf(char2);
            } 
        }

        if(indexChar1 == indexChar2) {
            return true;
        } else{
            return false;
        }
    }
}
