/*Kevin Zhou
04-18-17
QuizPart.java
This program is part of the 5-week game project "Parametric Power" and teaches the user about parametric equations through a quiz-based game format.
This part contains 10 questions, 5 conceptual and 5 problem-solving. This part will be supplemented by a simulation part.*/

import java.awt.Color;		//importing all of the appropriate classes
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class QuizPart extends JPanel		//this is the 'main' panel that calls all of the different panels that each do a different job during the quiz.
{									//for example, one of the panels called is the one that displays the questions to the user.
    private imagePan p1;
    private quesPan p2;
    private hintPan p3;
    private ansPan p4;
    
    public QuizPart(JPanel mainPanel, JPanel playPanel, JPanel tutorPanel)
    {
        setLayout(new GridLayout(2, 2, 10, 10));

        p1 = new imagePan();
        p2 = new quesPan();
        p4 = new ansPan(p2);
        p3 = new hintPan(p1, p2, p4, mainPanel,playPanel,tutorPanel,this);
        
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
    }
    
    public JPanel getAns()
    {
    	return p4;
    }
    public JPanel getImagePan()
    {
    	return p1;
    }
}

class imagePan extends JPanel		//this is the first JPanel in the quiz; this panel is the one that contains all of the images, 
{									//or memes to be displayed on the top left corner of the quiz. It is designed to entertain the
    private Image image;			//user as they go through the quiz. 
    private String imageName;
    private int counter1, sizeX, sizeY, xpos, ypos;

    public imagePan()
    {
		setLayout(new GridLayout(1, 1, 20, 20));
		imageName = "meme0.png";
		getMyImage();
		
		sizeX = 450;
		sizeY = 330;
		xpos = 25;
		ypos = 0;
	}
    
    public void getMyImage()	//getMyImage method to get/read all of the images 
    {
    	if(counter1 == 0)
    		imageName = "meme3.jpg";

    	if(counter1 == 1)
			imageName = "meme0.png";

		else if(counter1 == 2)
			imageName = "meme2.jpg";

		else if(counter1 == 3)
			imageName = "meme1.png";

		else if(counter1 == 4)
			imageName = "brace.jpg";

		else if(counter1 == 5)
			imageName = "fake.jpg";

		else if(counter1 == 6)
			imageName = "meme6.jpg";

		else if(counter1 == 7)
			imageName = "biology.jpg";

		else if(counter1 == 8)
			imageName = "einstein.jpg";

		else if(counter1 == 9)
			imageName = "smart.jpg";

		else if(counter1 == 10)
			imageName = "meme4.jpg";
    	
		else if(counter1 == 11)
			imageName = "meme5a.jpg";
    	
		else if(counter1 == 12)
			imageName = "100.jpg";
    	
 		try
		{
			image = ImageIO.read(new File(imageName));
		}
		catch (IOException e)
		{
			System.err.println("\n\n ERROR: " + imageName + " can't be found.\n\n");
			e.printStackTrace();
		}
	}
	
    public void changeProblemNum()		//changes the image as the problem number changes 
    {
        counter1 += 1;
        getMyImage();
        repaint();
    }
    
    public void paintComponent(Graphics g)	//paintComponent method that actually draws the images onto the screen. 
    {
		g.drawImage(image, xpos, ypos, sizeX, sizeY, this);
	}
    
    public void restart() 	//if the user decides to restart the quiz, then the image count will go to zero and the 
    {						//images will repaint in a new cycle. 
		counter1 = 0;
		imageName = "meme0.png";
		repaint();
	}
}

class quesPan extends JPanel		//this is the class that holds the JTextAreas that display the questions on the top right
{									//of the game. The JTextAreas cannot be edited (obviously), and change as the user progresses
    private JLabel quesText;		//in the game. 
    private int counter2;
    private String str, res;
    
    public quesPan()
    {
        counter2 = 0;
        str = "<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Welcome to the Parametric Power Quiz!<BR>Here is where the questions will be displayed!</html>";
        quesText = new JLabel(str);
        quesText.setBounds(455,5,855,305);
        quesText.setFont(new Font("Comic Sans",Font.PLAIN,14));
        add(quesText);
        quesText.setText(str);
        setBackground(Color.GREEN);
    }
    
    public JLabel getLabel()
    {
    	return quesText;
    }
    
    public void actionPerformed(ActionEvent e) {}  //actionPerformed method is empty because nothing about the JTextArea can change due to user interaction. 
    
