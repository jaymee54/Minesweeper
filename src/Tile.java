public class Tile {
    boolean Bomb;
    int Nearby_bombs;
    int[] Location;

    public Tile( int[] Location){
        this.Location = Location;
    };

    public void SetBomb(boolean Bomb) {
        this.Bomb = Bomb;
    };

    public int Work_out_nearby_bombs(){
        return Nearby_bombs;
    }

}
