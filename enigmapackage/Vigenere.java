package enigmapackage;

public class Vigenere {

    public static void vigenere(String inputText, String mode, String key) {
        key = key.toLowerCase();
        char[][] matrix = new char[26][26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        String text = inputText.replace(" ", "").toLowerCase();

        for (int i = 0; i < matrix.length; i++) {
            if(i == 0){
                matrix[i] = alphabet.subSequence(i, matrix.length).toString().toCharArray();
            } else {
                matrix[i] = (alphabet.subSequence(i, matrix.length).toString() + alphabet.subSequence(0, i).toString()).toCharArray();
            }
        }

        char[] keyArray = new char[text.length()];
        char[] textArray = text.toCharArray();

        for (int i = 0; i < textArray.length; i++) {
            int index = calculateIndex(i, key.length());
            keyArray[i] = key.charAt(index);
        }

        switch (mode){
            case "-e":
            System.out.println("Encoded text: " + encodeVigenere(matrix, textArray, keyArray, alphabet));
            break;
            case "-d":
            System.out.println("Decoded text: " + decodeVigenere(matrix, textArray, keyArray, alphabet));
            break;
            default:
                System.out.println("Uknown mode");
        }
    }

    private static String encodeVigenere(char[][] matrix, char[] textArray, char[] keyArray, String alphabet) {
        String result = "";
        for (int i = 0; i < textArray.length; i++) {
            result += matrix[alphabet.indexOf(textArray[i])][alphabet.indexOf(keyArray[i])]; 
        }
        return result;
    }

    private static String decodeVigenere(char[][] matrix, char[] textArray, char[] keyArray, String alphabet) {
        String result = "";
        for (int i = 0; i < textArray.length; i++) {
            String column = new String(matrix[alphabet.indexOf(keyArray[i])]);
            int characterIndex = column.indexOf(textArray[i]);
            result += alphabet.charAt(characterIndex);
        }
        return result;
    }

    private static int calculateIndex(int i, int keyLenght){
        while(i >= keyLenght){
            i -= keyLenght;
        }
        return i;
    }
}