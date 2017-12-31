//Eric Yang
//Period 3
//Simulation.java
//This class is the Simulation; the user can choose from several values, including the initial angle,
//velocity, and object to launch. Then if the user clicks 'launch', the object will be 'thrown' into the air 
//and follow a parabolic path. After the object lands, the horizontal and vertical distance traveled
//will be displayed on the top part of the simulation, in the JLabels. Limiting cases: if the user
//chooses an angle of zero degrees, both horizontal and vertical distances traveled will be 0 meters. 
//If the angle is 90 degrees, then the horizontal distance traveled will be zero, vertical distance
//will depend on the initial velocity.

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class Simulation extends JPanel 	//this is the game panel that actually does the calculations of the projectile motion; it uses a timer too.
{		
	 
    private int x_pos, y_pos;
    private double vx=0;
    private double vy=0;
    Timer timer;
    private Image image;	
    private String imageName;
    private double horizontalDistance, verticalDistance;
    private double initialSpeed, initialAngle;
	private JLabel h, v;
	public String hd, vd;

    public Simulation(JLabel h, JLabel v)	//this is the constructor that gets passed two variables 
    {										//that will set the text of the horizontal and vertical distances traveled 
    	this.h = h;
    	this.v = v;
    	x_pos=0;
    	y_pos=540;
    	try
    	{
  			image = ImageIO.read(new File("redBall.png"));
  	    }
  	    catch (IOException e)
    	{
  			System.err.println("\n\n ERROR: redBall.png can't be found.\n\n");
  			e.printStackTrace();
  	    }
    }

    public void stopTimer()		//this method stops the timer, and also stops the drawing 
    {
    	timer.stop();
    }

    public void runIt()			//run method to instantiate a timer and start the timer
    {     
       timer = new Timer(50, new ActionListener() 
       {
    	   public void actionPerformed(ActionEvent e) 
    	   {
    		   moveBall(timer);
               repaint();
             }
         });
         timer.start();            
    }
   
    public void setSpeedAngle(int speed_1, int angle_1, String objectThrown)
    {							//this method is the first of the two methods that actually does the calculations for the projectile motion
       this.x_pos=0;			//it gets passed the initial angle and velocity and also draws the object the user chose to launch. 
       this.y_pos=540;
       
       initialSpeed = speed_1;
       initialAngle = (angle_1)*(Math.PI/180);
       
       if(angle_1 == 0)
    	   speed_1 = 0;
       
       this.vx = speed_1 * Math.cos((Math.PI/180) *angle_1);
       this.vy = speed_1 * Math.sin((Math.PI/180) *angle_1);
       
       if(objectThrown.equalsIgnoreCase("greenBall"))
       {
    	   imageName = "greenBall.png";
       }
       else if(objectThrown.equalsIgnoreCase("human"))
       {
    	   imageName = "human.png";
       }
       else if(objectThrown.equalsIgnoreCase("piano"))
       {
    	   imageName = "piano.png";
       }
       else if(objectThrown.equalsIgnoreCase("redBall"))
       {
    	   imageName = "redBall.png";
       }
       else if(objectThrown.equalsIgnoreCase("car"))
       {
    	   imageName = "car.png";
       }
       else if(objectThrown.equalsIgnoreCase("football"))
       {
    	   imageName = "football.png";
       }
       else if(objectThrown.equalsIgnoreCase("sumowrestler"))
       {
    	   imageName = "sumowrestler.png";
       }
       
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
   
    public void moveBall(Timer timer) 	//this is the second of the two methods designed to do all of the calculations for the trajectory of the object. 
    {     								//it uses kinematics equations to help the program out. Once the timer has stopped, the horizontal and vertical 
    	double t = 0.1;					//distance traveled is displayed on a JLabel at the top of the simulation. 
    	
	    x_pos += (vx*t)*2;
	    y_pos -= (vy*t)*2;
	    
	    vy -= 9.8 *t;    
	    
	    if((x_pos>1000||y_pos>540))
	    {
	    	timer.stop();
	    	horizontalDistance = (int)(((Math.pow(initialSpeed,2))*(Math.sin(2*initialAngle)))/(9.8));
	    	verticalDistance = (int)(((Math.pow(initialSpeed,2))*(Math.pow(Math.sin(initialAngle), 2)))/(19.6));
	    	
	    	if(initialAngle == (Math.PI)/(2))
	    	{
	    		horizontalDistance = 0;
	    		h.setText("Horizontal Distance Traveled: " + horizontalDistance + " meters");
	    		v.setText("Maximum Height reached: " + verticalDistance + " meters");
	    	}
	    	
	    	else if(initialAngle == 0)
	    	{
	    		horizontalDistance = 0;
	    		verticalDistance = 0;
	    		h.setText("Horizontal Distance Traveled: " + horizontalDistance + " meters");
	    		v.setText("Maximum Height reached: " + verticalDistance + " meters");
	    		timer.stop();
	    	}
	    	
	    	else
	    	{
	    		h.setText("Horizontal Distance Traveled: " + horizontalDistance + " meters");
	    		v.setText("Maximum Height reached: " + verticalDistance + " meters");
	    	}
	    }
    }
    
    public void paintComponent(Graphics g)		//the paintComponent method that draws the ball as the timer ticks 
    {											//the motion is parabolic 
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g.drawImage(image, x_pos, y_pos, 40, 40, this);		//drawing the object
        g2d.dispose();
    }
}  