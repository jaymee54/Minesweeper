public class Grid {
    int x_axis;
    int y_axis;

    public void Grid(int x_axis,int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public void CreateGrid(){
        for (int x=1;x <= x_axis;++x){
            for (int y=1; y <= y_axis; ++y){
                Tile([x,y]);
            }
        }
    }
}
