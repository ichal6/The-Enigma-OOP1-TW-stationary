import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;


public class Enigma {
    public static void main(String[] args) {
        System.out.println("The Enigma");
        homophonicCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "-e");
        homophonicCipher("DXSF2EHCVITPGAQLKJRUOWMYBN", "-d");
        /*
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
        */

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

    private static void homophonicCipher(String text, String mode)
    {
        Random rand = new Random();
        int randomKey = rand.nextInt(3);

        text = text.toUpperCase();
        char[] textAsChar = text.toCharArray();

        String alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String[] keys = {"DXSFZEHCVITPGAQLKJRUOWMYBN", "9XSF7EHC3ITPG50LKJ46OWMYBN",
                         "DXSF2EHCVITPGAQLKJRUOWMYBN", "DXSF1EHCVITPGAQLKJRUOWMYBN"};
        String key = keys[randomKey];

        String returnText = "";

        if(mode.equals("-e"))
        {
            returnText = homophonicCipherEncipher(textAsChar, alphabet, key);
            System.out.println(returnText);   
        }
        else
        {
            returnText = homophonicCipherDecipher(textAsChar, alphabet, keys);
            System.out.println(returnText);  
        }
    }

    static private String homophonicCipherEncipher(char[] textAsChar, String alphabet, String key)
    {
        char[] alphabetAsChar = alphabet.toCharArray();
        String returnText = "";
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
        return returnText;
    }

    static private String homophonicCipherDecipher(char[] textAsChar, String alphabet, String[] keys)
    {
        String returnText = "";
        int indexKeys = 0;
        String key = keys[indexKeys];
        char[] keyAsChar = key.toCharArray();
        for(char letter: textAsChar)
        {
            int index = 0;
            indexKeys = 0;
            while(index < alphabet.length())
            {
                if(letter == keyAsChar[index])
                {
                    returnText += alphabet.charAt(index);
                    indexKeys = 0;
                    break;
                }
                index++;

                if(indexKeys > 2)
                {
                    returnText += letter;
                    break;
                }

                if(index == alphabet.length())
                {
                    key = keys[++indexKeys];
                    keyAsChar = key.toCharArray();
                    index = 0;
                }
            }
            
        }
        return returnText;
    }
}