    public void restart()	//if the user decides to restart the quiz after finishing it, this will restart the text displayed. 
    {
		counter2 = 0;
		str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Welcome to the Parametric"
			+ " Power Quiz!<BR>Here is where the questions will be displayed!</html>";
        quesText.setText(str);
	}
	
    public void nextProb()		//this is the method that contains all of the problems to be displayed 
    {
    	if(counter2 == 1)
    	{
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Concepts 1:<BR>In the"
            	+ " abscence of air, do ALL objects, light or heavy,<BR>fall "
            	+ "with the same acceleration?</html>";
    	}
    	else if(counter2 == 2)
    	{
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Concepts 2:<BR>What is "
            	+ "the slope of a position vs. time graph?</html>";
    	}
        else if(counter2 == 3)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Concepts 3:<BR>If a man"
            	+ " drops a pencil and shoots a bullet horizontally<BR>from a gun "
            	+ "at the same time, which one will<BR>reach the ground first?</html>";
        }
        else if(counter2 == 4)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Concepts 4:<BR>Which of "
            	+ "the following parametric equations<BR>models the x and y values"
            	+ " of a projectile thrown in the<BR>air at an angle?</html>";
    	}
        else if(counter2 == 5)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Concepts 5:<BR>If a plane"
            	+ " drops a crate high in the air, where will the<BR>crate be relative"
            	+ " to the plane while it is in the air?</html>";
    	}
        else if(counter2 == 6)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Concepts 6:<BR>Three objects,"
            	+ " A, B, and C, are thrown into the air. They are thrown<BR>with the "
            	+ "same velocity but at different angles. A is thrown at 45<BR>degrees, "
            	+ "B at 30 degrees, and C at 60 degrees.Which object<BR>travels the farthest?</html>";
    	}
        else if(counter2 == 7)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Problem-Solving 1:<BR>Given "
            	+ "two parametric equations: x = t^2 - 4, y = t + 1/t<BR>Which of the"
            	+ " following corresponds to t = -1?</html>";
    	}
        else if(counter2 == 8)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>Problem-Solving 2:<BR>Given"
            	+ " two parametric equations: x = 3t - 1, y = -t^2 + 8t<BR>Which "
            	+ "value of t corresponds with the point(14,15)?</html>";
    	}
        else if(counter2 == 9)
        {
    	    str = "<html><BR><BR><BR><BR><BR><BR><BR>Problem-Solving 3:<BR>A ball "
    	    	+ "is thrown straight up from the ground with its<BR>position above "
    	    	+ "ground at any time t >= to 0 given by<BR> x = 1-, y = -16t^2 + 64t"
    	    	+ " + 36. At what time will the<BR>ball be 100 ft above ground?<html>";
    	}
        else if(counter2 == 10)
        {
            str = "<html><BR><BR><BR><BR><BR><BR>Problem-Solving 4:<BR>Suppose that"
            	+ " a golf player is playing golf on Jupiter.<BR>She hits a ball of "
            	+ "a practice tee with an initial velocity <BR>of 190 ft/sec. If the "
            	+ "ball initially leaves the ground while<BR>making a 15 degree angle"
            	+ " with the horizontal, how far down the<BR>fairway will the ball "
            	+ "first hit the ground? Assume acceleration<BR>due to gravity on "
            	+ "Jupiter is 80 ft/s^2 downwards.</html>";
    	}
        else if(counter2 == 11)
        {
            str = "<html><BR><BR><BR><BR><BR><BR>Problem-Solving 5:<BR>Suppose that a"
            	+ " baseball player hits a ball when it is<BR>4 ft above the ground"
            	+ " with an initial velocity of 120 ft/sec.<BR>The balls leaves the "
            	+ "bat at a 30 degree angle with the horizontal<BR>and heads toward "
            	+ "a 20-ft fence 350 ft from home plate. Does the<BR>ball clear the "
            	+ "fence? If so, by how much? If not, by how much?<BR>(Assume acceleration"
            	+ " due to gravity is 32 ft/s^2 downwards and<BR>y displacement is equal"
            	+ " to (-1/2)at^2 - vt + h)</html>";
    	}
        else if(counter2 == 12)
        {
            str = "<html><BR><BR><BR><BR><BR><BR><BR><BR>CONGRATULATIONS! You've "
            	+ "finished the game!<BR>Hopefully you've got a better understanding "
            	+ "of kinematics!<BR>If you want to play again, click the Restart Button!"
            	+ "<BR>If not, click the Exit Button!</html>";
    	}
    quesText.setText(str);
}
    
    public void changeProblemNum()		//changes the problem number and the text in the JTextArea as the user progresses. 
    {
        counter2 += 1;
        nextProb();
    }
}

