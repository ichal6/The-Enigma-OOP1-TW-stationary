package enigmapackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Baconian {
    public static void BaconianCipher(String text, String mode) {
        String outputText = "";
        Map<String, String> cipherKeys = new HashMap();
        cipherKeys.put("A", "aaaaa");
        cipherKeys.put("B", "aaaab");
        cipherKeys.put("C", "aaaba");
        cipherKeys.put("D", "aaabb");
        cipherKeys.put("E", "aabaa");
        cipherKeys.put("F", "aabab");
        cipherKeys.put("G", "aabba");
        cipherKeys.put("H", "aabbb");
        cipherKeys.put("I", "abaaa");
        cipherKeys.put("K", "abaab");
        cipherKeys.put("L", "ababa");
        cipherKeys.put("M", "ababb");
        cipherKeys.put("N", "abbaa");
        cipherKeys.put("O", "abbab");
        cipherKeys.put("P", "abbba");
        cipherKeys.put("Q", "abbbb");
        cipherKeys.put("R", "baaaa");
        cipherKeys.put("S", "baaab");
        cipherKeys.put("T", "baaba");
        cipherKeys.put("U", "baabb");
        cipherKeys.put("W", "babaa");
        cipherKeys.put("X", "babab");
        cipherKeys.put("Y", "babba");
        cipherKeys.put("Z", "babbb");

        text = text.toUpperCase();

        if (mode.equals("-e")) {
            for (int index = 0; index < text.length(); index++) {
                if (String.valueOf(text.charAt(index)).equals(" ")) {
                    outputText += "";
                } else if (String.valueOf(text.charAt(index)).equals("V")) {
                    outputText += "baabb ";
                } else if (String.valueOf(text.charAt(index)).equals("J")) {
                    outputText += "abaaa ";
                } else {
                    outputText += (cipherKeys.get(String.valueOf(text.charAt(index))) + " ");
                }
            }
        } else {
            String[] cipherArray = text.split(" "); 
            for (int index = 0; index < cipherArray.length; index++) {
                for (Entry<String, String> entry : cipherKeys.entrySet()) {
                    if (entry.getValue().toUpperCase().equals(cipherArray[index])) {
                        outputText += entry.getKey();
                    }
                }
            }
        }
        System.out.println(outputText);
    }
}