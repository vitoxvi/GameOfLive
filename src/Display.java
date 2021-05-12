import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Display {
    private int size;
    private boolean BLINKER = false;
    private Cell[][] World;
    private Random r = new Random();

    public Display(int size, boolean blinker){
        this.BLINKER = blinker;
        this.size = size;
        this.World = new Cell[size][size];

    }

    public void generateWorld(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                World[i][j] = new Cell();
            }
        }
    }

    public void setBlinker(){
        World[5][5].setOutput('*');
        World[4][5].setOutput('*');
        World[6][5].setOutput('*');
    }

    public void setRandom(){
        int times = (int)(size* size/4);
        for (int i = 0; i < times; i++) {
            World[r.nextInt(size)][r.nextInt(size)].setOutput('*');
        }
    }

    
    public void showWorld(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = World[i][j];
                System.out.print(c.getOutput());
            }
            System.out.println();
        }
    }

    public void simulateGame(int times) throws InterruptedException {
        generateWorld();
         if(BLINKER)setBlinker();
         else setRandom();
        for (int i = 0; i < times; i++) {
            checkNeighbours();
            setState();
            System.out.println("Generation" + i);
            showWorld();
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public void setState(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = World[i][j];

                if(c.getNeighboursAlive() == 3){
                    c.setOutput('*');
                }
                else if(c.getNeighboursAlive() ==2 && c.getOutput()=='*'){
                    c.setOutput('*');
                }
                else {
                    c.setOutput('-');
                }
            }
        }
    }

    public void checkNeighbours(){
        for (int i = 0; i < World.length; i++) {
            for (int j = 0; j < World.length; j++) {

                Cell c = World[i][j];
                countNeighbours(c,i,j);

            }

        }
    }
    private  void countNeighbours(Cell c, int x, int y) {
        c.resetNeighbours();
        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                if (j < 0 || j >= World[0].length || i < 0 || i >= World.length) { // skip world border
                    continue;
                }
                if (World[i][j].getOutput() == '*') {
                    c.incrementNeighbours();
                }
            }
        }

        if (c.getOutput() == '*') { // don't count the living cell itself
            c.decrementNeighbours();
        }
    }



}
