package domain;
import java.util.*;

public class Board {
    private char[][] board;
    private int rows;
    private int columns;
    private int fruits;
    private int obstacles;

    private Snake snake;
    private static final Random random = new Random();

    //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite
    public Board(int rows, int columns, int fruits, int obstacles, char direction){
        checkCorrectSize(rows, columns);
        checkCorrectElements(fruits, obstacles);

        board = new char[this.rows + 2][this.columns + 2];
        createEmptyBoard();
        
        createSnake(this.rows, this.columns, direction);

        addObstacles();
        addFruit(this.fruits);
    }


    public void addFruit(int numOfFruits){
        int rowRand; 
        int columnRand;
        for (int i = 0 ; i < numOfFruits; i++){
            do {
                rowRand = randomNum(1, rows);
                columnRand = randomNum(1, columns);
            } while (board[rowRand][columnRand] != '0');
            
            board[rowRand][columnRand] = '2'; // número de las frutas
        }
    }

    private void addObstacles(){
        int rowRand; 
        int columnRand;
        for (int i = 0 ; i < this.obstacles; i++){
            do {
                rowRand = randomNum(1, rows);
                columnRand = randomNum(1, columns);
            } while (board[rowRand][columnRand] != '0');
            
            board[rowRand][columnRand] = '3'; // número de las obstaculos
        }
    }

    private void checkCorrectSize(int rows, int columns){
        //Minimo tamano sera 5
        if (rows < 5 || columns < 5){
            this.rows = 5;
            this.columns = 5;
        }
        else{
            this.rows = rows;
            this.columns = columns;
        }
    }

    //TODO: Revisar logica
    private void checkCorrectElements(int fruits, int obstacles){
        int totalSize = rows * columns - 2;
        if (fruits + obstacles >= totalSize){
            this.fruits = totalSize / 2;
            this.obstacles = totalSize / 2;
        }
        else{
            this.fruits = fruits;
            this.obstacles = obstacles;
        }
    }

    private void createEmptyBoard(){
        for (int i = 0 ; i < this.rows + 2 ; i++){
            for (int j = 0; j<this.columns + 2; j++){
                if (i == 0 || j == 0 || i == this.rows + 1 || j == this.columns + 1) board[i][j] = '5';
                else board[i][j] = grassAt(i, j);
            }
        }
    }

    private char grassAt(int row, int column){
        return (row + column) % 2 == 0 ? '6' : '0';
    }

    public boolean[] checkColission(int row, int column){
        char element = checkElement(row, column);
        boolean moveAllowed = false;
        boolean hasEaten = false;
        boolean triedToHitNeck = false;
        //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite 6 pasto
        switch (element){
            case '0':
                moveAllowed = true;
                break;
            case '2':
                moveAllowed = true;
                hasEaten = true;
                break;
            case '1':
                triedToHitNeck = isNeckCollision(row, column);
                break;
            case '6':
                moveAllowed = true;
                break;
        }
        return new boolean[] {moveAllowed, hasEaten, triedToHitNeck};
    }

    public void updateSnake(int[] oldHead, int[] oldTail, boolean grow){
        if (!grow) board[oldTail[0]][oldTail[1]] = grassAt(oldTail[0], oldTail[1]);
        if (snake.getSize() > 1) board[oldHead[0]][oldHead[1]] = '1';
        int[] head = snake.getHead();
        board[head[0]][head[1]] = '4';
    }

    private  char checkElement(int row, int column){
        return board[row][column];
    }

    public Snake getSnake(){
        return snake;
    }

    public char[][] getBoard(){
        return board;
    }

    private boolean isNeckCollision(int newRowPosition, int newColumnPosition){
        int neckPositionRow = snake.getBody().get(0)[0];
        int neckPositionCol = snake.getBody().get(0)[1];
        if(newRowPosition == neckPositionRow && newColumnPosition == neckPositionCol) return true;
        return false;
    }

    private void createSnake(int row, int column, char direction){
        this.snake = new Snake(this.rows, this.columns, direction); //Revisar la direccion
        // El número 4 corresponde a la cabeza
        board[row/2][column/2] = '4';
    }

    public static int randomNum(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }   
}