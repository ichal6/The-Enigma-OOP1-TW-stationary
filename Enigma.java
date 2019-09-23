import java.util.Arrays;

public class Enigma {
    public static void main(String[] args) {
        System.out.println("The Enigma");

        String mode = args[0];
        String cipher = args[1];
        String key;

        if(args.length > 2) {
            key = args[2];
        }

        switch(mode){
            case "-e":
                System.out.println("Encipher mode");
                break;
            case "-d":
                System.out.println("Decipher mode");
                break;
            case "-l":
                listAvailableCiphers();
                break;
        }

    }

    private static void listAvailableCiphers() {
        System.out.println("List of ciphers");
    }

    private static String testCipher(String text, String mode) {

    }
}