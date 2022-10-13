import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("input the x axis grid size");
        String x_axis_length = myObj.nextLine();

        int x_axis = Integer.parseInt(x_axis_length);
        System.out.println("input the y axis grid size");
        String y_axis_length = myObj.nextLine();

        int y_axis = Integer.parseInt(y_axis_length);
        Grid MyGrid = new Grid(x_axis,y_axis);

        Boolean Play_game = true;
        Boolean Win_condition = false;

        do{
            MyGrid.Draw_grid();
            System.out.println("");

            int[] Input_location = MyGrid.Select_tile();

            if(MyGrid.Select_flag()){
                MyGrid.Create_flag_or_remove_flag(MyGrid.Tile_array[Input_location[0]][Input_location[1]]);
            }
            else{
                if(!MyGrid.Tile_array[Input_location[0]][Input_location[1]].getFlag()){
                    Play_game = MyGrid.Reveal_square(Input_location[0],Input_location[1]);
                    if(MyGrid.Tile_array[Input_location[0]][Input_location[1]].getNearby_bombs() == 0){
                        MyGrid.Cascade(MyGrid.Tile_array[Input_location[0]][Input_location[1]]);
                    }
                }

            }

            if(x_axis * y_axis - MyGrid.Revealed_squares == MyGrid.Bomb_count){
                Win_condition = true;
            }

        }while(Play_game);
        if(Win_condition){
            System.out.println(("Well done! You win!"));
        }
        else{
            System.out.println("You hit a bomb. You Lose!");
        }
    }
}