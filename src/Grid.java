import java.util.Random;

public class Grid {
    int x_axis;
    int y_axis;
    double difficulty = 1;
    int Bomb_count = 0;
    double Expected;

    Tile[][] Tile_array;


    public Grid(int x_axis,int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
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
                if(Tile_array[column][row].getHidden()) {
                    if(Tile_array[column][row].getBomb()){
                        Current_tile = "[ * ]";
                    }
                    else {
                        Work_out_nearby_bombs(Tile_array[column][row]);
                        Current_tile = "[ " + Integer.toString(Tile_array[column][row].getNearby_bombs()) + " ]";
                    }
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
        if(!Tile_array[x_coord][y_coord].getHidden()){
            Tile_array[x_coord][y_coord].setHidden(true);
            if(Tile_array[x_coord][y_coord].getBomb()){
                val = false;
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
                        if (Tile_array[Location[0]][Location[1]].getHidden()){
                            Tile_array[Location[0]][Location[1]].setHidden(false);
                            if(Tile_array[Location[0]][Location[1]].getNearby_bombs() == 0){
                                Cascade(Tile_array[Location[0]][Location[1]]);
                            }
                        }
                    }
                }
            }
        }
    }
}
