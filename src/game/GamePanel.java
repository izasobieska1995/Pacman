package game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paweł on 2017-04-06.
 */


public class GamePanel extends JPanel implements Runnable, KeyListener {

    private GameFrame gameFrame;
    private Image background;
    private static int defaultWidth;
    private static int defaultHeight;
    public static Level level;
    String levelPath = "data/level/txt/level1.txt";
    String path1 = "data/level/jpg/right_open.jpg";
    JButton pauseButton;
    MenuPanel menuPanel;
    //public static Player player;
    static int rectWidth, rectHeight;
    boolean isrunning = false;
    Map<PlayerGraphicsState, BufferedImage> PlayerTexMap;
    Player player;

    public GamePanel(GameFrame visibleFrame) {/**Tworzenie Panelu Game o zadanych wymiarach.**/

        Dimension dimension = new Dimension(Config.dimensionX, Config.dimensionY);
        setPreferredSize(dimension);

        this.gameFrame = visibleFrame;
        pauseButton = new JButton("Pauza");
        //menuPanel = new MenuPanel(gameFrame);

        pauseButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameFrame.changePanel(GameFrame.panel_menu);
            }});


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension buttonSize = new Dimension(100,100);
        pauseButton.setAlignmentX(Component.RIGHT_ALIGNMENT);/**Wyrówananie w prawo przycisku pauzy.*/
        pauseButton.setSize(buttonSize);/**Ustawienie rozmiaru.*/

        PlayerTexMap = new HashMap<PlayerGraphicsState, BufferedImage>();
        this.add(pauseButton);
        runLevel();
        addKeyListener(this);
        player = new Player();//put - powiazanie wartości wyliczeniowych z plikami odczytanymi ze ściezki
        try{
            PlayerTexMap.put(PlayerGraphicsState.DOWN_CLOSE, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\down_close.png")));
            PlayerTexMap.put(PlayerGraphicsState.DOWN_HALF, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\down_half.png")));
            PlayerTexMap.put(PlayerGraphicsState.DOWN_OPEN, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\down_open.png")));
            PlayerTexMap.put(PlayerGraphicsState.UP_CLOSE, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\up_close.png")));
            PlayerTexMap.put(PlayerGraphicsState.UP_HALF, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\up_half.png")));
            PlayerTexMap.put(PlayerGraphicsState.UP_OPEN, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\up_open.png")));
            PlayerTexMap.put(PlayerGraphicsState.RIGHT_CLOSE, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\right_close.png")));
            PlayerTexMap.put(PlayerGraphicsState.RIGHT_HALF, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\right_half.png")));
            PlayerTexMap.put(PlayerGraphicsState.RIGHT_OPEN, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\right_open.png")));
            PlayerTexMap.put(PlayerGraphicsState.LEFT_CLOSE, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\left_close.png")));
            PlayerTexMap.put(PlayerGraphicsState.LEFT_HALF, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\left_half.png")));
            PlayerTexMap.put(PlayerGraphicsState.LEFT_OPEN, ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\level\\jpg\\left_open.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    Player get_player(){
        return player; }

    void runLevel(){
        try{
            new Level(levelPath);
        }catch(Exception io){
            System.out.println("Exception: " + io);
        }
    }
    public void run(){
       // while(isRunning){

      //  }

    }

    public void paintComponent(Graphics g) {/**Rysowanie komponentu.**/
        //super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.RED);
        //int rectWidth = Config.dimensionX/Level.gridX_size, rectHeight = Config.dimensionY/Level.gridY_size;
        rectWidth = getWidth() / Level.gridX_size;
        rectHeight = getHeight() / Level.gridY_size;

        //g.setColor(Color.WHITE);
        //g.fillRect(50,50,200,100);

        for (int j = 0; j < Level.gridY_size; j++) {
            for (int i = 0; i < Level.gridX_size; i++) {

                switch (Level.coordinates[j][i]) {
                    case 0:
                        g.setColor(Color.WHITE);/**Wybór koloru kompnentu.*/
                        g.fillRect(i * rectWidth, j * rectHeight, rectWidth, rectHeight);/**Wypełnienie pola prostokąta.**/
                        break;/**0 - rysowanie bialego prostokązta - pola, po którym może chodzić**/
                    case 1:/**1 - czarny prostokąt -ściana**/
                        g.setColor(Color.BLACK);
                        g.fillRect(i * rectWidth, j * rectHeight, rectWidth, rectHeight);
                        break;
                }

            }
            player.paintComponent(g);

        }
    }

    /**Metoda wywołana gdy wciśniemy przycisk.**/
    /**getKeyCode - zwraca integer klucza związany ze zdarzeniem.**/
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP)
            player.up = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            player.down = true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.right = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            player.left = true;
    }
    @Override
    public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                player.right = false;
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                player.left = false;
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
                player.down = false;
            if(e.getKeyCode() == KeyEvent.VK_UP)
                player.right = false;
    }
    @Override
    public void keyTyped(KeyEvent e){
    }


}




