import java.util.Random;

public class Grid {
    int x_axis;
    int y_axis;

    double difficulty = 1;

    double Expected = x_axis*y_axis*difficulty;

    Tile[][] Tile_array;


    public Grid(int x_axis,int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.Tile_array = new Tile[x_axis][y_axis];
        for (int x=0;x <= x_axis-1;++x){
            for (int y=0; y <= y_axis-1; ++y){
                int[] Location = {x,y};
                Tile_array[Location[0]][Location[1]] = new Tile(Location);
                Distribute_bombs(Tile_array[Location[0]][Location[1]]);
                }
            }
        }


    public void Distribute_bombs(Tile tile){
        Random rand = new Random();
        int Random_number = rand.nextInt(x_axis*y_axis);
        int Bomb_count = 0;
        if(Random_number < Expected && Bomb_count < Expected-1){
            tile.setBomb(true);
            ++Bomb_count;
        }
    }

    public void Draw_grid(){
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
}
