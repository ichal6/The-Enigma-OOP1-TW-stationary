import java.util.ArrayList;
import java.util.List;
package enigmapackage;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

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
                adfgxCipher(text, mode, key);
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

    private static void adfgxCipher(String text, String mode, String key) {
        char[][] keySquare = {{'p', 'h', 'q', 'g', 'm'}, {'e', 'a', 'y', 'n', 'o'}, {'f', 'd', 'x', 'k', 'r'}, {'c', 'v', 's', 'z', 'w'}, {'b', 'u', 't', 'i', 'l'}};
        
        List<Character> result = new ArrayList<>();
        HashMap<Integer, Character> hashMap = new HashMap<>();
        hashMap.put(0, 'A');
        hashMap.put(1, 'D');
        hashMap.put(2, 'F');
        hashMap.put(3, 'G');
        hashMap.put(4, 'X');

        if(mode.equals("-e")){
            for (int i = 0; i < text.length(); i++) {
                result.add(getY(hashMap, keySquare, text.charAt(i)));
                result.add(getX(hashMap, keySquare, text.charAt(i)));
            }
            System.out.println(result);
            System.out.println(key);
            // while (result.size() != 0){
            //     char x = result.get(0);
            //     result.remove(0);
            //     char y = result.get(0);
            //     char[] temp = {}
            // }
            

        }
        else{
            System.out.println(text);
        }
    }

    private static char getY( HashMap<Integer, Character> hashMap, char[][] keySquare,char character_){

        for (char[] temp: keySquare){
            if(new String(temp).indexOf(character_) != -1){
                return hashMap.get(Arrays.asList(keySquare).indexOf(temp));
            }
        }
        return '-';
    }

    private static char getX( HashMap<Integer, Character> hashMap, char[][] keySquare,char character_){

        for (char[] temp: keySquare){
            int index_ = new String(temp).indexOf(character_);
            if(index_ != -1){
                return hashMap.get(index_);
            }
        }
        return '-';
    }

}