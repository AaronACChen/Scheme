import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureDisplay extends JPanel {
	
	private static final long serialVersionUID = 1L;

	int pool = 1, length = 10, choice = 7;
	
	JPanel entry;
	
	int currentEntry[] = {0,1,2,3,4,3,2,1,0,2};
	int password[];
	
	JLabel thumbs[] = new JLabel[length];
	final Image[][] images = new Image[10][7];
	Image empty;
	
	
	public PictureDisplay(int i[]){
		
		currentEntry = i;
		
        ImageIcon icon; 
        JLabel thumb; 
        
        try {
			empty = ImageIO.read(new File("Empty.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        for (int x = 1;x<=length;x++){
        	for (int y = 1;y<=choice;y++){
        		try {
					images[x-1][y-1] = ImageIO.read(new File("Pool"+pool+"/"+x+"-"+y+".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
         
        final CardLayout lay = new CardLayout();
        this.setLayout(lay);
        
        Image img = null;
        
        entry = new JPanel(new GridLayout(0,length));

        
        for (int x = 0;x<length;x++){        	
        	if (currentEntry[x] == -1){
				img = empty.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			} else {
				img = images[x][currentEntry[x]].getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			}
        	
        	icon = new ImageIcon(img); 
        	thumbs[x] = new JLabel();
        	thumbs[x].setIcon(icon);
        	
        	entry.add(thumbs[x]);
        }      
        
        this.add(entry);		
	}
	
}