class hintPan extends JPanel implements ActionListener		//this is the class that includes the hints that will displayed
{        													//on a JPanel if the user clicks the 'hints' button. There is only
    private JButton retry;									//one hint per question. 
    private JButton nextQues;
    private PressHint pressH;
    private DrawHint drawH;
    private PressSimInfo pressS;
    private DrawSimInfo drawS;
    private ContinueButton contB;
    private RetryButton retryB;
    private int counter3;
    private imagePan ipanel;
    private quesPan q2panel;
    private ansPan apanel;
    private JPanel mainPanel,playPanel,tutorPanel,quizPanel;
    
    public hintPan(imagePan ipan, quesPan q2pan, ansPan apan, JPanel mainPanel,JPanel playPanel,JPanel tutorPanel,JPanel quizPanel)
    {
        this.mainPanel=mainPanel;
        this.playPanel=playPanel;
        this.tutorPanel=tutorPanel;
        this.quizPanel=quizPanel;
    	ipanel = ipan;
        q2panel = q2pan;
        apanel = apan;
        counter3 = 0;
        
        setLayout(new GridLayout(3, 2, 10, 10));
        setBackground(Color.BLUE);
        
        pressH = new PressHint();
        drawH = new DrawHint();
        pressS = new PressSimInfo();
        drawS = new DrawSimInfo();    
        contB = new ContinueButton();     
        retryB = new RetryButton();
                    
        add(pressH);     
        add(drawH);      
        add(pressS);     
        add(drawS);     
        add(contB);
        add(retryB);
    }
    
    public void actionPerformed(ActionEvent e) {}
	
    class ContinueButton extends JPanel implements ActionListener	//nested class that will reset the panel if the user decides to 
    {																//'restart' the game. 
        public ContinueButton()
        {
            nextQues = new JButton("Continue");
            nextQues.setPreferredSize(new Dimension(150,80));
            nextQues.setFont(new Font("Lucide Grande", Font.PLAIN,18));
            nextQues.addActionListener(this);
            add(nextQues);
        }
        
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if(command.equals("Submit"))
            {
            	if(apanel.moveOn() == true)
                {
            		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Correct! "
            				+ "You got the right answer!<BR>Hit the Continue button to move on!</html>");
                }
            	
                else if(apanel.moveOn() == false)
                {
                	if(counter3 == 1)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect; "
                				+ "the answer is B. Maybe refere to the simulation to<BR>get rid of "
                				+ "misconceptions? Then hit the Continue button to<BR>move on.</html>");
                	}
                	
