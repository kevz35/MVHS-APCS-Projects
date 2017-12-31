/**
 *       Utilities for handling HTML
 *	 This program tokenizes an HTML file’s strings into
 * 	 tags, numbers, punctuation, words, etc. by ordering the
 * 	 precedence of the types of tokens. The program adds each char
 * 	 to a token string, adds each token string to a string array,
 * 	 and prints out the string array.
 *
 *       @author Kevin Zhou
 *       @version October 27th, 2017
 */

public class HTMLUtilities {

        // NONE = not nested in a tag
	// COMMENT = between comment tags
        // PREFORMAT = between pre-format tags
        private enum TokenState { NONE, COMMENT, PREFORMAT };
        // the current state of the tokenizer
        private static TokenState state;

        public HTMLUtilities(){
		// initialize the state as NONE
                state = TokenState.NONE;
        }

        /**
         * This main utility takes a string as input, then
         * tokenizes the string and passes back an array of string tokens.
         * Precondition: str is not null
         * Precondition: each '<' tag is followed by one '>' tag
         * Precondition: each '<' tag has a corresponding '>' tag
         * Postcondition: the returned array size is exactly the number
         *                                        of tokens found
         * @param str            the input string to tokenize
         * @return                       the array of string tokens
         */
        public String[] tokenizeHTMLString(String str){

                String[] result = new String[1000000]; //make this large
                int strIndex = 0;

                for(int i = 0; i < str.length(); i++) {
                        // check if the state is comment
                        if(state == TokenState.COMMENT)
                                // check for the ending comment tag by finding the substring "-->"
                                if(i + 3 <= str.length() && str.substring(i, i + 3).equals("-->")){
                                        // reset token state
                                        state = TokenState.NONE;
                                        // incriment the index appropriately
                                        i = i + 2;
                                }
				// do nothing and don’t add a token to the result array
                                else continue;
                        // check for the starting comment tag by finding the substring "<!--"
                        else if(i + 4 <= str.length() && str.substring(i, i + 4).equals("<!--")) {
                                // set the token state to comment
                                state = TokenState.COMMENT;
                                // incriment the index appropriately
                                i = i + 3;
                        }
                        // check if the state is preformat
                        else if(state == TokenState.PREFORMAT) {
                                // check for the closing preformat tag by finding the substring "</pre>"
                                if(i + 6 <= str.length() && str.substring(i, i + 6).equals("</pre>")){
                                        // reset token state
                                        state = TokenState.NONE;
                                        // add the tag as a token
                                        result[strIndex++] = str.substring(i, i + 6);
                                        // incriment index appropriately
                                        i = i + 5;
                                }
                                else {
                                        // if token is a line and not a tag, add it the whole line normally
                                        result[strIndex++] = str.substring(i);
                                        // move index value appropriately
                                        i = str.length();
                                }
                        }
                        // check for the starting preformat tag by finding the substring "<pre>"
                        else if(i + 5 <= str.length() && str.substring(i, i + 5).equals("<pre>")) {
                                // set the token state to preformat
                                state = TokenState.PREFORMAT;
                                // add the tag as a token
                                result[strIndex++] = str.substring(i, i + 5);
                                // incriment index appropriately
                                i = i + 4;
                        }
                        // check for the start of a tag by finding the char '<'
                        else if(str.charAt(i) == '<') {
                                int j = i + 1;
                                // until the end of a tag is found, through finding the char '>', keep incrementing the index j
                                while(j < str.length() && str.charAt(j) != '>')
                                        j++;
                                if(j == str.length())
                                        j--;
                                // add the token
                                result[strIndex++] = str.substring(i, j + 1);
                                // move index value appropriately
                                i = j;
                        }
                        // check for the start of a word by checking for a letter being followed by letters
                        else if(Character.isLetter(str.charAt(i)) && i + 1 < str.length() && Character.isLetter(str.charAt(i + 1))) {
                                int j = i + 1;
				// until a char is found that is not a letter, keep incrementing the index j
                                while(j < str.length() && Character.isLetter(str.charAt(j)))
                                        j++;
				// add the token
                                result[strIndex++] = str.substring(i, j);
				//move the index value appropriately
                                i = j - 1;
                        }
                        //EC: check for a weird number by checking chars inclusively for '-' , '.'
                        else if(Character.isDigit(str.charAt(i)) || ((str.charAt(i) == '-' || str.charAt(i)
                        == '.') && i + 1 < str.length() && Character.isDigit(str.charAt(i + 1)))) {
                                int j = i + 1;
				// this boolean is used to break out of the while loop when the end of the weird number is found
                                boolean cancel = false;
                                while(j < str.length() && !cancel) {
					// check inclusively for e later on in the number after the first digit
					// until a char is found that is not allowed in a weird number, keep incrementing the index j
                                        if(Character.isDigit(str.charAt(j)) || str.charAt(j) == 'e' || str.charAt(j) == '-')
                                                j++;
                                        else if(str.charAt(j) == '.')
                                                if(j + 1 == str.length())
                                                        j++;
                                        else if(Character.isDigit(str.charAt(j + 1)))
                                                j++;
                                        else
                                                cancel = true;
                                }
				// add the token
                                result[strIndex++] = str.substring(i, j);
				// move the index appropriately
                                i = j - 1;
                        }
                        //EC: check for ellipses by checking for three '.' in a row
                        else if(str.charAt(i) == '.' && i + 1 < str.length() && str.charAt(i + 1) == str.charAt(i)) {
                                int j = i + 1;
				// until a char is found that is not a '.' move the index j (until the end of the ellipse)
                                while(j < str.length() && str.charAt(j) == str.charAt(i))
                                        j++;
				// add the token
                                result[strIndex++] = str.substring(i, j);
				// move the index value appropriately
                                i = j - 1;
                        }
                        //check for single punctuation by just checking using isPunctuation because of the precedence
                        else if(isPunctuation(str.charAt(i)))
                                result[strIndex++] = str.substring(i, i + 1);
			// check for whitespace
                        else if(str.charAt(i) != ' ') {
                                int j = i + 1;
				// if white space is found, take it out by incrementing until a punctuation or a letter is found
                                while(j < str.length() && str.charAt(j) != ' ' && !isPunctuation(str.charAt(j))	&& !Character.isLetter(str.charAt(j)))
                                        j++;
				// don’t add anything
				// move the index value appropriately
                                i = j - 1;
                        }
                }
		// resize the array to fit the exact number of tokens
                result = sizeArray(result, strIndex);
                return result;
        }

        /**
         * Take a large String array as input and output a copy String
         * array that is exactly the number of valid elements
         * @param arr            the input String array
         * @param num            the number of valid elements
         * @return                       a sized copy of the original String array
         */
         private String[] sizeArray(String[] arr, int num) {
                 String[] result = new String[num];
                 for (int a = 0; a < num; a++)
                         result[a] = arr[a];
                 return result;
         }

        /**
         * Punctuation is a printable character that is not whitespace, a digit, nor a letter.
         * Method takes a single char as input and checks if it is punctuation or not
         * @param c                      the input char
         * @return                       true if the char is punctuation and false if not
         */
        private boolean isPunctuation(char c){
                if (c >= '!' && c <= '~' && c != ' ' && !Character.isDigit(c) && !Character.isLetter(c))
                        return true;
                return false;
        }

       /**
        * Print the tokens in the array
        * Precondition: All elements in the array are valid String objects. (no nulls)
        * @param tokens an array of String tokens
        */
        public void printTokens(String[] tokens) {
                if (tokens == null) return;
                for (int a = 0; a < tokens.length; a++) {
                        if (a % 5 == 0) System.out.print("\n  ");
                        System.out.print("[token " + a + "]: " + tokens[a] + " ");
                }
                System.out.println();
        }

}