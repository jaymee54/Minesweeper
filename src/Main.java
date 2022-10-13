import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Grid MyGrid = new Grid(10,10);
        Boolean Play_game = true;
        Scanner myObj = new Scanner(System.in);
        do{
            MyGrid.Draw_grid();
            System.out.println("");
            System.out.println("input the column");
            String x_coord = myObj.nextLine();
            int x = Integer.parseInt(x_coord);
            System.out.println("input the row");
            String y_coord = myObj.nextLine();
            int y = Integer.parseInt(y_coord);
            int[] Input_location = {x-1,y-1};

            Play_game = MyGrid.Reveal_square(Input_location[0],Input_location[1]);

            /*if(MyGrid.Tile_array[Input_location[0]][Input_location[1]].getNearby_bombs() == 0){
                MyGrid.Cascade(MyGrid.Tile_array[Input_location[0]][Input_location[1]]);
            }*/

        }while(Play_game);
    }

}