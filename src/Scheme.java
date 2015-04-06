
import javax.swing.*;

public class Scheme {
	
private static void createAndShowGUI() {
				
	
        JFrame frame = new JFrame("??");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.


		PictureEntry enter = new PictureEntry();
		frame.getContentPane().add(enter);

        
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
    public static void main(String[] args) {
    	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 createAndShowGUI();
             }
         });
    }
}