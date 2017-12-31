import java.io.PrintWriter;
import java.util.Scanner;

/**
 * MVCipher - program that encrypts and decrypts a plaintext file
 * Requires Prompt and OpenFile classes.
 *
 * @author Kevin Zhou
 * @since September 13th, 2017
 */
 
public class MVCipher {
	private String key; // uppercase version of key; must be one word
	private String inFileName, outFileName;
	private boolean encrypt; // encrypt if true; otherwise decrypt
	private Scanner input; // Input text file
	private PrintWriter output; // Output text file
	
	public MVCipher() {
		encrypt = true;
	}
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
   
   /**
	*  Description: this is the run method which encrypts and
	*  and decrypts a plaintext file
	*  Pre-Conditions: Prompt.java and OpenFile.java are in
	*  the same directory
	*  Post-Conditions: output file is encrypted or decrypted
	*  based on contents of input file, user's keyword, and
	*  user's choice of encrypt/decrypt
	*/
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		// Prompt for a key and change to uppercase
		boolean badInput = false;
		char temp = ' ';
		do {
		// Use the Prompt class to get user input
			key = Prompt.getString("Please input a word to use as key (letters only)");
			key = key.toUpperCase();
			badInput = false;
		
		// Do not let the key contain anything but alpha
			for (int i = 0; i < key.length(); i++){
				temp = key.charAt(i);
				if (!(temp >= 'A' && temp < 'Z'))
					badInput = true;
				}
		} while(badInput);

		// Prompt for encrypt or decrypt
		if (Prompt.getInt("Encrypt or decrypt?", 1, 2) == 1)
			encrypt = true;
		else
			encrypt = false;
		
		// Prompt for an input file name
		if (encrypt)
			inFileName = Prompt.getString("Name of file to encrypt");
		else
			inFileName = Prompt.getString("Name of file to decrypt");
		input = OpenFile.openToRead(inFileName);
		
		// Prompt for an output file name
		outFileName = Prompt.getString("Name of output file");
		output = OpenFile.openToWrite(outFileName);
		
		// Read input file, encrypt or decrypt, and print to output file
		encryptDecryptFile();
		
		// Don't forget to close your output file
		if (encrypt)
			System.out.println("\nThe encrypted file " + outFileName + " has been created"
									+ " using the keyword -> " + key + "\n");
		else
			System.out.println("\nThe decrypted file " + outFileName + " has been created"
									+ " using the keyword -> " + key + "\n");
		output.close();
	}
	
   /**
	* Reads the input file, encrypts or decrypts the contents,
	* then writes the results to an output file.
	*/
	public void encryptDecryptFile() {
		String oldLine = "";
		String newLine = "";
		while (input.hasNext()) {
			oldLine = input.nextLine();
			newLine = convertString(oldLine);
			output.println(newLine);
		}
	}
	
   /**
	*  Description: breaks down a string into lowercase and
	*  uppercase letters to be converted individually
	*  @param String str: the string being converted from the input file
	*  @return String newStr: the converted string
	*/
	public String convertString(String str) {
		char oldChar = ' ';
		char newChar = ' ';
		String newStr = "";
		int position = 0;
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			oldChar = str.charAt(i);
			if (oldChar >= 'a' && oldChar <= 'z') {
				position = count % key.length();
				newChar = convertLowercase(str.charAt(i), position);
			}
			else if (oldChar >= 'A' && oldChar <= 'Z') {
				position = count % key.length();
				newChar = convertUppercase(str.charAt(i), position);
			}
			else {
				newChar = oldChar;
				count -= 1;
			}
			count++;
			newStr += newChar;
		}
		return newStr;
	}
	
   /**
	*  Description: converts the lowercase letters from the string
	*  Pre-Conditions: char is lowercase from 'a' to 'z'
	*  Post-Conditions: char may be lowercase or uppercase
	*  @param char oldChar: the char from the string being converted
	*  @param int position: the shift due to the position of the oldChar
	*  in relation to the corresponding char from the keyword
	*  @return char newChar: the converted char
	*/
	public char convertLowercase(char oldChar, int position) {
		char newChar = ' ';
		if (encrypt) {
			newChar = (char)(oldChar + key.charAt(position) - 64);
			if (newChar > 'z'){
				newChar = (char)(newChar - 58);
				}
		}
		else {
			newChar = (char)(oldChar - key.charAt(position) + 64);
			if (newChar < 'a') {
				newChar = (char)(newChar - 6);
			}
		}
		return newChar;
	}
	
   /**
	*  Description: converts the uppercase letters from the string
	*  Pre-Conditions: char is uppercase from 'A' to 'Z'
	*  Post-Conditions: char may be lowercase or uppercase
	*  @param char oldChar: the char from the string being converted
	*  @param int position: the shift due to the position of the oldChar
	*  in relation to the corresponding char from the keyword
	*  @return char newChar: the converted char
	*/
	public char convertUppercase(char oldChar, int position) {
		char newChar = ' ';
		if (encrypt) {
			newChar = (char)(oldChar + key.charAt(position) - 64);
			if (newChar > 'Z'){
				newChar = (char)(newChar + 6);
            }
        }
        else {
            newChar = (char)(oldChar - key.charAt(position) + 64);
			if (newChar < 'A') {
				newChar = (char)(newChar + 58);
            }
        }
		return newChar;
	}
}