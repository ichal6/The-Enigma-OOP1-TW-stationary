package enigmapackage;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;


import enigmapackage.HomophonicCipher;

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
            case "ADFGX":
                ADFGX.adfgxCipher(text, mode, key);
                break;
            case "ROT13":
                Rot13.rot13(text, mode);
                break;
            case "CAESAR":
                Caesar.load(text, mode, Integer.parseInt(key));
                break;
            case "HOMOPHONIC":
                HomophonicCipher.homophonicCipher(text, mode);
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

    private static String readUserInput(){
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }
}