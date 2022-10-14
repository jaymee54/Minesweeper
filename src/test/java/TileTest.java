import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TileTest {
    Tile testtile = new Tile(new int[]{1, 1});
    @Test
    public void Test_Tile_setup(){
        for(int x=0; x<2; ++x){
            Assertions.assertEquals(1,testtile.getLocation()[x],"The location is incorrect");
        }
        Assertions.assertEquals(false, testtile.getBomb(), "Bomb initialisation is incorrect");
        Assertions.assertEquals(false, testtile.getReveal(), "Reveal initialisation is incorrect");
        Assertions.assertEquals(false, testtile.getFlag(), "Flag initialisation is incorrect");
        Assertions.assertEquals(0, testtile.getNearby_bombs(), "Nearby_bombs initialisation is incorrect");
    }

    @Test
    public void Test_Tile_getters_and_setters(){
        testtile.setBomb(true);
        Assertions.assertEquals(true, testtile.getBomb(), "bomb setter is incorrect");
        testtile.setReveal(true);
        Assertions.assertEquals(true, testtile.getReveal(), "Reveal setter is incorrect");
        testtile.setFlag(true);
        Assertions.assertEquals(true, testtile.getFlag(), "Flag setter is incorrect");
        testtile.setNearby_bombs(1);
        Assertions.assertEquals(1, testtile.getNearby_bombs(), "Nearby_bombs setter is incorrect");
    }
}
