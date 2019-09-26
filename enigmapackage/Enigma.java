package enigmapackage;

import java.util.Scanner;

public class Enigma {
    public static void main(String[] args) {
        System.out.println("The Enigma");
        String mode = "-e";

        try{
            mode = args[0];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            
        }
        
        String cipher = "HOMOPHONIC";
        String key = "";

        if (args.length > 1)
        {
            cipher = args[1]; 
        }
        else if(args.length > 2) 
        {
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
            case "ATBASH":
                Atbash.atbashCipher(text, mode);
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
            case "SIMPLESUBSTRATION":
                SimpleSubstrationCipher.simpleSubstitutionCipher(text, mode, key);
                break;
            case "BACONIAN":
                Baconian.BaconianCipher(text, mode);
                break;
            case "PLAYFAIR":
                Playfair.playfairCipher(text, mode);
                break;
            default:
                System.out.println("Cipher not supported");
        }
    }

    private static String readUserInput(){
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return text;
    }

}