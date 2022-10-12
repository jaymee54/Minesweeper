import java.util.Random;

public class Grid {
    int x_axis;
    int y_axis;

    double difficulty = 1;

    double Expected = x_axis*y_axis*difficulty/7;

    Tile[][] Tile_array;


    public Grid(int x_axis,int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.Tile_array = new Tile[x_axis][y_axis];
        for (int x=0;x <= x_axis-1;++x){
            for (int y=0; y <= y_axis-1; ++y){
                int[] Location = {x-1,y-1};
                Tile_array[x][y] = new Tile(Location);
            }
        }
    }

    public void distribute_bombs(Tile tile){
        Random rand = new Random();
        int Random_number = rand.nextInt(x_axis*y_axis);
        if(Random_number < Expected){
            tile.SetBomb(true);
        }
    }

    public void Work_out_nearby_bombs(Tile tile){
        int Count = 0;
        for (int x_coord_modifier=-1; x_coord_modifier<=1 ; ++x_coord_modifier) {
            for (int y_coord_modifier = -1; y_coord_modifier <= 1; ++y_coord_modifier){
                int[] Location = tile.getLocation();
                if(Tile_array[Location[0]+x_coord_modifier][Location[1]+y_coord_modifier].getBomb()){
                    ++Count;
                }
            }
        }
        tile.setNearby_bombs(Count);
    }
}
