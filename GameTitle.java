/*Eric Yang
 * Period 3
 * GameTitle2.java
 * This is the first part of our physics and math game. It includes the titlePanel and 
 * the the simulation for the game. The title panel includes three JButtons that the user
 * can click on to proceed (Play, Tutorial, and Quit button). The Play button leads the 
 * user to the quiz; it has a few questions and also includes memes on the side to 
 * entertain the player as they answer the questions. The tutorial panel includes a description of the
 * game and how to play. It also includes an overview of kinematics and parametric equations. If the user
 * clicks the "Quit" button, the game quits and the window closes. 
 */

import java.awt.BorderLayout; //importing all of the appropriate classes
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;

public class GameTitle extends JFrame implements ActionListener //class for the Title panel of the game
{
	private JPanel contentPane, playPanel, mainPanel;
	private TutorPanel tutorPanel;
	private QuizPart quizPanel;
	public JButton btnNewButton, btnNewButton_1, btnTutorial, preButton, nextButton;
    private Simulation displayPanel;
    private static int ANGLE, VELOCITY;
	private static String objectThrown;
	private JSlider angleslider,velocityslider;
	public String horizontalD;
	public String verticalD;
	
    public static void main(String[] args) 
    {
		GameTitle frame = new GameTitle();
		frame.setVisible(true);
		frame.drawEverything();
		frame.repaint();
	}
	
	public GameTitle(){}	
	
	public void drawEverything()		//this is the method where all the JButtons and JLables and images are put
	{									//into the JFrame, including the two JPanels for the left and right side. 
		ANGLE = 45;
		VELOCITY = 50;
		objectThrown = "redBall";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setResizable(false);
		
		contentPane = new JPanel(){
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("SkyWallpaper.png");
				ii.paintIcon(this,g,0,0);
			}
		};
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		mainPanel = new JPanel();
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		
		btnNewButton = new JButton("Play!");    //the 'main' button that the user clicks to enter the game
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		btnNewButton.setBounds(688, 76, 238, 115);   //manually setting the size of the button so it is visibly pleasing to the user
		btnNewButton.addActionListener(this);
		mainPanel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Quit");	//the 'quit' button that exits the game once the user clicks on it
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		btnNewButton_1.setBounds(688, 376, 238, 115);
		btnNewButton_1.addActionListener(this);
		mainPanel.add(btnNewButton_1);
		
		btnTutorial = new JButton("Tutorial");	//the 'tutorial' button that includes a tutorial of kinematics and parametric equations, and also includes images to help
		btnTutorial.setForeground(Color.BLUE);	//the user understand the content faster and easier.
		btnTutorial.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		btnTutorial.setBounds(688, 228, 238, 115);
		btnTutorial.addActionListener(this);
		mainPanel.add(btnTutorial);
		
		JLabel lblNewLabel = new JLabel("By: Eric Yang, Kevin Zhou");	//JLabel for game designer names
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(688, 520, 244, 41);
		mainPanel.add(lblNewLabel);
		
		JLabel lblPeriod = new JLabel("Period 3");		//period label
		lblPeriod.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPeriod.setBounds(776, 560, 74, 33);
		mainPanel.add(lblPeriod);
		
