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
    public boolean move (char direction, boolean auto){
        int [] lastHeadPosition = snake.getHead();
        int [] newHeadPosition = snake.getHead();
        if (snake.getDirection() == direction && !auto) return true;
        snake.setDirection(direction);

        switch (direction){
            case 'N':
                newHeadPosition[0] = lastHeadPosition[0] - 1;
                break;

            case 'S':
                newHeadPosition[0] = lastHeadPosition[0] + 1;
                break;

            case 'E':
                newHeadPosition[1] = lastHeadPosition[1] + 1;
                break;

            case 'W':
                newHeadPosition[1] = lastHeadPosition[1] - 1;
                break;
        }
        // [0] moveAllowed
        // [1] hasEaten
        // [2] triedToHitNeck
        boolean[] conditionsToMove = board.checkColission(newHeadPosition[0], newHeadPosition[1]);

        if(conditionsToMove[2]) return true;
        else if (conditionsToMove[0]){
            int[] lastTailPosition = snake.getTail();
            snake.move(newHeadPosition[0], newHeadPosition[1], conditionsToMove[1]);
            board.updateSnake(lastHeadPosition, lastTailPosition, conditionsToMove[1]);
            if (conditionsToMove[1]) board.addFruit(1);
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

    public char getDirection(){
        return snake.getDirection();
    }
}