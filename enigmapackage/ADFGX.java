package enigmapackage;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class ADFGX {
    public static void adfgxCipher(String inputText, String mode, String key) {
        char[][] keySquare = {{'p', 'h', 'q', 'g', 'm'}, {'e', 'a', 'y', 'n', 'o'}, {'f', 'd', 'x', 'k', 'r'}, {'c', 'v', 's', 'z', 'w'}, {'b', 'u', 't', 'i', 'l'}};
        String text = inputText.replaceAll(" ", "");
        
        String result = "";
        HashMap<Integer, Character> hashMap = new HashMap<>();
        hashMap.put(0, 'A');
        hashMap.put(1, 'D');
        hashMap.put(2, 'F');
        hashMap.put(3, 'G');
        hashMap.put(4, 'X');

        List<List<Character>> listOfLists = new ArrayList<List<Character>>();
        for (int i = 0; i < key.length(); i++) {
            listOfLists.add(new ArrayList<Character>());
        }

        String sortedKey = sortString(key);

        if(mode.equals("-e")){
            // Encrypt
            char[] sortedKeyArray = sortedKey.toCharArray();

            HashMap<Character, Integer> keyMap = new HashMap<>();
            for(char ch: key.toCharArray()) {
                keyMap.put(ch, key.indexOf(ch));
            }

            for (int i = 0; i < text.length(); i++) {
                result += getY(hashMap, keySquare, text.charAt(i));
                result += getX(hashMap, keySquare, text.charAt(i));
            }

            for (int i = 0; i < result.length(); i++) {
                int numberOflist = calculateListNumber(i, key.length());
                listOfLists.get(numberOflist).add(result.charAt(i));
            }

            String finalResult = "";
            for (int i = 0; i < listOfLists.size(); i++) {
                finalResult += getStringRepresentation(listOfLists.get(keyMap.get(sortedKeyArray[i])));
            }
            System.out.println(finalResult);
        }
        else{
            // Decrypt
            char[] unsortedKeyArray = key.toCharArray();
            HashMap<Character, Integer> unsortedKeyMap = new HashMap<>();
            for(char ch: sortedKey.toCharArray()) {
                unsortedKeyMap.put(ch, sortedKey.indexOf(ch));
            }
            System.out.println(text);
            int j = 0;
            for (int i = 0; i < text.length(); i += 2) {   
                listOfLists.get(j).add(text.charAt(i));
                listOfLists.get(j).add(text.charAt(i+1));
                j += 1;
            }
            System.out.println(listOfLists);


            //String encryptedText = "";
            List<List<Character>> encryptedText = new ArrayList<List<Character>>();
            for (int i = 0; i < listOfLists.size(); i++) {
                System.out.println(unsortedKeyMap.get(unsortedKeyArray[i]));     
                encryptedText.add(listOfLists.get(unsortedKeyMap.get(unsortedKeyArray[i])));
            }
            System.out.println(unsortedKeyArray);        
            System.out.println(encryptedText);

            String tempText = "";
            for (int i = 0; i < encryptedText.get(i).size(); i++) {
                for (int k = 0; k < key.length(); k++) {
                    tempText += encryptedText.get(k).get(i);
                }
            }
            System.out.println(tempText);


            for (int i = 0; i < tempText.length(); i += 2) {

                int y = getKey(hashMap, tempText.charAt(i));
                int x = getKey(hashMap, tempText.charAt(i+1));

                result += keySquare[y][x];
            }
            System.out.println(result);
        }
    }

    public static String getStringRepresentation(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }

    public static String sortString(String text) {
        char[] temp = text.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }

    public static int calculateListNumber(int characterIndex, int keyLenght) {
        while(characterIndex >= keyLenght) {
            characterIndex -= keyLenght;
        }
        return characterIndex;
    }

    public static int calculateDecodeListNumber(int characterIndex, int keyLenght) {
        while(characterIndex >= keyLenght) {
            characterIndex -= keyLenght;
        }
        return characterIndex;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
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