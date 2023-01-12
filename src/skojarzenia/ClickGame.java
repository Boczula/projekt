package skojarzenia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.util.concurrent.TimeUnit;



public class ClickGame extends JPanel implements MouseListener {
	/**
	 * 
	 */
	 
	private static final long serialVersionUID = 1L;
	JFrame  jf = new JFrame(); 		 			// frame
	JPanel  jp = new JPanel();  				// panel to add in a frame
	JButton jb = new JButton("Click ME!!");	 	// button
	Random  rand = new Random();				// for random positioning 
	String  msg1 = "Try to click me!!"; 
	JLabel label = new JLabel(msg1); 			// setting the label
	int seconds = 0;
	public boolean buttonClicked;
	
	
	public ClickGame()
	{
		jp.setBackground(Color.WHITE); //setting the panel color to white

		jf.add(jp); //adding panel in the frame
		jp.setLayout(null); // null layout to move button freely in a frame
		label.setBounds(225, 235, 200, 20); //setting the label
		label.setFont(new Font("Ink Free",Font.BOLD,20));

		jp.add(label); //adding label
		jp.setBounds(0, 0, 650, 650); // setting panel boundries

		jp.add(jb); //adding button in panel
		jb.setBounds(235,255, 120, 30); //positioning button in panel
		jb.addMouseListener(this); // adding mouse listener to the button
	}
	
	public void setComponents()
	{
		jf.setSize(650, 650); //setting frame size
		
	
		jf.setTitle("GAME - CLICK ME IF YOU CAN !!"); //setting title
		jf.setVisible(true); // setting the frame to visible
	}


	public void mouseEntered(MouseEvent me) {
		Timer pause = new Timer(130, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  if(me.getSource() == jb) {	//checking if mouse entered jbutton

				    	jb.setBounds(rand.nextInt(jf.getWidth()-100),rand.nextInt(jf.getHeight()-100),120,30); // randomizing position
				    }
			}
		});
		pause.setRepeats(false);
		pause.start();
			
  
	}
	public void mouseClicked(MouseEvent mc) {   // chech if button was clicked
	    if (mc.getSource() == jb) {
	    	buttonClicked = true;
	        jf.dispose();
	    }
	}
	
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}