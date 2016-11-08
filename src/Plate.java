import java.io.Serializable;

public class Plate implements Serializable {
    int plate1x;
    int plate1y;
    int plate2x;
    int plate2y;
    int ballx;
    int bally;

    Plate(int x, int y, boolean g){
        if(g) {
            this.plate1x = x;
            this.plate1y = y;
        } else {
            this.plate2x = x;
            this.plate2y = y;
        }
    }
    Plate(int plate1x, int plate1y, int ballx, int bally, int plate2x, int plate2y){
        this.plate1x = plate1x;
        this.plate1y = plate1y;
        this.ballx = ballx;
        this.bally = bally;
        this.plate2x = plate2x;
        this.plate2y = plate2x;
    }

    public int[] getPlate2Coord(){
        int[] coord = new int[2];
        coord[0] = plate2x;
        coord[1] = plate2y;
        return coord;
    }

    public void setPlate2Coord(int x, int y){
        plate2x = x;
        plate2y = y;
    }
}