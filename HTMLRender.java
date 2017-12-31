/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hx>, </hx> - Start/end of heading of size x = 1, 2, 3, 4, 5, 6
 *	Optional tags supported:
 *		<pre>, </pre> - Preformatted text, optional
 *
 *	@author Kevin Zhou	
 *	@version 13 November 2017
 */

import java.util.Scanner;

public class HTMLRender
{
    // the array holding all the tokens of the HTML file
    private String [] tokens;
    private final int TOKENS_SIZE = 100000;
    private int characterCount;

    // SimpleHtmlRenderer fields
    private SimpleHtmlRenderer render;
    private HtmlPrinter browser;

    private HTMLUtilities util;
    
    public HTMLRender()
    {
        // Initialize token array
        tokens = new String[TOKENS_SIZE];
        characterCount = 0;

        // Initialize Simple Browser
        render = new SimpleHtmlRenderer();
        browser = render.getHtmlPrinter();

        util = new HTMLUtilities();
    }
    
    public static void main(String[] args)
    {
    	//Proof that character count is correct
    	/*System.out.println("This is HTML your program should tokenize and render. Be sure not to go over 80 characters".length());
    	System.out.println("For paragraphs too, we want to have bold comments with words and italic statements".length());
    	System.out.println("Bold header that goes on and on from one line to the other without any stopping and".length());*/
        HTMLRender hf = new HTMLRender();
        hf.run(args);
    }
    
    private void fillTokens(String[] args)
    {
        Scanner input = null;
        String fileName = "";
        if (args.length > 0)
            fileName = args[0];
        else {
            System.out.println("Usage: java HTMLTester <inputFileName>");
            System.exit(0);
        }
        
        input = OpenFile.openToRead(fileName);
        int count = 0;
        while (input.hasNext())
        {
            String[] arr = util.tokenizeHTMLString(input.nextLine());
            for(int i = 0; i < arr.length; i++)
                tokens[count++] = arr[i];
        }
    }

    private void checkCharacterCount()
    {
    	if(characterCount > 80)
    	{
    		browser.println();
    		characterCount = 0;
    	}
    }
    
    public void run(String[] args)
    {
        fillTokens(args);
        for(int i = 0; i < tokens.length && tokens[i] != null; i++)
        {
            switch(tokens[i])
            {
                case "<html>": case "<body>": case "</body>": case "</html>":
                    break;
                case "<p>": case "<P>": 
                	browser.println();
                	characterCount = 0;
                	break;
                case "</p>": case "</P>":
                	browser.printBreak();
                	characterCount = 0;
                	break;
                case "<q>": case "<Q>": 
                	browser.print(" \"");
                	characterCount++;
                	checkCharacterCount();
                	break;
                case "</q>": case "</Q>":
                	browser.print("\"");
                	characterCount++;
                	checkCharacterCount();
                	break;
                case "<b>": case "<B>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</b>")))
                    {
                    	String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printBold(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    break;
                case "<i>": case "<I>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</i>")))
                    {
                    	String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printItalic(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }    
                    break;
                case "<h1>": case "<H1>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</h1>")))
                    {
                        String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printHeading1(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    browser.printBreak();
                    characterCount = 0;
                    break;
                case "<h2>": case "<H2>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</h2>")))
                    {
                        String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printHeading2(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    browser.printBreak();
                    characterCount = 0;
                    break;
                case "<h3>": case "<H3>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</h3>")))
                    {
                        String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printHeading3(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    browser.printBreak();
                    characterCount = 0;
                    break;
                case "<h4>": case "<H4>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</h4>")))
                    {
                        String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printHeading4(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    browser.printBreak();
                    characterCount = 0;
                    break;
                case "<h5>": case "<H5>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</h5>")))
                    {
                        String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printHeading5(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    browser.printBreak();
                    characterCount = 0;
                    break;
                case "<h6>": case "<H6>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</h6>")))
                    {
                        String str = "";
                    	if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    		str = tokens[i];
                    	else
                    		str = " " + tokens[i];
                    	browser.printHeading6(str);
                    	characterCount += str.length();
                    	checkCharacterCount();
                        i++;
                    }
                    browser.printBreak();
                    characterCount = 0;
                    break;
                case "<hr>": case "<HR>": 
                	browser.printHorizontalRule();
                	characterCount = 0;
                    break;
                case "<br>": case "<BR>": 
                	browser.printBreak();
                	characterCount = 0;
                    break;
                case "<pre>": case "<PRE>":
                    i++;
                    while(!(tokens[i].equalsIgnoreCase("</pre>")))
                    {
                        browser.printPreformattedText(tokens[i]);
                        browser.println();
                        characterCount = 0;
                        i++;
                    }
                    break;
                default:
                 	String str = "";
                    if(characterCount == 0 || (tokens[i].length() == 1 && util.isPunctuation(tokens[i].charAt(0))))
                    	str = tokens[i];
                    else
                    	str = " " + tokens[i];
                    browser.print(str);
                    characterCount += str.length();
                    checkCharacterCount(); 
                    break;
            }//switch
        }//for
    }//run
}//HTMLRender