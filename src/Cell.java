public class Cell {
    private Character output = '-';
    private int neighboursAlive = 0;


    public Character getOutput() {
        return output;
    }

    public void setOutput(Character output) {
        this.output = output;
    }

    public int getNeighboursAlive() {
        return neighboursAlive;
    }

    public void incrementNeighbours() {
        this.neighboursAlive += 1;
    }

    public void decrementNeighbours(){
        this.neighboursAlive -= 1;
    }

    public void resetNeighbours() {
        this.neighboursAlive = 0;
    }
}
