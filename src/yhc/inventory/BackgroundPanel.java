/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yhc.inventory;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Saqib
 */
class BackgroundPanel extends JPanel {
    Image image;
    public BackgroundPanel() {
        image = (new ImageIcon(getClass().getResource("background.jpg"))).getImage();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null) 
            g.drawImage(image, (this.getWidth()/2 - image.getWidth(this)/2), (this.getHeight()/2 - image.getHeight(this)/3)+20,null);
    }
}