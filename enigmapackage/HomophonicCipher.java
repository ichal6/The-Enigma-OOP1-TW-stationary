import java.util.Random;

public class HomophonicCipher{
    public static void main()
    {
        
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