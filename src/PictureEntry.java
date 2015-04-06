import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureEntry extends JPanel {
	
	JPanel select;
	JPanel entry;
	
	public PictureEntry(){
		Image[][] images = new Image[10][7];
        
        ImageIcon icon; 
        JLabel thumb;
        
        int pool = 1, length = 10, choice = 7;
        
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
        final JPanel panel = this;
        this.setLayout(lay);
        
        Image img = null;
        
        select = new JPanel();
        entry = new JPanel(new GridLayout(0,10));
        
        for (int x = 1;x<=10;x++){        	
        	img = images[x-1][0].getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        	icon = new ImageIcon(img); 
        	thumb = new JLabel();
        	thumb.setIcon(icon);
        	
        	thumb.addMouseListener(new MouseListener() {
        		@Override
        		public void mouseClicked(MouseEvent arg0) {
        			System.out.println("CLICKED");
        			lay.next(panel);
        		}
				public void mouseEntered(MouseEvent e){};
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
        	});
        	
        	entry.add(thumb);
        }      
        
        this.add(select);
        this.add(entry);
        
        lay.next(this);
        //lay.next(this);
		
	}
	
	public void createSelect(){
		
	}
	}