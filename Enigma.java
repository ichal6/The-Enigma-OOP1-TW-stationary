import java.util.Arrays;
import java.util.Scanner;

public class Enigma {
    public static void main(String[] args) {
        System.out.println("The Enigma");

        String mode = args[0];
        String cipher = args[1];
        String key = "";

        if(args.length > 2) {
            key = args[2];
        }

        switch(mode){
            case "-e":
            case "-d":
                loadCipher(mode, cipher, key);
                System.out.println("Do you want to continue?[Y]");
                String answer = readUserInput();
                answer = answer.toLowerCase();
                if (answer.equals("y"))
                {
                    main(args);
                }
                break;
            case "-l":
                listAvailableCiphers();
                break;
            default:
                System.out.println("Option not supported. Try: -e | -d | -l");
        }

    }

    private static void listAvailableCiphers() {
        System.out.println("List of ciphers");
        System.out.println("List of ciphers");
    }

    private static void loadCipher(String mode, String cipher, String key) {
        System.out.print("Please insert text: ");
        String text = readUserInput();
        switch(cipher){
            case "atbash":
                atbashCipher(text, mode);
                break;
            case "ROT13":
                rot13(text, mode);
                break;
            default:
                System.out.println("Cipher not supported");
        }
    }

    private static void atbashCipher(String text, String mode){
        if(mode.equals("-e")){
            System.out.println(text);
        }
        else{
            System.out.println(text);
        }
        
    }

    private static void rot13(String text, String mode) {
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

    private static String readUserInput(){
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }

}