                	if(counter3 == 2)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect; "
                				+ "the answer is actually A.<BR>Hit the Continue button to move on.</html>");
                	}
                	
                	if(counter3 == 3)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect;"
                				+ " the answer is actually C.<BR>Hit the Continue button to move on.</html>");
                	}
                	
                	if(counter3 == 4)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect; "
                				+ "the answer is A. This is a tough one, maybe<BR>refer to the hint? "
                				+ "Then hit the Continue button to<BR>move on.</html>");
                	}	
                	
                	if(counter3 == 5)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect;"
                				+ " the answer is A. This is a tough one, maybe<BR>refer to the hint?"
                				+ " Then hit the Continue button to<BR>move on.</html>");
                	}	
                	
                	if(counter3 == 6)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect;"
                				+ " the answer is A. Try playing around with<BR>the simulation, and "
                				+ "notice what happens at the<BR>different angles. Then hit the Continue"
                				+ " button to move on.</html>");
                	}
                	
                	if(counter3 == 7)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect;"
                				+ " the answer is A. Maybe refer to the tutorial<BR>after the quiz to "
                				+ "help you on this type of problem.<BR>Then hit the Continue button "
                				+ "to move on.</html>");
                	}
                	
                	if(counter3 == 8)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect;"
                				+ " the answer is A. Hit the Continue button to move on.</html>");
                	}
                	
                	if(counter3 == 9)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect;"
                				+ " the answer is B. Hit the Continue button to move on.</html>");
                	}
                	
                	if(counter3 == 10)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect; "
                				+ "the answer is A. Hit the Continue button to move on.</html>");
                	}
                	
                	if(counter3 == 11)
                	{
                		q2panel.getLabel().setText("<html><BR><BR><BR><BR><BR><BR><BR><BR><BR>Incorrect; "
                				+ "the answer is C. The simulation might help.<BR>Hit the Continue "
                				+ "button to move on.</html>");
                	}
                }
                	nextQues.setText("Continue");
            }
            
            if(command.equals("Continue"))
            {
                if(counter3 != 12)		//while the user has not reached the last question, the game continues
                {
                	counter3 += 1;
                	
                    ipanel.changeProblemNum();
                    q2panel.changeProblemNum();
                    apanel.changeProblemNum();
                    
                    apanel.deSelect();
                    
                    drawS.getTextArea().setText("");
                    drawH.getTextArea().setText("");
                    
                    pressH.hintBox.setText("Stuck!? Press for a Hint!");
                    pressS.simPresetInfo.setText("Click for Simulation!");
                    nextQues.setText("Submit");
                }
                
                if(counter3 == 12)
                {
                    nextQues.setText("Restart");
                    retry.setText("Exit");
                }
            }
            
            if(command.equals("Restart"))               //the if statment that restartsthe panel if the user decides to
            {
	            counter3 = 0;
	            
	            ipanel.restart();
	            q2panel.restart();
	            apanel.restart();
	            
	            drawS.getTextArea().setText("");
	            drawH.getTextArea().setText("");
	            
	            pressH.hintBox.setText("Stuck!? Press for a Hint!");
	            pressS.simPresetInfo.setText("Click for Simulation!");
	            
	            nextQues.setText("Continue");
	            retry.setText("Clear");
            }
        }
    }
    
    class RetryButton extends JPanel implements ActionListener 		//this is the class that gives the user the ability to retry 
    {																//the current question to get a second chance. It simply 
        public RetryButton()										//deselects the radio button and resets the presets and the hints
        {															//as if the user had just gotten to the question for the first time.
            retry = new JButton("Clear");
            retry.setPreferredSize(new Dimension(150,80));
            retry.addActionListener(this);
            retry.setFont(new Font("Lucida", Font.PLAIN,16));
            add(retry);
        }
        
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            
            if(command.equals("Clear"))
            {
            	 apanel.deSelect();
            	 
                 if(counter3 != 0)
                	 apanel.setFalse();
            }
            else if(command.equals("Exit"))
            {
            	 mainPanel.setVisible(true);
                 playPanel.setVisible(false);
                 tutorPanel.setVisible(false);
                 apanel.setVisible(false);
                 quizPanel.setVisible(false);
            }
        }
    }
    
    class PressHint extends JPanel		//this the class that actually displays all of the hints if the user clicks on the 'hints'
    {									//button. Some hints are more extensive than others, because the corresponding questions are 
        private JButton hintBox;		//harder. The text is all displayed inside a simple JTextArea. 
        
        public PressHint()
        {
            setLayout(new FlowLayout());
            Button1Handler b1Handler = new Button1Handler();
            hintBox = new JButton("Stuck!? Press for a Hint!");
            hintBox.setFont(new Font("Comic Sans", Font.PLAIN,16));
            hintBox.setPreferredSize(new Dimension(220,80));
            hintBox.addActionListener(b1Handler);
            add(hintBox);
        }
        
        class Button1Handler implements ActionListener
        {
            public Button1Handler() {}
            
            public void actionPerformed(ActionEvent evt)	//actionPerformed method that includes the hints
            {
                String command = evt.getActionCommand();
                if(command.equals("Stuck!? Press for a Hint!"))
                {
                    hintBox.setText("Reset");
                    
                    if(counter3 == 0)
                    {
                    	drawH.getTextArea().setFont(new Font("Comic Sans", Font.PLAIN,10));
                        drawH.getTextArea().setText("This is where hints will be displayed!");
                    }
                    
                    else if(counter3 == 1)
                    {
                    	drawH.getTextArea().setText("<html>No hint for this question!</html>");
                    }
                    
                    else if(counter3 == 2)
                    {
                    	drawH.getTextArea().setText("<html>Remember, v = d/t</html>");
                    }
                    
                    else if(counter3 == 3)
                    {
                    	drawH.getTextArea().setText("<html>Remember, both start from the same height!</html>");
                    }
                    
                    else if(counter3 == 4)
                    {
                    	drawH.getTextArea().setText("<html>x component should have a cosine somewhere in it!</html>");
                    }
                    
                    else if(counter3 == 5)
                    {
                    	drawH.getTextArea().setText("<html>Horizontal velocity doesn't change!</html>");
                    }
                    
                    else if(counter3 == 6)
                    {
                    	drawH.getTextArea().setText("<html>Maybe refer the simulation?</html>");
                    } 
                    
                    else if(counter3 == 7)
                    {
                    	drawH.getTextArea().setText("<html>Substitute -1 for each equation to find"
                    			+ " the x and y coordinate.</html>");
                    }
                    
                    else if(counter3 == 8)
                    {
                    	drawH.getTextArea().setText("<html>Substitute the coordinate values for x "
                    			+ "and y to find t. (t should be the same for both equations)</html>");
                    }
                    
                    else if(counter3 == 9)
                    {
                    	drawH.getTextArea().setText("<html>Set y to 100 and solve the resulting "
                    			+ "quadratic equation. Since t must be >= 0, only one solution"
                    			+ " works.</html>");
                    }
                    
                    else if(counter3 == 10)
                    {
                    	drawH.getTextArea().setText("<html>Set up two parametric equations. "
                    			+ "Use trig to find the x and y components of the initial "
                    			+ "velocity. Then use y = h + vt - 0.5at^2</html>");
                    }
                    
                    else if(counter3 == 11)
                    {
                    	drawH.getTextArea().setText("<html>Set up two parametric equations."
                    			+ " Use trig to find x and y components of the initial velocity."
                    			+ " Find the time to get to the 350ft mark, then use it to "
                    			+ "see what height it is at.</html>");
                    }
                    
                    else if(counter3 == 12)
                    {
                    	drawH.getTextArea().setText("<html>You finished the quiz!<BR>You "
                    			+ "don't need any hints!</html>");
					}
                }
                
                else
                {
                    hintBox.setText("Stuck!? Press for a Hint!");
                    hintBox.setFont(new Font("Comic Sans", Font.PLAIN,16));
                    drawH.getTextArea().setText("");
                }
            }
        }
    }
    
    class DrawHint extends JPanel		//this is the class that is first called before the user clicks any hints. It
    {									//adds the JTextArea to the panel and sets all the settings (size and editable)
        private JLabel hintBoxText;
        
        public DrawHint()
        {
            hintBoxText = new JLabel("");
            hintBoxText.setPreferredSize(new Dimension(200,85));
            add(hintBoxText);
        }
        
        public JLabel getTextArea()
        {
            return hintBoxText;
        }
    }
    
    class PressSimInfo extends JPanel		//PressSimInfo class allows the user to click on a button that displays the presets for 
    {										//the simulation while going through the game. The simulation is to the right of the game
        private JButton simPresetInfo;		//and can be used by the user during the game. 
        
        public PressSimInfo()
        {
            setLayout(new FlowLayout());
            Button2Handler b2Handler = new Button2Handler();
            simPresetInfo = new JButton("Click for Simulation!");
            simPresetInfo.setFont(new Font("Comic Sans",Font.PLAIN,16));
            simPresetInfo.setPreferredSize(new Dimension(220,80));
            simPresetInfo.addActionListener(b2Handler);
            add(simPresetInfo);
        }
        
        class Button2Handler implements ActionListener
        {
            public Button2Handler() {}
            
            public void actionPerformed(ActionEvent evt)  //actionPerformed method that includes the different presets available. 
            {
                String command = evt.getActionCommand();
                if(command.equals("Click for Simulation!"))
                {
	            	mainPanel.setVisible(false);
					playPanel.setVisible(true);
					tutorPanel.setVisible(false);
					apanel.setVisible(false);
					quizPanel.setVisible(false);
                }
            }
        }
    }
    
    class DrawSimInfo extends JPanel		//this is the class that is first called before the user clicks any presets. It
    {										//adds the JTextArea to the panel and sets all the settings (size and editable)
        public JLabel simPresetInfoText;
        
        public DrawSimInfo()
        {
            simPresetInfoText = new JLabel("<html>Feel free to click on the<BR>simulation or hints to aid you<BR>through this quiz!</html>");
            simPresetInfoText.setPreferredSize(new Dimension(220, 80));
            add(simPresetInfoText);
        }
        
        public JLabel getTextArea()
        {
            return simPresetInfoText;
        }
    }
}

