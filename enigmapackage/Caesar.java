package enigmapackage;

public class Caesar {
    public static void load(String text, String mode, int key) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

        switch (mode){
            case "-e":
            System.out.println("Encoded text: " + encodeCaesar(alphabet, text.toUpperCase(), key));
            break;
            case "-d":
            System.out.println("Decoded text: " + decodeCaesar(alphabet, text.toUpperCase(), key));
            break;
            default:
                System.out.println("Uknown mode");
        }
    }

    private static String encodeCaesar(char[] alphabet, String text, int key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (new String(alphabet).indexOf(text.charAt(i)) > -1) {
                int index_ = calculateIndex(alphabet, text, i, false, key);
                result += alphabet[index_];
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }

    private static String decodeCaesar(char[] alphabet, String text, int key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (new String(alphabet).indexOf(text.charAt(i)) > -1) {
                int index_ = calculateIndex(alphabet, text, i, true, key);
                result += alphabet[index_];
            } else {
                result += text.charAt(i);
            }
        }
        return result;
    }

    private static int calculateIndex(char[] alphabet, String text, int i, boolean decode, int key) {
        if(decode) {
            if ((new String(alphabet).indexOf(text.charAt(i)) - key) < 0){
                return alphabet.length + (new String(alphabet).indexOf(text.charAt(i)) - key);
            } else {
                return new String(alphabet).indexOf(text.charAt(i)) - key;
            }
        } else {
            if ((new String(alphabet).indexOf(text.charAt(i)) + key) >= alphabet.length){
                return key - (alphabet.length - new String(alphabet).indexOf(text.charAt(i)));
            } else {
                return new String(alphabet).indexOf(text.charAt(i)) + key;
            }
        }
    }
}