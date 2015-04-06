import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureEntry extends JPanel {
	
	private static final long serialVersionUID = 1L;

	int pool = 1, length = 10, choice = 7;
	
	JPanel select;
	JPanel entry;
	
	int currentEntry[] = new int[length];
	int password[] = {0,1,2,3,4,3,2,1,0,2};
	
	
	JLabel thumbs[] = new JLabel[length];
	final Image[][] images = new Image[10][7];
	
	
	public PictureEntry(){
		
        
        ImageIcon icon; 
        JLabel thumb;        
        
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
        final PictureEntry panel = this;
        this.setLayout(lay);
        
        Image img = null;
        
        select = new JPanel(new GridLayout(0,choice));
        entry = new JPanel(new GridLayout(0,length));

        
        for (int x = 0;x<length;x++){        	
        	img = images[x][0].getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
        	
        	icon = new ImageIcon(img); 
        	thumbs[x] = new JLabel();
        	thumbs[x].setIcon(icon);
        	
        	thumbs[x].addMouseListener(new MouseListener() {
        		int index = 0;
        		public MouseListener setIndex(int i){
        			index = i;
        			return this;
        		}
        		
        		public void mouseClicked(MouseEvent arg0){}
				public void mouseEntered(MouseEvent e){};
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {
					System.out.println("CLICKED");
        			lay.next(panel);
        			
        			Image img = null;
        			ImageIcon icon; 
        	        JLabel thumb;
        	        
        			select.removeAll();
        			for (int x = 0;x<choice;x++){
        				img = images[index][x].getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
        				icon = new ImageIcon(img); 
                    	thumb = new JLabel();
                    	thumb.setIcon(icon);
                    	
                    	thumb.addMouseListener(new MouseListener() {
                    		int index1 = 0;
                    		int index2 = 0;
                    		public MouseListener setIndex(int i1,int i2){
                    			index1 = i1;
                    			index2 = i2;
                    			return this;
                    		}
                    		
                    		public void mouseClicked(MouseEvent arg0){};
                    		public void mouseEntered(MouseEvent e){};
            				public void mouseExited(MouseEvent e) {}
            				public void mousePressed(MouseEvent e) {
            					lay.next(panel);
                    			currentEntry[index1] = index2;
                    			//System.out.println(index2);
                    			panel.redraw();
                    			verify();
            				}
            				public void mouseReleased(MouseEvent e) {}
                    	}.setIndex(index,x));
                    	
                    	select.add(thumb);
        			}
				}
				public void mouseReleased(MouseEvent e) {}
        	}.setIndex(x));
        	
        	entry.add(thumbs[x]);
        }      
        
        this.add(select);
        this.add(entry);
        
        lay.next(this);
        //lay.next(this);
		
	}
	
	public void redraw(){
		Image img;
		Icon icon;
		for (int x = 0;x<length;x++){
			System.out.print(currentEntry[x]);
			
			img = images[x][currentEntry[x]].getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(img); 
			thumbs[x].setIcon(icon);
		}
		System.out.println("");
		this.repaint();
	}
	
	public void verify(){
		boolean correct;
		
		correct = true;
		for(int x = 0; x < length;x++){
			if (currentEntry[x] != password[x]){
				correct = false;
				break;
			}
		}
		if (correct){
			System.out.println("CORRECT PASSWORD WOW");
		}
	}
}