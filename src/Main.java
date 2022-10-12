import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Grid MyGrid = new Grid(10,10);
        Boolean Play_game = true;
        Scanner myObj = new Scanner(System.in);
        do{
            MyGrid.Draw_grid();
            System.out.println("");
            System.out.println("input the column");
            String x_coord = myObj.nextLine();
            int x = Integer.parseInt(x_coord);
            System.out.println("input the row");
            String y_coord = myObj.nextLine();
            int y = Integer.parseInt(y_coord);

            Play_game = MyGrid.Reveal_square(x-1,y-1);

        }while(Play_game);
    }

}