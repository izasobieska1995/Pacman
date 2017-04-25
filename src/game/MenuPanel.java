package game;

import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Paweł on 2017-04-01.
 */
public class MenuPanel extends JPanel{

    //ImageFrame panel;
    JButton startButton,levelButton, scoreButton, helpButton, soundButton, endButton;
    JPanel buttons;

    GamePanel gamePanel;
    HelpPanel helpPanel;
    ScoresPanel scoresPanel;


    String menuPath = "data\\resources\\pacman_logo.jpg";
    protected static String nickname;

    Image menuImage;
    private GameFrame gameFrame;

    private static int defaultWidth;
    private static int defaultHeight;


    public MenuPanel(GameFrame visibleFrame){

        menuImage = (new ImageIcon(menuPath).getImage());
        defaultWidth = menuImage.getWidth(this);/**Wybor rozmiarów obrazka.*/
        defaultHeight = menuImage.getHeight(this);

        setPreferredSize(new Dimension(defaultWidth, defaultHeight));/**Ustawienie preferowancyh wymiarów komponentu.**/

        this.gameFrame = visibleFrame;
        this.setLayout(new BorderLayout());

        gamePanel = new GamePanel(gameFrame);
        helpPanel = new HelpPanel(gameFrame);
        scoresPanel = new ScoresPanel(gameFrame);


        buttons = new JPanel(new GridLayout(6, 1, 0 , 0));/**Utworzenie Panelu z 6 przyciskami.**/

        /*buttons = new JPanel();
        Dimension panelSize = new Dimension(defaultWidth,  defaultHeight/2);
        buttons.setPreferredSize(panelSize);

        Dimension buttonSize = new Dimension(defaultWidth,  defaultHeight/15);
        startButton = new JButton("Start");
        levelButton = new JButton("Poziom");
        scoreButton = new JButton("Najlepsze wyniki");
        helpButton = new JButton("Pomoc");
        soundButton = new JButton("Dźwięk");
        endButton = new JButton("Koniec");

        startButton.setPreferredSize(buttonSize);
        levelButton.setPreferredSize(buttonSize);
        scoreButton.setPreferredSize(buttonSize);
        helpButton.setPreferredSize(buttonSize);
        soundButton.setPreferredSize(buttonSize);
        endButton.setPreferredSize(buttonSize);

        buttons.add(startButton);
        buttons.add(levelButton);
        buttons.add(scoreButton);
        buttons.add(helpButton);
        buttons.add(soundButton);
        buttons.add(endButton);*/

        buttons.add(startButton = new JButton("Start"));/**Dodawanie przycisków do Panelu.**/
        buttons.add(levelButton = new JButton("Poziom"));
        buttons.add(scoreButton = new JButton("Najlepsze wyniki"));
        buttons.add(helpButton = new JButton("Pomoc"));
        buttons.add(soundButton = new JButton("Dźwięk"));
        buttons.add(endButton = new JButton("Koniec"));
        //panel.add(buttons,BorderLayout.SOUTH);

        startButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            nickname =  JOptionPane.showInputDialog(null, "Podaj nazwę gracza:", "Nazwa gracza",JOptionPane.QUESTION_MESSAGE);
                System.out.print(nickname);/**Okno z prosba o dane wejściowe.**/
            //if(nickname !=null);


                gameFrame.changePanel(gamePanel);}});

        scoreButton.addActionListener( new ActionListener() {/**Utworzenie słuchacza - aby była reakcja na przycisk.*/
            public void actionPerformed(ActionEvent e) {/**Generowana gdy zdarzenie powiąze się ze słuchaczem.**/
                gameFrame.changePanel(scoresPanel);
            }});

        helpButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameFrame.changePanel(helpPanel);
            }});

        endButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                System.exit(0);
            }});

        this.add(buttons, BorderLayout.SOUTH);
        this.setVisible(true);
       /* while(true){
            gamePanel.animate();

        }*/
        //setLocationRelativeTo(null);
    }


    /*public void changePanel(JPanel next){
        this.remove(currentPanel);			// usuniecie z ramki obecnie wyswietlanego panelu
        this.add(next);						// wlozenie nowego panelu
        repaint();							// odrysowanie zawartości
        this.pack();						// spakowanie w ramke
        currentPanel = next;				// przypisanie referencji na obecnie wyswietlany panel
    }*/


    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, getWidth(), getHeight(), this);

    }

    /*public void newSize(int width, int height){

        Dimension buttonSize = new Dimension(width,  height/15);
        startButton.setPreferredSize(buttonSize);
        levelButton.setPreferredSize(buttonSize);
        scoreButton.setPreferredSize(buttonSize);
        helpButton.setPreferredSize(buttonSize);
        soundButton.setPreferredSize(buttonSize);
        endButton.setPreferredSize(buttonSize);
        //startButton.setFont(new Font("TimesRoman", Font.PLAIN, (int) (10*Math.sqrt((double) width*height/defaultWidth/defaultHeight)) ));
        //levelButton.setFont(new Font("TimesRoman", Font.PLAIN, (int) (10*Math.sqrt((double) width*height/defaultWidth/defaultHeight) )));
        //scoreButton.setFont(new Font("TimesRoman", Font.PLAIN, (int) (10*Math.sqrt((double) width*height/defaultWidth/defaultHeight)) ));

        defaultWidth = menuImage.getWidth(this);
        defaultHeight = menuImage.getHeight(this);

        Dimension panelSize = new Dimension(width,  defaultHeight/2);
        buttons.setPreferredSize(panelSize);

    }*/

    /*public Dimension getPreferredSize(){

        return new Dimension(defaultWidth, defaultHeight);

    }*/



}

