
import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import javax.swing.*;

public class Scheme {
	JFrame frame;
	int p[][] = new int[3][];
    int c[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int index = 0;
	
    PictureDisplay dis;
    PictureEntry enter;
    JPanel message;
    
    JButton button;
    JLabel text;
    
    int fail;
    
    PrintWriter out = null;
    
	private void createAndShowGUI() {
				
		
        frame = new JFrame("Entry");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        
    	p[0] = generatePicturePassword();
    	p[1] = generatePicturePassword();
    	p[2] = generatePicturePassword();  
        
        //Display the window
    	this.complete();
        frame.pack();
        frame.setVisible(true);

        try{
			out = new PrintWriter(new BufferedWriter(new FileWriter("Log.txt", true)));
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
       
    }
	
	
	public void complete(){
		final Scheme scheme = this;
		frame.getContentPane().removeAll();
		if (index < 3){
			
			dis = new PictureDisplay(p[index]);
			enter = new PictureEntry(p[index],p[index]);
			message = new JPanel();
						
			text = new JLabel(); 
			text.setText("THIS IS PASSWORD " + (index+1) + ", ENTER IT BELOW, AND THEN PUSH CONTINUE");
			message.add(text);
			button = new JButton();
			
			button.setText("CONTINUE");
			button.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		                if (scheme.enter.verify() == true){
		                	index++;
		                	scheme.complete();
		            	}
		            }
	        	}
			);
			
			message.add(button);
			frame.getContentPane().setLayout(new GridLayout(3,0));
			frame.getContentPane().add(dis);
			frame.getContentPane().add(message);
			frame.getContentPane().add(enter);
		} else if (index < 6) {
			
			out.print("\nStart:"+System.currentTimeMillis());
			//System.out.print("Password Entry Start:"+System.currentTimeMillis());
			fail = 0;
			enter = new PictureEntry(c,p[index-3]);
			frame.getContentPane().setLayout(new GridLayout(2,0));
			frame.getContentPane().add(enter);
			
			message = new JPanel();
			text = new JLabel(); 
			text.setText("ENTER PASSWORD " + (index-2) + ", AND THEN PUSH ENTER");
			message.add(text);
			
			button = new JButton();
			message.add(button);
			button.setText("ENTER");
			
			frame.getContentPane().add(enter);
			frame.getContentPane().add(message);
			
			button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	                if (scheme.enter.verify() == true){
	                	scheme.out.print(",Correct:"+System.currentTimeMillis());
	                	//System.out.println(",Password Correct:"+System.currentTimeMillis());
	                	System.out.println("CORRECT PASSWORD");
	                	index++;
	                	scheme.complete();
	            	} else {
	            		scheme.out.print(",Incorrect "+(fail+1)+":"+System.currentTimeMillis());
	            		//System.out.print(",Password Incorrect "+(fail)+":"+System.currentTimeMillis());
	            		System.out.println("WRONG PASSWORD");
	            		fail++;
	            		if (fail >= 3){
	            			index++;
	            			scheme.complete();
	            		}
	            	}
	            }
        	});
			
		} else {
			out.close();
			message = new JPanel();
			text = new JLabel();
			text.setText("WOW, THIS IS A TERRIBLE PASSWORD SCHEME");
			message.add(text);
			frame.getContentPane().add(message);
		}
		
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
	}
	
    public static void main(String[] args) {
    	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
            	 Scheme s = new Scheme();
            	 s.createAndShowGUI();
            	 //createAndShowGUI();
             }
         });
    }
    
    public int[] generatePicturePassword(){
    	Random r = new Random();
    	int temp[] = new int[10];
    	for (int x = 0; x < 10; x++){
    		temp[x] = r.nextInt(7);
    	}
    	return temp;
    }
}