package game;

/**
 * Created by Paweł on 2017-04-07.
 */
public class Block {
    int x_location, y_location;
    static int type;


    Block(int x_tmp, int y_tmp, int type_tmp) {
        x_location = x_tmp; y_location = y_tmp; type = type_tmp;
    }

    int get_x_location()	{return x_location;}/**Metoda zwracajaca wspolrzedna x**/
    int get_y_location()	{return y_location;}/**Metoda zwracajaca wspolrzedna y**/

    int get_type() {return type;}/**Metoda zwracajaca typ**/

    void action(){};
}