		JLabel lblNewLabel_1 = new JLabel("Parametric Power!");  //title of the game
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 44));
		lblNewLabel_1.setBounds(146, 125, 420, 66);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html>v = v<sub>o</sub>+at</html>");    //html code to show equations with subscripts 
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(185, 80, 74, 33);
		mainPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("<html>x = x<sub>o</sub>+ v<sub>o</sub>+ 0.5gt<sup>2</sup></html>"); //more html code to show equations with subscripts and superscripts 
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(348, 76, 186, 34);
		mainPanel.add(lblNewLabel_3);
		
		playPanel = new JPanel();	//play panel instance
		contentPane.add(playPanel, "");
		playPanel.setLayout(new BorderLayout(0, 0));
		playPanel.setVisible(false);
				
		tutorPanel = new TutorPanel(1);		//tutorial panel instance
		contentPane.add(tutorPanel, "");
		tutorPanel.setLayout(null);
		tutorPanel.setVisible(false);
		
		quizPanel = new QuizPart(mainPanel,playPanel,tutorPanel);
		contentPane.add(quizPanel);		//adding the quiz panel to the title pane
		
		nextButton = new JButton("Next");	
		nextButton.addActionListener(new ActionListener()    //if the user clicks the 'back button', then the user is redirected 
		{												     //back to the home screen by setting the spcific panels visible and invisible.
			public void actionPerformed(ActionEvent e)
			{
				mainPanel.setVisible(false);		
				playPanel.setVisible(false);
				tutorPanel.value++;
				
				if(tutorPanel.value==4)
				{
					nextButton.setText("Done");
				}
				
				if(tutorPanel.value==5)
				{
					mainPanel.setVisible(true);		
					playPanel.setVisible(false);
					nextButton.setVisible(false);
					tutorPanel.value=1;
					nextButton.setText("Next");
					tutorPanel.setVisible(false);
					mainPanel.repaint();
				}
				tutorPanel.setVisible(true);
				tutorPanel.repaint();
			}
		});

		preButton = new JButton("Previous");	
		preButton.addActionListener(new ActionListener()    //if the user clicks the 'back button', then the user is redirected 
		{												    //back to the home screen by setting the spcific panels visible and invisible.
			public void actionPerformed(ActionEvent e)
			{
				tutorPanel.value--;
				if(tutorPanel.value==0)
				{
					tutorPanel.value=1;
					mainPanel.setVisible(true);		
					playPanel.setVisible(false);
					tutorPanel.setVisible(false);		
				}
				
				else
				{
					mainPanel.setVisible(false);		
					playPanel.setVisible(false);
					tutorPanel.setVisible(true);
					tutorPanel.repaint();
				}
			}
		});
		
		nextButton.setBounds(868, 610, 96, 35);
		tutorPanel.add(nextButton);

		preButton.setBounds(100, 610, 96, 35);
		tutorPanel.add(preButton);

		ImagePanel p3 = new ImagePanel();
		p3.setForeground(Color.WHITE);
		p3.setBackground(Color.WHITE);
		p3.setBounds(29, 212, 626, 429);
		mainPanel.add(p3);
		
        JPanel controlPanel = new JPanel();				//this panel is where you can choose the initial angle, velocity, and the objec to launch in the simulation.
        controlPanel.setBackground(Color.CYAN);
        playPanel.add(controlPanel, BorderLayout.SOUTH);
        
        JPanel resultPanel = new JPanel();				//this panel displays the results of the projectile motion
        resultPanel.setBackground(Color.CYAN);
        playPanel.add(resultPanel, BorderLayout.NORTH);
        
        JLabel horizontaldistanceTraveled = new JLabel("Horizontal Distance Traveled: ");
        resultPanel.add(horizontaldistanceTraveled);
        
        JLabel verticaldistanceTraveled = new JLabel("Maxmimum height reached: ");
        resultPanel.add(verticaldistanceTraveled);
        
        JButton btnPlay = new JButton("Launch!");			//adding the play button to launch the projectile
        btnPlay.addActionListener(new ButtonClickListener());
        controlPanel.add(btnPlay);
       
        JMenuBar menuBar = new JMenuBar();
        controlPanel.add(menuBar);			//check if this works
		
        controlPanel.add(new JLabel("Angle (degrees)"));   //this is the JSlider that lets the user choose what angle to launch the projectile
        angleslider = new JSlider(JSlider.HORIZONTAL, 0, 90, 45);
        angleslider.setMinorTickSpacing(2);
        angleslider.setMajorTickSpacing(10);
        angleslider.setPaintTicks(true);
        angleslider.setPaintLabels(true);
        angleslider.setLabelTable(angleslider.createStandardLabels(10));
        controlPanel.add(angleslider);
        
        controlPanel.add(new JLabel("Velocity (m/s)"));		//this is the JSlider that lets the user choose what velocity to launch the projectile
        velocityslider = new JSlider(JSlider.HORIZONTAL, 0, 70, 40);
        velocityslider.setMinorTickSpacing(2);
        velocityslider.setMajorTickSpacing(10);
        velocityslider.setPaintTicks(true);
        velocityslider.setPaintLabels(true);
        velocityslider.setLabelTable(velocityslider.createStandardLabels(10));
        controlPanel.add(velocityslider);
        
		JMenu object = new JMenu("Click to choose projectile:");	//this JMenu lets the user choose what type of object to launch
		menuBar.add(object);
		JMenuItem greenBall = new JMenuItem("Green Ball");	//here are all of the JMenuItems (the choices for the user)
		JMenuItem human = new JMenuItem("Human");
		JMenuItem piano = new JMenuItem("Piano");
		JMenuItem redBall = new JMenuItem("Red Ball");
		JMenuItem car = new JMenuItem("Car");
		JMenuItem football = new JMenuItem("Football");
		JMenuItem sumowrestler = new JMenuItem("Sumo Wrestler");
		
		object.add(greenBall);
		object.add(human);
		object.add(piano);
		object.add(redBall);
		object.add(car);
		object.add(football);
		object.add(sumowrestler);
		
		class GreenBall implements ActionListener	//these are the individual classes for the JMenuItems' actionListener
		{											//ex, if greenBall is chosen, then the JMenu text will be set for the green ball, and that object will be launched
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "greenBall";
				object.setText("Object: Green Ball");
			}
		}
		
		class Human implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "human";
				object.setText("Object: Human");
			}
		}
		
		class Piano implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "piano";
				object.setText("Object: Piano");
			}
		}
		
		class RedBall implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "redBall";
				object.setText("Object: Red Ball");
			}
		}
		
		class Car implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "car";
				object.setText("Object: Car");
			}
		}
		
		class Football implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "football";
				object.setText("Object: Football");
			}
		}
		
		class SumoWrestler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				objectThrown = "sumowrestler";
				object.setText("Object: Sumo Wrestler");
			}
		}
		
		greenBall.addActionListener(new GreenBall());
		human.addActionListener(new Human());
		piano.addActionListener(new Piano());
		redBall.addActionListener(new RedBall());
		car.addActionListener(new Car());
		football.addActionListener(new Football());
		sumowrestler.addActionListener(new SumoWrestler());
       
        JButton btnQuit = new JButton("Back");			//back button for the user to exit the current panel and return to the home screen. 
        btnQuit.addActionListener(new ButtonClickListener());
        controlPanel.add(btnQuit);
        
        displayPanel = new Simulation(horizontaldistanceTraveled, verticaldistanceTraveled);
        
        displayPanel.setBackground(Color.BLUE);
        playPanel.add(displayPanel, BorderLayout.CENTER);
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
	}
	
	class ButtonClickListener implements ActionListener 	 //this is the class that determines which values of velocity and angle 
    {	  													 //is chosen, and the projectile motion is calculated respectively. 
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand(); 
            if(command.equalsIgnoreCase("Launch!")) 
            {      
            	VELOCITY=velocityslider.getValue();
            	ANGLE=angleslider.getValue();
            	  
                displayPanel.setSpeedAngle(VELOCITY, ANGLE, objectThrown);
                if(displayPanel.timer !=null)
                {
                     displayPanel.stopTimer();                	  
                }
                displayPanel.runIt();
            } 
            else if(command.equalsIgnoreCase("Back"))  
            {
            	mainPanel.setVisible(false);
                playPanel.setVisible(false);
                tutorPanel.setVisible(false);
                quizPanel.setVisible(true);
                quizPanel.getAns().setVisible(true);
            }
        }   
	}
	
	public void actionPerformed(ActionEvent e)   //this method will make the appropriate panels visible and invisible depending on which button is clicked on.
	{
		String s = e.getActionCommand();
		if(s == "Play!")
		{
			playPanel.setVisible(false);		//ex: only the quiz panel is set visible when the user clicks the play button
			quizPanel.setVisible(true);
			quizPanel.getAns().setVisible(true);
			mainPanel.setVisible(false);
			tutorPanel.setVisible(false);
		}
		
		else if(s == "Tutorial")
		{
			tutorPanel.setVisible(true);
			mainPanel.setVisible(false);
			quizPanel.setVisible(false);
			playPanel.setVisible(false);
			nextButton.setVisible(true);
		}
		
		else if(s == "Quit")
		{
			System.exit(0);
		}
		
		if(s == "Next")
		{
			playPanel.setVisible(false);
			mainPanel.setVisible(true);
			quizPanel.setVisible(false);
			tutorPanel.setVisible(false);
		}
	}
}

class ImagePanel extends JPanel				//this is the class that draws the title page image of the projectile motion; it uses 
{											//the paintComponent method to draw the image. 
	public ImagePanel()
	{
		setLayout(new BorderLayout());
	}
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon i = new ImageIcon("projectile-motion-formulas.PNG");
		i.paintIcon(this,g,0,50);
	}
}

class TutorPanel extends JPanel		//this class is the tutorial panel; it includes four pages that teach the user a basic understanding of 1D and 2D kinematics 
{ 									//it draws images that are the pages; it was done like this so the program is more concise and doesn't need a bunch of JLabels. 
	public int value;
	private String imageName;
	private Image image;
	
	public TutorPanel(int value1)
	{
		this.value=value1;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(value==1)
			imageName = "1T.png";
		if(value==2)
			imageName = "2T.png";		
		if(value==3)
			imageName = "3T.png";		
		if(value==4)
			imageName = "4T.png";		

		try
		{
			image = ImageIO.read(new File(imageName));
		}
		catch (IOException e)
		{
			System.err.println("\n\n ERROR: " + imageName + " can't be found.\n\n");
			e.printStackTrace();
		}	
		g.drawImage(image,0,0,990,668,this);
	 }
}
