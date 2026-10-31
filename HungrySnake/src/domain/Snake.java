package domain;
import java.util.*;

public class Snake {
    private ArrayList<int[]> snake; //[0] es la posicion de la cabeza


    public Snake(int rows, int columns, char direction){
        //La serpiente se ubicara siempre en el centro al iniciar
        snake = new ArrayList<>();
        snake.add(new int[] {rows/2, columns/2});
    }

    public int getSize(){
        return snake.size();
    }
    
    public int[] getHead(){
        return snake.get(0).clone();
    }

    public int[] getTail(){
        return snake.get(snake.size() - 1).clone();
    }

    public ArrayList<int []> getBody(){
        ArrayList<int[]> body = new ArrayList<>(snake);
        if (!body.isEmpty()) body.remove(0);
        return body;
    }

    public void move(int row, int column, boolean hasEaten){
        snake.add(0, new int[] {row, column});
        if (!hasEaten) snake.remove(snake.size() - 1);
    }
}