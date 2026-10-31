package domain;

public class HungrySnake {

    private Board board;
    private Snake snake;
    
    //1
    public HungrySnake(int rows, int columns, int fruits, int obstacle){
        this.board = new Board(rows, columns, fruits, obstacle, 'N'); //Revisar la direccion
        this.snake = board.getSnake();
    }

    //2
    public boolean move (char direction){
        int [] finalPos = {snake.getHead()[0], snake.getHead()[1]};

        switch (direction){
            case 'N':
                finalPos[0] = finalPos[0] - 1;
                break;

            case 'S':
                finalPos[0] = finalPos[0] + 1;
                break;

            case 'E':
                finalPos[1] = finalPos[1] + 1;
                break;

            case 'W':
                finalPos[1] = finalPos[1] - 1;
                break;
        }
        //[0] correctToMove [1] grow
        boolean[] conditionsToMove = board.checkColission(finalPos[0], finalPos[1]);

        if (conditionsToMove[0]){
            int[] oldHead = snake.getHead();
            int[] oldTail = snake.getTail();
            snake.move(finalPos[0], finalPos[1], conditionsToMove[1]);
            board.updateSnake(oldHead, oldTail, conditionsToMove[1]);
            if (conditionsToMove[1]) board.addFruit();
            return true;
        }
        else return false;
    }

    //3
    public int size(){
        return snake.getSize();
    }

    //4
    public char[][] board(){
        return board.getBoard();
    }
}