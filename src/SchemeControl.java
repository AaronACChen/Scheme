
import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import javax.swing.*;

public class SchemeControl {
	JFrame frame;
	String p[] = new String[3];
    int index = 0;
	
    JPanel message;
    
    JButton button;
    JLabel text;
    JTextField textField;
    
    int fail;
    
    PrintWriter out = null;
    
	private void createAndShowGUI() {		
        frame = new JFrame("Entry");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        
    	p[0] = generatePassword();
    	p[1] = generatePassword();
    	p[2] = generatePassword();  
        
        //Display the window
    	this.complete();
        frame.pack();
        frame.setVisible(true);

        try{
			out = new PrintWriter(new BufferedWriter(new FileWriter("LogControl.txt", true)));
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
       
    }
	
	
	public void complete(){
		final SchemeControl scheme = this;
		frame.getContentPane().removeAll();
		if (index < 3){
			frame.getContentPane().setLayout(new GridLayout(3,0));
			
			text = new JLabel(); 
			text.setText(p[index]);
			message = new JPanel();
			message.add(text);
			frame.getContentPane().add(message);
			
			text = new JLabel(); 
			text.setText("THIS IS PASSWORD " + (index+1) + ", ENTER IT BELOW, AND THEN PUSH CONTINUE");
			message = new JPanel();
			message.add(text);
			button = new JButton();
			
			button.setText("CONTINUE");
			button.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		                System.out.println(scheme.textField.getText());
		                if (scheme.textField.getText().equals(p[index]) || true){
		                	index++;
		                	scheme.complete();
		                }
		            }
	        	}
			);
			frame.getContentPane().add(message);
			message.add(button);
			
			textField = new JTextField(20);
			textField.setHorizontalAlignment(JTextField.CENTER);
			frame.getContentPane().add(textField);

		} else if (index < 6) {
			
			out.print("\nStart:"+System.currentTimeMillis());
			//System.out.print("Password Entry Start:"+System.currentTimeMillis());
			fail = 0;

			frame.getContentPane().setLayout(new GridLayout(3,0));
						
			text = new JLabel(); 
			text.setText("ENTER PASSWORD " + (index-2) + ", AND THEN PUSH ENTER");
			//frame.getContentPane().add(text);
			
			message = new JPanel();
			message.add(text);
			
			button = new JButton();
			message.add(button);
			button.setText("ENTER");
			
			frame.getContentPane().add(message);
			
			textField = new JTextField(20);
			textField.setHorizontalAlignment(JTextField.CENTER);
			frame.getContentPane().add(textField);
			
			button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {	
	            	if (scheme.textField.getText().equals(p[index-3])){
	            		scheme.out.print(",Correct:"+System.currentTimeMillis());
	            		System.out.println(",Password Correct:"+System.currentTimeMillis());
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
			
			button = new JButton();
			button.setText("RESTART");
			frame.getContentPane().add(button);
			button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {	
	            	p[0] = generatePassword();
	            	p[1] = generatePassword();
	            	p[2] = generatePassword(); 
	            	
	            	try{
	        			out = new PrintWriter(new BufferedWriter(new FileWriter("LogControl.txt", true)));
	        		} catch (IOException f) {
	        		    //exception handling left as an exercise for the reader
	        		}
	            	index = 0;
	            	scheme.complete();
	            }
        	});
		}
		
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
	}
	
    public static void main(String[] args) {
    	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
            	 SchemeControl s = new SchemeControl();
            	 s.createAndShowGUI();
            	 //createAndShowGUI();
             }
         });
    }
    
    public String generatePassword(){
    	String password = "";
    	Random r = new Random();
    	for (int x = 0;x<6;x++){
    		password = password + (char)(r.nextInt(26) + 97);
    	}
    	return password;
    }
}