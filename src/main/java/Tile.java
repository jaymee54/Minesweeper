public class Tile {
    boolean Bomb;
    boolean Reveal;
    boolean Flag;
    int Nearby_bombs;
    int[] Location;

    public Tile(int[] Location) {
        this.Location = Location;
        this.Bomb = false;
        this.Reveal = false;
        this.Flag = false;
    }

    public void setBomb(boolean Bomb) {
        this.Bomb = Bomb;
    }

    public boolean getBomb() {
        return Bomb;
    }

    public void setNearby_bombs(int Nearby_bombs) {
        this.Nearby_bombs = Nearby_bombs;
    }

    public int getNearby_bombs(){
        return Nearby_bombs;
    }

    public int[] getLocation() {
        return Location;
    }

    public boolean getReveal() {
        return Reveal;
    }

    public void setReveal(boolean Hidden){
        this.Reveal = Hidden;
    }

    public void setFlag (boolean Flag){
        this.Flag = Flag;
    }

    public boolean getFlag(){
        return Flag;
    }
}