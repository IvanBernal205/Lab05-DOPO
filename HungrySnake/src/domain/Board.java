package domain;
import java.util.*;

public class Board {
    private char[][] board;
    private int rows;
    private int columns;
    private int fruits;
    private int obstacles;

    private Snake snake;

    //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite
    public Board(int rows, int columns, int fruits, int obstacles, char direction){
        checkCorrectSize(rows, columns);
        checkCorrectElements(fruits, obstacles);

        this.board = new char[this.rows + 2][this.columns + 2];
        for (int i = 0 ; i < this.rows + 2 ; i++){
            for (int j = 0; j<this.columns + 2; j++){
                if (i == 0 || j == 0 || i == this.rows + 1 || j == this.columns + 1) board[i][j] = '5';
                else board[i][j] = '0';
            }
        }
        this.snake = new Snake(this.rows, this.columns, direction); //Revisar la direccion
        placeHead(this.rows, this.columns);

        for (int i = 0 ; i < obstacles; i++){
            addObstacles();
        }

        for (int i = 0 ; i < fruits; i++){
            addFruit();
        }
    }


    public void addFruit(){
        Random rand = new Random();

        int rowRand; 
        int columnRand;
        do {
            rowRand = rand.nextInt(board.length - 2) + 1;
            columnRand = rand.nextInt(board[0].length - 2) + 1;
        } while (board[rowRand][columnRand] != '0');
        board[rowRand][columnRand] = '2';
    }

    private void addObstacles(){
        Random rand = new Random();

        int rowRand; 
        int columnRand;
        do {
            rowRand = rand.nextInt(board.length - 2) + 1;
            columnRand = rand.nextInt(board[0].length - 2) + 1;
        } while (board[rowRand][columnRand] != '0');
        board[rowRand][columnRand] = '3';
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

    public boolean[] checkColission(int row, int column){
        char element = checkElement(row, column);
        boolean correctToMove = false;
        boolean grow = false;
        //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite
        switch (element){
            case '0':
                correctToMove = true;
                grow = false;
                break;
            case '2':
                correctToMove = true;
                grow = true;
                break;
            case '1':
            case '3':
            case '4':
            case '5':
                correctToMove = false;
                grow = false;
                break;
        }
        return new boolean[] {correctToMove, grow};
    }

    public void updateSnake(int[] oldHead, int[] oldTail, boolean grow){
        if (!grow) board[oldTail[0]][oldTail[1]] = '0';
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

    private void placeHead(int row, int column){
        board[row/2][column/2] = '4';
    }
}