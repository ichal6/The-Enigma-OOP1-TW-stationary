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
            int numberOfRowsMin = (int)Math.floor((double)(text.length()) / (double)(key.length()));
            int numberOfRowsMax = (int)Math.ceil((double)(text.length()) / (double)(key.length()));
            int amountOfFullLists = (text.length()) % (key.length());
            int amountOfSmallerLists = key.length() - amountOfFullLists;
            System.out.println("Amount of smaller lits: " + amountOfSmallerLists);


            
            char[] keyAsArray = key.toCharArray();
            HashMap<Character, Integer> sortedKeyMap = new HashMap<>();
            for(char ch: sortedKey.toCharArray()) {
                sortedKeyMap.put(ch, sortedKey.indexOf(ch));
            }
            HashMap<Character, Integer> columnLenghtMap = new HashMap<>();
            for(char ch: key.toCharArray()) {
                System.out.println("Index of character in key: " + key.indexOf(ch));
                System.out.println("Index of last Max list: " + (key.length() - amountOfSmallerLists));
                if(key.indexOf(ch) >= (key.length() - amountOfSmallerLists)) {
                    System.out.println("Adding Min to dict");
                    columnLenghtMap.put(ch, numberOfRowsMin);
                } else {
                    System.out.println("Adding Max to dict");
                    columnLenghtMap.put(ch, numberOfRowsMax);
                }
            }
            System.out.println(text);

            int z = 0;
            int characterNumber = 0;
            for(List<Character> list: listOfLists) {
                if (columnLenghtMap.get(sortedKey.charAt(z)) == numberOfRowsMin){
                    System.out.println("Character in Key:" + sortedKey.charAt(z));
                    System.out.println("This array is size:" + numberOfRowsMin);
                    for (int j = 0; j < numberOfRowsMin; j++, characterNumber++) {
                        list.add(text.charAt(characterNumber));
                    }
                } else {
                    System.out.println("Character in Key:" + sortedKey.charAt(z));
                    System.out.println("This array is size:" + numberOfRowsMax);
                    for (int j = 0; j < numberOfRowsMax; j++, characterNumber++) {
                        list.add(text.charAt(characterNumber));
                    }
                }
                z++;
            }
            System.out.println(amountOfFullLists);
            System.out.println(numberOfRowsMax);
            System.out.println(numberOfRowsMin);
            System.out.print("List: ");
            System.out.println(listOfLists);
            //int z = 0;


            //System.out.println(numberOfRows);
            // for (int i = 0, j = key.length(); i < text.length(); i += numberOfRows, j++) {
            //     for (int k = 0; k < numberOfRows; k++, z++) {
            //         try{
            //         listOfLists.get(j-key.length()).add(text.charAt(z));
            //         } catch(Exception e){
            //             break;
            //         }
            //     }
            // }



            // int z = 0;
            // int max = 0;
            // for (int i = 0; i < key.length(); i++, amountOfFullLists--) {
                
            //     if(amountOfFullLists > 0){
                    
            //         max += numberOfRowsMax;
                    
                    
            //         for (int k = z; k < max; k++, z = k) {
            //             try {
            //                 listOfLists.get(i).add(text.charAt(k));                    
            //             } catch (Exception e){
            //                 break;
            //             }
                        
            //         }
            //         //z += numberOfRowsMax;
            //     } else {
                    
            //         max += numberOfRowsMin;
                    
                    
            //         for (int k = z; k < max; k++, z = k) {
            //             try {
            //                 listOfLists.get(i).add(text.charAt(k));                    
            //             } catch (Exception e){
            //                 break;
            //             }
                                    
            //             }
            //            // z += numberOfRowsMin;
            //     }
            // }
            // int indexOfCharactersLeft = numberOfRows*key.length();
            // for (int i = 0, index = indexOfCharactersLeft; index < text.length(); i++, index++) {
            //     listOfLists.get(i).add(text.charAt(index));
            // }





            //String encryptedText = "";
            List<List<Character>> encryptedText = new ArrayList<List<Character>>();
            for (int i = 0; i < listOfLists.size(); i++) {
                System.out.println(sortedKeyMap.get(keyAsArray[i]));     
                encryptedText.add(listOfLists.get(sortedKeyMap.get(keyAsArray[i])));
            }
            System.out.println(keyAsArray);        
            System.out.println(encryptedText);

            String tempText = "";
            // for (int i = 0; i < encryptedText.get(i).size(); i++) {
            //     for (int k = 0; k < key.length(); k++) {
            //         try {
            //             tempText += encryptedText.get(k).get(i);                   
            //         } catch (Exception e){
            //             continue;
            //         }

            //     }
            // }
            for (int characterToBeAdded = 0; characterToBeAdded < numberOfRowsMax; characterToBeAdded++) {
                for(List<Character> listEncrypted: encryptedText){
                    try{
                        tempText += listEncrypted.get(characterToBeAdded);
                    } catch (Exception e){
                        continue;
                    }
                }
            }
            System.out.println(tempText);

            for (int i = 0; i + 1 < tempText.length(); i += 2) {

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