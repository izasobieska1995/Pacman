package game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                GameFrame gameframe = new GameFrame();
                gameframe.setLocationRelativeTo(null);/**Wyswietlenie  okna w domyslenej lokazlizacji**/
                gameframe.setVisible(true);/**Metoda, która sprawia że ramka jest widoczna.**/
                //gameframe.refresh();

            }
        });
    }
}