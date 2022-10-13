import java.util.Random;
import java.util.Scanner;

public class Grid {
    int x_axis;
    int y_axis;
    double difficulty = 1;
    int Bomb_count = 0;

    int Revealed_squares = 0;
    double Expected;
    Tile[][] Tile_array;
    Scanner myObj = new Scanner(System.in);


    public Grid(int x_axis,int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        Select_difficulty();
        Expected = x_axis*y_axis*difficulty/7;
        this.Tile_array = new Tile[x_axis][y_axis];
        for (int x=0;x <= x_axis-1;++x){
            for (int y=0; y <= y_axis-1; ++y){
                int[] Location = {x,y};
                Tile_array[Location[0]][Location[1]] = new Tile(Location);
                if(Bomb_count < Expected) {
                    Distribute_bombs(Tile_array[Location[0]][Location[1]]);
                }
                if(Tile_array[Location[0]][Location[1]].getBomb()){
                    Bomb_count += 1;
                }
            }

        }
    }

    public void Distribute_bombs(Tile tile){
        Random rand = new Random();
        int Random_number = rand.nextInt(x_axis*y_axis);
        if(Random_number < Expected){
            tile.setBomb(true);
        }
    }

    public void Draw_grid(){
        System.out.println("The number of Bombs = "+Bomb_count);
        for(int column = 0; column < x_axis; ++column){
            System.out.println("");
            for(int row = 0; row < y_axis; ++row){
                String Current_tile;
                if(Tile_array[column][row].getReveal()) {
                    if(Tile_array[column][row].getBomb()){
                        Current_tile = "[ * ]";
                    }
                    else {
                        Work_out_nearby_bombs(Tile_array[column][row]);
                        Current_tile = "[ " + Integer.toString(Tile_array[column][row].getNearby_bombs()) + " ]";
                    }
                }
                else if(Tile_array[column][row].getFlag()){
                    Current_tile = "[ F ]";
                }
                else{
                    Current_tile = "[ ~ ]";
                }
                System.out.print(Current_tile);
            }
        }
    }

    public Boolean is_in_bound(int[] Location){
        if(Location[0]  < 0 ){
            return false;
        }
        else if(Location[0]  > x_axis-1){
            return false;
        }
        else if (Location[1]  < 0){
            return false;
        }
        else if (Location[1] > y_axis-1){
            return false;
        }
        else{
            return true;
        }
    }

    public void Work_out_nearby_bombs(Tile tile){
        int Count = 0;
        int[] Location_original = tile.getLocation();
        for (int x_coord_modifier=-1; x_coord_modifier<=1 ; ++x_coord_modifier) {
            int[] Location = new int[Location_original.length];

            for(int x = 0; x < Location_original.length; ++x){
                Location[x] = Location_original[x];
            }

            Location[0] += x_coord_modifier;
            Location[1] -= 2;
            for (int y_coord_modifier = -1; y_coord_modifier <= 1; ++y_coord_modifier){
                Location[1] +=1;
                if (is_in_bound(Location)){
                    if(Tile_array[Location[0]][Location[1]].getBomb()){
                        ++Count;
                    }
                }

            }
        }
        tile.setNearby_bombs(Count);
    }

    public Boolean Reveal_square (int x_coord, int y_coord){
        boolean val = true;
        if (!Tile_array[x_coord][y_coord].getFlag()){
            if(!Tile_array[x_coord][y_coord].getReveal()){
                Work_out_nearby_bombs(Tile_array[x_coord][y_coord]);
                Tile_array[x_coord][y_coord].setReveal(true);
                Revealed_squares += 1;
                if(Tile_array[x_coord][y_coord].getBomb()){
                    val = false;
                }
            }
        }
        return val;
    }

    public void Cascade(Tile tile){
        if (tile.getNearby_bombs() == 0){
            int[] Location_original = tile.getLocation();
            for (int x_coord_modifier=-1; x_coord_modifier<=1 ; ++x_coord_modifier) {
                int[] Location = new int[Location_original.length];

                for(int x = 0; x < Location_original.length; ++x){
                    Location[x] = Location_original[x];
                }

                Location[0] += x_coord_modifier;
                Location[1] -= 2;
                for (int y_coord_modifier = -1; y_coord_modifier <= 1; ++y_coord_modifier){
                    Location[1] +=1;
                    if (is_in_bound(Location)){
                        if (!Tile_array[Location[0]][Location[1]].getReveal()){
                            Reveal_square(Location[0],Location[1]);
                            if(Tile_array[Location[0]][Location[1]].getNearby_bombs() == 0){
                                Cascade(Tile_array[Location[0]][Location[1]]);
                            }
                        }
                    }
                }
            }
        }
    }

    public int[] Select_tile(){
        System.out.println("input the column");
        String x_coord = myObj.nextLine();
        int x = Integer.parseInt(x_coord);

        System.out.println("input the row");
        String y_coord = myObj.nextLine();
        int y = Integer.parseInt(y_coord);

        int[] Input_location = {y-1,x-1};
        return Input_location;
    }

    public boolean Select_flag(){
        System.out.println("Would you like you place a flag or dig? F or D");
        String flag_input = myObj.nextLine();
        if(flag_input.toLowerCase().equals("f")){
            return true;
        }
        else if(flag_input.toLowerCase().equals("d")){
            return false;
        }
        else{
            System.out.println("invalid input try again");
            return Select_flag(); // best practice don't use recursion for this
        }
    }

    public void Create_flag(Tile tile){
        tile.setFlag(true);
    }

    public void Select_difficulty(){
        System.out.println("input the difficulty: 1,2,3,4,5");
        String diff = myObj.nextLine();
        difficulty = Integer.parseInt(diff);

    }
}
