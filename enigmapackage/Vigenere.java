public class Vigenere {

    public static void vigenere(String text, String mode) {
        char[][] matrix = new char[26][26];


        switch (mode){
            case "-e":
            System.out.println("Encoded text: " + encodeVigenere(matrix, text));
            break;
            case "-d":
            //System.out.println("Decoded text: " + decodeVigenere(text));
            break;
            default:
                System.out.println("Uknown mode");
        }
    }

    private static String encodeVigenere(char[][] matrix, String text) {
        String result = "";
        return result;
    }
}