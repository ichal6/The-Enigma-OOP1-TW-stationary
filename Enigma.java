import java.util.Arrays;
import java.util.Scanner;

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
                chooseCipher(mode, cipher);
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

    private static void chooseCipher(String mode, String cipher) {
        System.out.print("Please insert text: ");
        String text = readUserInput();
        switch(cipher){
            case "atbash":
                atbashCipher(text, mode);
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

    private static String readUserInput(){
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }

}