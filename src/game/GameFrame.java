package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by Paweł on 2017-04-02.
 */
public class GameFrame extends JFrame {

    private JPanel activePanel;
    public static MenuPanel panel_menu;
    String iconPath = "data\\resources\\icon.jpg";/**Scieżka dostępu.*/

public GameFrame(){
    /**Wyjątek, jeśli nie jest spełniony to na ekarnie pojawi się napis.*/
    try{
        new Config();
    }catch(Exception io){
        System.out.println("Exception: " + io);
    }

    panel_menu = new MenuPanel(this);
    this.activePanel = panel_menu;

    this.setTitle("Pac-Man");/**Nadanie tytułu ramce*/
    this.setIconImage(new ImageIcon(iconPath).getImage());/**Ustawienie obrazu który ma być wyświletlony przez ścieżkę**/
    this.add(panel_menu);/**Dodanie panelu**/
    this.pack();/**Formaowanie ramki tak aby jej azawartość mieściła się w preferowanych rozmiarach.*/
    //this.addComponentListener(new WindowResize());


}

    /*private class WindowResize extends ComponentAdapter
    {
        public void componentResized(ComponentEvent e)
        {
            int height = e.getComponent().getHeight();
            int width = e.getComponent().getWidth();

            panel_menu.newSize(width, height);

            System.out.print("size: ");
            System.out.println(width + " " + height);
        }

    }*/

    public void changePanel(JPanel next){
        this.remove(activePanel);/**Usunęcie elementu z activePanel.**/
        this.add(next);
        //repaint();
        this.pack();
        activePanel = next;

    }

    /*public void refresh() {
        this.invalidate();
        this.validate();
        this.repaint();
    }*/

}
