public class Tile{
    boolean Bomb;
    int Nearby_bombs;
    int[] Location;

    public Tile(int[] Location){
        this.Location = Location;
        this.Bomb = false;
    }

    public void SetBomb(boolean Bomb) {
        this.Bomb = Bomb;
    }

    public boolean getBomb(){
        return Bomb;
    }

    public void setNearby_bombs(int Nearby_bombs){
        this.Nearby_bombs = Nearby_bombs;
    }

    public int[] getLocation(){
        return  Location;
    }
}
