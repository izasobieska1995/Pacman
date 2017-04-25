package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends JPanel {

    int curr_level;
    /**
     * Aktulany poziom gracza
     **/
    double start_x, start_y, x_location, y_location, x_vel, y_vel;
    PlayerState current_state;
    static int run_change = 0;
    boolean wallU, wallD, wallR, wallL, no_wall;
    public boolean up, down, right, left;
    final static String imgPath = "data/level/jpg/pacman0.jpg";
    static String path1 = "data/level/jpg/right_open.jpg";
    static String path2 = "data/level/jpg/right_half.jpg";
    static String path3 = "data/level/jpg/right_open.jpg";
    //    final static String imgPath = "data/resources/icon.jpg";
    final static int ROWS = 3;
    final static int COLS = 4;
    final static int WIDTH = 32;
    final static int HEIGHT = 32;
    int x, y;
    BufferedImage img;
    BufferedImage subimg;
    BufferedImage[][] subImages;

    public Player() {
        //setBounds(x, y, 32, 32);
        subImages = new BufferedImage[ROWS][COLS];
        x = y = 0;
        try {
            img = ImageIO.read(new File(imgPath));
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    subImages[i][j] = img.getSubimage(j * 90, i * 90, 195, 195);
                }
            }
        } catch (IOException e) {
            System.out.println("Blad wczytywania pliku." + e.toString());
        }
    }

    //@Override
    public void paintComponent(Graphics g) {/**Rysowanie prostokąta zadanych wymiarach i kolorze.**/

        //int rectWidth = getWidth()/Level.gridX_size, rectHeight = getHeight()/Level.gridY_size;

        /*g.setColor(Color.YELLOW);
        g.fillRect(5*GamePanel.rectWidth,5*GamePanel.rectHeight, GamePanel.rectWidth, GamePanel.rectHeight);*/
        // super.paintComponent(g);
        //subimg = img.getSubimage(10, 10, 190, 175);

        g.drawImage(subImages[0][0], 32, 32, WIDTH, HEIGHT, null);
        /**subImage - metoda która wybiera framgent obrazka **/
    }

    public void moving(boolean up_n, boolean down_n, boolean right_n, boolean left_n, long timer) {
        up = up_n;
        down = down_n;
        right = right_n;
        left = left_n;
        switch (current_state) {
            case LEFT:
                if (x_vel > 0)
                    x_vel = 0;
                x_vel = x_vel - 0.001;
                if (x_vel < -0.085)
                    x_vel = -0.085;
                if(wallL)
                {current_state = PlayerState.WAIT;
                    y_vel = 0;}
                break;
            case RIGHT:
                if(wallR)
                {current_state = PlayerState.WAIT;
                y_vel=0;}
                if (x_vel < 0)
                    x_vel = 0;
                x_vel = x_vel + 0.001;
                if (x_vel > 0.085)
                    x_vel = 0.085;
                break;
            case UP:
                if(wallU)
                {current_state = PlayerState.WAIT;
                y_vel = 0;}
                break;
            case DOWN:
                if(wallD){
                    current_state = PlayerState.WAIT;
                    y_vel = 0;
                }
                break;
            case WAIT:
                x_vel =0;break;
            default:
                x_vel = 0;
                break;
        }
        if(wallU && x_vel < 0)
            x_vel = 0;
        if(wallR && x_vel < 0)
            x_vel = 0;
        if(wallD && x_vel < 0)
            x_vel = 0;
        if(wallU && x_vel < 0)
            x_vel = 0;
        if(wallR && x_vel > 0)
            x_location = x_location+x_vel;
    }


    PlayerGraphicsState get_current_graphic_state() {

        switch (current_state) {
            case LEFT:
                if (wallL)
                    return PlayerGraphicsState.LEFT_CLOSE;
                if (run_change < 125)
                    return PlayerGraphicsState.LEFT_HALF;
                else
                    return PlayerGraphicsState.LEFT_OPEN;
            case RIGHT:
                if (wallR)
                    return PlayerGraphicsState.RIGHT_CLOSE;
                if (run_change < 125)
                    return PlayerGraphicsState.RIGHT_CLOSE;
                else
                    return PlayerGraphicsState.RIGHT_OPEN;
            case DOWN:
                if (wallD)
                    return PlayerGraphicsState.DOWN_CLOSE;
                if (run_change < 125)
                    return PlayerGraphicsState.DOWN_HALF;
                else
                    return PlayerGraphicsState.DOWN_CLOSE;
            case UP:
                if (wallU)
                    return PlayerGraphicsState.UP_CLOSE;
                if (run_change < 125)
                    return PlayerGraphicsState.UP_HALF;
                else
                    return PlayerGraphicsState.UP_OPEN;
                default: return PlayerGraphicsState.UP_OPEN;

        }

    }

    void start(int x_tmp, int y_tmp){
        start_x = x_tmp;
        start_x = y_tmp;
        x_location = start_x;
        y_location = start_y;
        current_state = PlayerState.WAIT;
    }
    double getX_location(){
        return x_location;
    }
    double getY_location(){
        return y_location;
    }
    int getCurr_level(){
        return curr_level;
    }
    void set_current_state(PlayerState tmp_state){
        current_state = tmp_state;
    }

}
