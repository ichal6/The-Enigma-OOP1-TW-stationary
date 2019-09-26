package enigmapackage;

import java.awt.Robot;
import java.util.Arrays;

public class Playfair {
    public static void playfairCipher(String text, String mode) {
        String abc = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String outPutText = "";
        String cipherKey = "MONARCHY"; // key may be default or not
        text = text.toUpperCase();
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
        int counter = 0;
        for (int row = 0; row < 5; row++) {
            for (int cell = 0; cell < 5; cell++) {
                cipherTable[row][cell] = Character.toString(cipherKey.charAt(counter));
                counter++;
            }
        }
        // removing punctuation and empty chars
        for (int i = 0; i < charsToRemove.length(); i++) {
            if (text.contains((Character.toString(charsToRemove.charAt(i))))) {
                text = text.replace(Character.toString(charsToRemove.charAt(i)), "");
            }
        }

        // replacing second double char with 'x'
        int length = (int) text.length() / 2 + text.length() % 2;
        for (int i = 0; i < (length - 1); i++) {
            if (text.charAt(2 * i) == text.charAt(2 * i + 1)) {
                int secondDoubleIndex = text.indexOf(text.charAt(2 * i + 1)) + 1;
                char[] textChars = text.toCharArray();
                String temporaryCharReplacer = "X";
                char charReplacer = temporaryCharReplacer.charAt(0);
                textChars[secondDoubleIndex] = charReplacer;
                length = (int) text.length() / 2 + text.length() % 2;
                text = new String(textChars);
            }
        }

        // adding 'x' at the end if needed
        if (text.length() % 2 != 0) {
            text += "X";
        }

        // dividing into pairs
        String[] textPairs = new String[text.length() / 2];
        counter = 0;
        for (int i = 0; i < text.length() / 2; i++) {
            textPairs[i] = text.substring(counter, counter + 2);
            counter = counter + 2;
        }

        // main algorythm
        for (int i = 0; i < textPairs.length; i++) {
            String char1 = String.valueOf(textPairs[i].charAt(0));
            String char2 = String.valueOf(textPairs[i].charAt(1));
            
            for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
                int char1Index = Arrays.asList(cipherTable[rowIndex]).indexOf(char1);
                int char2Index = Arrays.asList(cipherTable[rowIndex]).indexOf(char2);
                if (Arrays.asList(cipherTable[rowIndex]).contains(char1) && Arrays.asList(cipherTable[rowIndex]).contains(char2)) {
                    if (char1Index+1 < 5 && char2Index+1 < 5) {
                        outPutText += cipherTable[rowIndex][char1Index+1];
                        outPutText += cipherTable[rowIndex][char2Index+1];
                    } else if (char1Index+1 < 5 && char2Index+1 > 5) {
                        outPutText += cipherTable[rowIndex][char1Index]+1;
                        outPutText += cipherTable[rowIndex][0];
                    } else if (char1Index+1 > 5 && char2Index+1 < 5) {
                        outPutText += cipherTable[rowIndex][0];
                        outPutText += cipherTable[rowIndex][char2Index+1];
                    }
                } else if (Arrays.asList(cipherTable[rowIndex]).indexOf(char1) == Arrays.asList(cipherTable[rowIndex]).indexOf(char1) && Arrays.asList(cipherTable[rowIndex]).indexOf(char1) >= 0) {
                    if (rowIndex+1 < 4) {
                        outPutText += cipherTable[rowIndex+1][char1Index];
                        outPutText += cipherTable[rowIndex+2][char1Index];
                    } else if (rowIndex+1 >= 4) {
                        outPutText += cipherTable[rowIndex+1][char1Index];
                        outPutText += cipherTable[0][char1Index];
                    }
                } else {
                    System.out.println(char1Index);
                    System.out.println(char2Index);
                }
            }

            System.out.println(Arrays.deepToString(cipherTable));
            System.out.println(Arrays.deepToString(textPairs));
            System.out.println(outPutText);
        }
    }
}
