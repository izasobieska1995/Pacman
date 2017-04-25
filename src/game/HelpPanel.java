package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Created by Paweł on 2017-04-02.
 */
public class HelpPanel extends JPanel {

    private GameFrame gameFrame;
    Image helpImage;
    JButton returnButton;
    String helpPath = "data\\resources\\help_pacman.jpg";

    private static int defaultWidth;
    private static int defaultHeight;

    HelpPanel(GameFrame visibleFrame) {

        /*defaultWidth = Config.dimensionX;
        defaultHeight = Config.dimensionY;
        System.out.println(defaultWidth);
        System.out.println(defaultHeight);*/

        helpImage = (new ImageIcon(helpPath).getImage());/**getImage - zwraca obrazek za pomoca ścieżki**/
        //setPreferredSize(new Dimension(helpImage.getWidth(this), helpImage.getHeight(this)));
        setPreferredSize(new Dimension(Config.dimensionX, Config.dimensionY));
        //defaultWidth = helpImage.getWidth(this);
        //defaultHeight = helpImage.getHeight(this);

        this.gameFrame = visibleFrame;
        //this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());

        JPanel steerPanel = new JPanel(new BorderLayout());
        JLabel labelSteer = new JLabel("Sterowanie", SwingConstants.CENTER);
        JTextArea textSteer = new JTextArea();
        textSteer.setEditable(false);/**Ustawienie czy teskt powienien być edytowalny.*/

        try{
            readFileToTextArea(textSteer, "data\\config\\steerage.txt");
        }
        catch(IOException ioe){
            ioe.printStackTrace();/**Mówi gdzie i jaki wyjątek.*/
        }

        steerPanel.add(labelSteer, BorderLayout.NORTH);/**Dodanie przycisku wraz z ustawieniem lokaliazacji.**/
        steerPanel.add(textSteer, BorderLayout.CENTER);

        JPanel rulesPanel = new JPanel(new BorderLayout());
        JLabel labelRules = new JLabel("Zasady gry", SwingConstants.CENTER);/**SwingConstans - rozmieszczenie elementów.**/
        JTextArea textRules = new JTextArea();
        textRules.setEditable(false);

        try{
            readFileToTextArea(textRules, "data\\config\\rules.txt");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        rulesPanel.add(labelRules, BorderLayout.NORTH);
        rulesPanel.add(textRules, BorderLayout.CENTER);


        returnButton = new JButton("Powrót");
        returnButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                gameFrame.changePanel(GameFrame.panel_menu);
            }
        });/**Metoda wywoływana gdy wygenrowane zostanie zdarzenie na obiektcie powiązanym ze słuchaczem.**/


        JPanel finalPanel = new JPanel(new BorderLayout());

        finalPanel.add(steerPanel, BorderLayout.NORTH);
        finalPanel.add(rulesPanel, BorderLayout.CENTER);
        finalPanel.add(returnButton, BorderLayout.SOUTH);

        //wprowadzam wielkość panelu menu
        GridBagConstraints c = new GridBagConstraints();/**Insets - określa przestrzeń jaka może zajmowac kontener.**/
        c.insets = new Insets((helpImage.getHeight(this)/4), 0, 0,0);

        this.add(finalPanel, c);
        //setVisible(true);
    }

    void readFileToTextArea(JTextArea textArea, String textFile) throws IOException {

        String line = null;

        try {
            FileReader fileReader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            textArea.read(bufferedReader, textFile);
            //LineNumberReader lnr = new LineNumberReader(bufferedReader);
            //while ((line = lnr.readLine()) != null) {
            fileReader.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {/**Rysowanie komponentów*/
        //super.paintComponent(g);
        g.drawImage(helpImage, 0, 0, getWidth(), getHeight(), this);/**Rysowanie w zalżności od
         wymiarow i wpsółrzędnych.**/

    }

    /*public Dimension getPreferredSize(){

        return new Dimension(defaultWidth, defaultHeight);

    }*/

}
