package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


/**
 * Created by Paweł on 2017-04-02.
 */
public class ScoresPanel extends JPanel {

    private GameFrame gameFrame;
    private static int defaultWidth;
    private static int defaultHeight;
    Image scoresImage;
    JButton returnButton;
    String scoresPath = "data\\resources\\help_pacman.jpg";

    public ScoresPanel(GameFrame visibleFrame){
        defaultWidth = Config.dimensionX;
        defaultHeight = Config.dimensionY;

        scoresImage = (new ImageIcon(scoresPath).getImage());
        setPreferredSize(new Dimension(scoresImage.getWidth(this), scoresImage.getHeight(this)));

        //this.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        //this.setVisible(true);
        this.gameFrame = visibleFrame;
        this.setLayout(new BorderLayout());

        returnButton = new JButton("Powrót");
        returnButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                gameFrame.changePanel(GameFrame.panel_menu);
            }
        });

        this.add(returnButton, BorderLayout.SOUTH);

    }

    /*public Dimension getPreferredSize(defaultWidth, defaultHeight){

        return new Dimension(defaultWidth, defaultHeight);
    }*/

    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(scoresImage, 0, 0, getWidth(), getHeight(), this);

    }

}
