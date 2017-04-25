package game;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł on 2017-04-06.
 */
public class Level {

    static int gridX_size, gridY_size;
    int tmp_X, tmp_Y;
    static int[][] coordinates;
    static int[] coord;
    List<Block> blocklist;


    public Level(String level_path) throws IOException {

        blocklist=new ArrayList<Block>();
        String line = null;
        File file = new File(level_path);
        FileReader fileReader = new FileReader(file);/**Odczytywanie z pliku za pomoca ścieżki**/
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        line = bufferedReader.readLine();
        gridY_size = Integer.parseInt(line);/**Parsowanie Strrng do Integer.**/
        System.out.println(gridY_size);
        line = bufferedReader.readLine();
        gridX_size = Integer.parseInt(line);
        System.out.println(gridX_size);
        coordinates = new int[gridY_size][gridX_size];

        //while ((line = bufferedReader.readLine()) != null){
        for (int j = 0; j < gridY_size; j++) {
                    line = bufferedReader.readLine();
                    String[] part = line.split(":");/**Odczytywanie z pliku znaków oddzielonych :**/
                    for (int i = 0; i < gridX_size; i++) {
                        int tmpVal = Integer.parseInt(part[i]);
                        System.out.println(tmpVal);
                        coordinates[j][i] = tmpVal;
                        /*switch (tmpVal) {
                            case 0:
                                blocklist.add(new Block(i, j, tmpVal));
                                break;
                            case 1:
                                blocklist.add(new Block(i, j, tmpVal));
                                break;
                        }*/

            }
        }
    }
}





            /*catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Blad plik wejsciowy");
                System.exit(0);}

            catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Popraw plik konfiguracyjny");
                System.exit(0);}

            catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Blad plik wejsciowy");
                System.exit(0);}

                //Wypisz();*/


       /*public void Wypisz(){
        for(int i = 0; i < gridY_size; i++) {
            for (int j = 0; j < gridX_size; j++) {
                System.out.println(coordinates[i][j]);
            }
        }
    }*/

