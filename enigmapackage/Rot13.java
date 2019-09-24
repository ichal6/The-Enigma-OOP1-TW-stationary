package enigmapackage;

public class Rot13 {
    public static void rot13(String text, String mode) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

        switch (mode){
            case "-e":
            System.out.println("Encoded text: " + encodeRot13(alphabet, text.toUpperCase()));
            break;
            case "-d":
            System.out.println("Decoded text: " + decodeRot13(alphabet, text.toUpperCase()));
            break;
            default:
                System.out.println("Uknown mode");
        }
    }

    private static String encodeRot13(char[] alphabet, String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (new String(alphabet).indexOf(text.charAt(i)) > -1) {
                int index_ = calculateIndex(alphabet, text, i, false);
                result += alphabet[index_];
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }

    private static String decodeRot13(char[] alphabet, String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (new String(alphabet).indexOf(text.charAt(i)) > -1) {
                int index_ = calculateIndex(alphabet, text, i, true);
                result += alphabet[index_];
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }

    private static int calculateIndex(char[] alphabet, String text, int i, boolean decode) {
        if(decode) {
            if ((new String(alphabet).indexOf(text.charAt(i)) - 13) < 0){
                return alphabet.length + (new String(alphabet).indexOf(text.charAt(i)) - 13);
            } else {
                return new String(alphabet).indexOf(text.charAt(i)) - 13;
            }
        } else {
            if ((new String(alphabet).indexOf(text.charAt(i)) + 13) >= alphabet.length){
                return 13 - (alphabet.length - new String(alphabet).indexOf(text.charAt(i)));
            } else {
                return new String(alphabet).indexOf(text.charAt(i)) + 13;
            }
        }
    }
}