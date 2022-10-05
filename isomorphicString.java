/*Algorithm
We define a dictionary mapping_s_t which will be used to map characters in string s to characters in string t and another dictionary mapping_t_s which will be used to map characters in string t to characters in string s.
Next, we iterate over the two strings one character at a time.
Let's assume the character in the first string is c1 and the corresponding character in the second string is c2.
If c1 does not have a mapping in mapping_s_t and c2 does not have a mapping in mapping_t_s, we add the corresponding mappings in both the dictionaries and move on to the next character.
At this point, we expect both the character mappings to exist in the dictionaries and their values should be mapping_s_t[c1] = c2 and mapping_t_s[c2] = c1. If either of these conditions fails (c1 is not in the dictionary, c2 is not in the dictionary, unexpected mapping), we return false.
Return true once both the strings have been exhausted.
*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        
        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);
        
        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);
        
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            
            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }
            
            // Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both 
            else if (!(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)) {
                return false;
            }
        }
        
        return true;
    }
}


//Alternate

/*Algorithm
Define a function called transform that takes a string as an input and returns a new string with modifications as explained in the intuition section.
We maintain a dictionary to store the character to index mapping for the given string.
For each character, we look up the mapping in the dictionary. If there is a mapping, that means this character already has its first occurrence recorded and we simply use the first occurrence's index in the new string. Otherwise, we use the current index for the first occurrence.
We find the transformed strings for both of our input strings. Let's say the transformed strings are s1 and s2 respectively.
If s1 == s2, that implies the two input strings are isomorphic. Otherwise, they're not.
    */
    
class Solution {
    private String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            
            if (!indexMapping.containsKey(c1)) {
                indexMapping.put(c1, i);
            }
            
            builder.append(Integer.toString(indexMapping.get(c1)));
            builder.append(" ");
        }
        return builder.toString();
    }
    
    public boolean isIsomorphic(String s, String t) {
        return transformString(s).equals(transformString(t));
    }
}
