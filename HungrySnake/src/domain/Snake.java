package domain;
import java.util.*;

public class Snake {
    private ArrayList<int[]> snake; //[0] es la posicion de la cabeza
    private char direction;


    public Snake(int rows, int columns, char direction){
        //La serpiente se ubicara siempre en el centro al iniciar
        snake = new ArrayList<>();
        snake.add(new int[] {rows/2, columns/2});
        this.direction = direction;
    }

    public int getSize(){
        return snake.size();
    }
    
    public int[] getHead(){
        return snake.get(0);
    }

    public ArrayList<int []> getBody(){
        ArrayList<int[]> body = new ArrayList<>(snake);
        if (!body.isEmpty()) body.remove(0);
        return body;
    }

    public void move(int row, int column, boolean grow){
        snake.add(0, new int[] {row, column});
        if (!grow) snake.remove(snake.size() - 1);
    }

    public void setDirection(char direction){
        this.direction = direction;
    }

}