class ansPan extends JPanel implements ActionListener	//the ansPan class is one of the most important classes of the game; it 
{														//includes the four JButtons that the user clicsk on as the answer choices. 
    private ButtonGroup bg;								//depending on which button the user clicks, the user can proceed, or they
    private JRadioButton ans1, ans2, ans3, ans4;		//stay at the question and are given a second try to try and solve the question. 
    private String txt1, txt2, txt3, txt4;				//(It basically won't let you move on to the next question unless you get the previous 
    public String startText;							//one correct. 
    private int counter4;
    private boolean correct;
    public quesPan qpanel;
    public static int SCORE;
    public static boolean twelve;
    
    public ansPan(quesPan qpan)
    {
        qpanel = qpan;
        correct = true;
        counter4 = 0;
        twelve = false;
        
        String txt1 = "";
        String txt2 = "";
        String txt3 = "";
        String txt4 = "";
        
        if(counter4 == 0)
        {
            txt1 = "This is answer choice A";
            txt2 = "This is answer choice B";
            txt3 = "This is answer choice C";
            txt4 = "This is answer choice D";
        }
        
        setBackground(Color.RED);
        
        startText = "Answer Choices:";
        
        bg = new ButtonGroup();
        
        ans1 = new JRadioButton(txt1);			//instantiating the JRadioButtons
        ans2 = new JRadioButton(txt2);
        ans3 = new JRadioButton(txt3);
        ans4 = new JRadioButton(txt4);
        
        ans1.setFont(new Font("Comic Sans", Font.PLAIN,16));
        ans2.setFont(new Font("Comic Sans", Font.PLAIN,16));
        ans3.setFont(new Font("Comic Sans", Font.PLAIN,16));
        ans4.setFont(new Font("Comic Sans", Font.PLAIN,16));
      
        setLayout(new GridLayout(5, 1, 10, 10));
        
        bg.add(ans1);
        bg.add(ans2);
        bg.add(ans3);
        bg.add(ans4);
        
        ans1.addActionListener(this);
        ans2.addActionListener(this);
        ans3.addActionListener(this);
        ans4.addActionListener(this);
        
        TxtPanel panel = new TxtPanel();
        panel.setBackground(Color.RED);
        
        add(panel);
        add(ans1);					//adding the buttons to the JPanel 
        add(ans2);
        add(ans3);
        add(ans4);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(counter4 == 1)		//if this choice is clicked, then the user can move on to the next question 
        {			
            if(e.getSource() == (ans2))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 2)	//if this choice is clicked, then the user can move on to the next question 
        {	
            if(e.getSource() == (ans1))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 3)	//if this choice is clicked, then the user can move on to the next question 
        {
            if(e.getSource() == (ans3))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 4)	//if this choice is clicked, then the user can move on to the next question 
        {			
            if(e.getSource() == (ans1))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 5)	//if this choice is clicked, then the user can move on to the next question 
        {				
            if(e.getSource() == (ans1))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 6)	//if this choice is clicked, then the user can move on to the next question 
        {                        
            if(e.getSource() == (ans1))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 7)	//if this choice is clicked, then the user can move on to the next question 
        {                      
            if(e.getSource() == (ans1))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 8)	//if this choice is clicked, then the user can move on to the next question 
        {               
            if(e.getSource() == (ans3))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 9)	//if this choice is clicked, then the user can move on to the next question 
        {                    
            if(e.getSource() == (ans2))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 10)	//if this choice is clicked, then the user can move on to the next question 
        {                     
            if(e.getSource() == (ans1))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
        
        else if(counter4 == 11)	//if this choice is clicked, then the user can move on to the next question 
        {                        
            if(e.getSource() == (ans3))
            {
                correct = true;
                SCORE++;
            }
            else
            {
                correct = false;
            }
        }
    }
    
    public void nextProb()	
    {
    	correct = false;
        if(counter4 == 1)		 //question 1
        {         
            txt1 = "A: No, the lighter object will fall faster";
            txt2 = "B: Yes, acceleration doesn't depend on weight";
            txt3 = "C: No, the heavier object will fall faster";
            txt4 = "D: Yes, but the acceleration is different for each object";
        }
        
        if(counter4 == 2)		//question 2
        {         
            txt1 = "A: Velocity";
            txt2 = "B: Acceleration";
            txt3 = "C: Work";
            txt4 = "D: Magnitude of Applied Force";
        }
        
        if(counter4 == 3)		//question 3
        {    
            txt1 = "A: The bullet";
            txt2 = "B: The pencil";
            txt3 = "C: Both land at the same time";
            txt4 = "D: Neither object will reach the ground";
        }
        
        if(counter4 == 4)		//question 4
        {           
            txt1 = "A: x: v(cos(theta * t)), y: -1/2gt^2 + v(sin(theta * t)) + h";
            txt2 = "B: y: v(cos(theta * t)), x: -1/2gt^2 + v(sin(theta * t)) + h";
            txt3 = "C: x: v(tan(theta * t)), y: -1/2gt^2 + v(cot(theta * t)) + h";
            txt4 = "D: y: v(sin(theta * t)), x: -1/2gt^2 + v(cos(theta * t)) + h";
        }
        
        if(counter4 == 5)		//question 5
        {            
            txt1 = "A: Always right below the plane";
            txt2 = "B: Behind the plane";
            txt3 = "C: In front of the plane";
            txt4 = "D: Above the plane";
        }
        
        if(counter4 == 6)		//question 6
        {        
            txt1 = "Object A";
            txt2 = "Object B";
            txt3 = "Object C";
            txt4 = "All travel the same distance";
        }
        
        if(counter4 == 7)		//question 7
        {            
            txt1 = "A: (-3,-2)";
            txt2 = "B: (-3,0)";
            txt3 = "C: (-5,-2)";
            txt4 = "D: (-5,0)";
        }
        
        else if(counter4 == 8)		//question 8
        {
            txt1 = "A: t = 3";
            txt2 = "B: t = 4";
            txt3 = "C: t = 5";
            txt4 = "D: t = 6";
        }
        
        else if(counter4 == 9)		//question 9
        {     
            txt1 = "A: 1 sec";
            txt2 = "B: 2 sec";
            txt3 = "C: 3 sec";
            txt4 = "D: 4 sec";
        }
        
        else if(counter4 == 10)		//question 10
        {     
            txt1 = "A: 202.6 ft";
            txt2 = "B: 286.4 ft";
            txt3 = "C: 350.7 ft";
            txt4 = "D: 405.0 ft";
        }
        
        else if(counter4 == 11)		//question 11
        {     
            txt1 = "A: Yes, by 0.59 ft";
            txt2 = "B: No, by 0.59 ft";
            txt3 = "C: Yes, by 4.59 ft";
            txt4 = "D: No, by 4.59 ft";
        }
        
        else if(counter4 == 12)			 //end
        {    
            txt1 = "Congrats!";
            
            if(SCORE>=0 && SCORE <=4)
            	txt2 = "Your score is: " + SCORE + "/12; you could work on that score!";

            else if(SCORE>=5 && SCORE <=8)
            	txt2 = "Your score is: " + SCORE + "/12; not that shabby!";

            else if(SCORE>=9 && SCORE <=12)
            	txt2 = "Your score is: " + SCORE + "/12; what a god at physics!";

            txt3 = "Hope you understand more about kinematics now!";
            txt4 = "A parametric powered SuperHero!";
            startText = "    Results:";
        }
		ans1.setText(txt1);		//set the text of the JRadioButtons
		ans2.setText(txt2);
		ans3.setText(txt3);
		ans4.setText(txt4);
    }
    
    public void changeProblemNum()
    {
        counter4 += 1;
        nextProb();
    }
    
    public boolean moveOn()
    {
        return correct;
    }
    
    public void setFalse()
    {
    	correct = false;
    }
    
    public void setTrue()
    {
    	correct = true;
    }
    
    public void restart()		//this restarts the game and starts with the initial conditions 
    {							//(the intro to the game)
		counter4 = 0;
		SCORE = 0;
		
		correct = true;
		
		txt1 = "This is answer choice A";
        txt2 = "This is answer choice B";
        txt3 = "This is answer choice C";
        txt4 = "This is answer choice D";
        
		ans1.setText(txt1);
        ans2.setText(txt2);
        ans3.setText(txt3);
        ans4.setText(txt4);
        
        startText = "Answer Choices:";
        deSelect();
	}
	
    public void deSelect()
    {
        bg.clearSelection();
    }
    
    class TxtPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            g.setFont(new Font("Comic Sans", Font.PLAIN, 35));
            g.drawString(startText, 120, 50);
            repaint();
        }
    }
}