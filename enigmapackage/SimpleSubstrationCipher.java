package enigmapackage;

public class SimpleSubstrationCipher {
    public static void main(String[] args) {

    }

    public static void simpleSubstitutionCipher(String text, String mode, String key)
    {
        //key = "DXSFZEHCVITPGAQLKJRUOWMYBN";
        key = key.toUpperCase();
        text = text.toUpperCase();
        char[] textAsChar = text.toCharArray();

        String alphabet = "ABCDEFGHIJKLMNOPRSTUVWXYZ";

        String returnText = "";

        if(mode.equals("-e"))
        {
            returnText = simpleSubstationEncripher(alphabet, textAsChar, key);
            System.out.println(returnText);   
        }
        else
        {
            returnText = simpleSubstationDecripher(alphabet, textAsChar, key);
            System.out.println(returnText);  
        }
    }

    private static String simpleSubstationEncripher(String alphabet, char[] textAsChar, String key)
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

    private static String simpleSubstationDecripher(String alphabet, char[] textAsChar, String key)
    {
        String returnText = "";
        char[] keyAsChar = key.toCharArray();
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
        return returnText;
    }
}