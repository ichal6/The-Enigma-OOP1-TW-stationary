import java.util.ArrayList;
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
        System.out.println("List of ciphers:");
        System.out.println("List of ciphers");
    }

    private static void loadCipher(String mode, String cipher, String key) {
        System.out.print("Please insert text: ");
        String text = readUserInput();
        switch(cipher){
            case "atbash":
                atbashCipher(text, mode);
                break;
            case "simplesub":
                simpleSubstitutionCipher(text, mode);
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

    private static void simpleSubstitutionCipher(String text, String mode)
    {
        text = text.toUpperCase();
        char[] textAsChar = text.toCharArray();
        String alphabet = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
        char[] alphabetAsChar = alphabet.toCharArray();
        String key = "DXSFZEHCVITPGAQLKJRUOWMYBN";
        char[] keyAsChar = key.toCharArray();
        String returnText = "";
        if(mode.equals("-e"))
        {
            
            for(char letter: textAsChar)
            {
                int index = 0;
                while(index < alphabet.length())
                {
                    if(letter == alphabetAsChar[index])
                    {
                        returnText += key.charAt(index);
                        break;
                    }
                    index++;

                    if(index == alphabet.length())
                    {
                        returnText += letter;
                    }
                
                }
                
            }
             System.out.println(returnText);   
        }
        else
        {
            for(char letter: textAsChar)
            {
                int index = 0;
                while(index < alphabet.length())
                {
                    if(letter == keyAsChar[index])
                    {
                        returnText += alphabet.charAt(index);
                        break;
                    }
                    index++;

                    if(index == alphabet.length())
                    {
                        returnText += letter;
                    }
                
                }
                
            }
             System.out.println(returnText);  
        }
    }

}