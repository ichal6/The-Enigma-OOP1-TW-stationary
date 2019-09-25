package enigmapackage;

public class Atbash {
    public static void atbashCipher(String text, String mode) {
        String cipherKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder reversedCipherKey = new StringBuilder();
        reversedCipherKey.append(cipherKey);
        reversedCipherKey = reversedCipherKey.reverse();
        String encryptedText = "";
        text = text.toUpperCase();
        int textLength = text.length();
        if (mode.equals("-e")) {
            for (int i = 0; i < textLength; i++) {
                String textChar = Character.toString(text.charAt(i));
                if (!textChar.equals(" ")) {
                    int letterIndex = cipherKey.indexOf(textChar);
                    encryptedText += reversedCipherKey.charAt(letterIndex);
                } else {
                    encryptedText += " ";
                }
            }
        } else {
            for (int i = 0; i < textLength; i++) {
                String textChar = Character.toString(text.charAt(i));
                if (!textChar.equals(" ")) {
                    int letterIndex = reversedCipherKey.indexOf(textChar);
                    encryptedText += cipherKey.charAt(letterIndex);
                } else {
                    encryptedText += " ";
                }
            }
        }
        System.out.println(encryptedText);
    }
}