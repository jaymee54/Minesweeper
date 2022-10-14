import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    Grid testgrid = new Grid(10,10,1);
    @Test
    public void TestGrid(){
        Assertions.assertEquals(10,testgrid.x_axis,"grid x size not 10");
        Assertions.assertEquals(10,testgrid.y_axis,"grid y size not 10");
        Assertions.assertEquals(1,testgrid.difficulty,"grid difficulty not 1");
        int bombs = 0;
        for(int x = 0; x<testgrid.x_axis;x++){
            for(int y = 0; x<testgrid.y_axis;y++){
                if(testgrid.Tile_array[x][y].getBomb()){
                    bombs++;
                }
            }
        }
        Assertions.assertEquals(bombs, testgrid.Bomb_count, "Bomb_count not working correctly");
        Assertions.assertTrue(testgrid.Bomb_count<testgrid.Expected,"more bombs than expected");
    }

    public void Testis_in_bound(){
        Assertions.assertEquals(/*test*/);
    }
}
