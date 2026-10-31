package test;
import domain.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class HungrySnakeTest {
    @Test 
    public void shouldCreateTheHeadOfTheSnakeOfSizeOne(){
        Board board = new Board(10, 10, 1, 1, 'N');
        Snake snake = board.getSnake();

        assertEquals(1, snake.getSize());
        assertEquals('4', board.getBoard()[5][5]);
    }

    @Test 
    public void shouldCreateABoardWithGivenSizeCountingTheBorders(){
        HungrySnake game = new HungrySnake(10, 10, 1, 1);
        char[][] board =  game.board();

        int rowsLength = 12;
        int columnsLength = 12;
        assertEquals(rowsLength, board.length);
        assertEquals(columnsLength, board[0].length);
    }

    @Test 
    public void shouldBeAbleToMoveToAFruitAndGrown(){
        HungrySnake game = new HungrySnake(10, 10, 1, 1);
        char[][] board = game.board();

        board[5][6] = '2'; //Fruta

        game.move('E');

        assertEquals(2, game.size());
    }

    @Test
    public void shouldNotGrowIfMovesToGrass(){
        HungrySnake game = new HungrySnake(10, 10, 1, 1);
        char[][] board = game.board();

        board[5][6] = '0';
        game.move('E');

        assertEquals(1, game.size());
    }

    @Test 
    public void shouldGrowWhenEatingFruits(){
        HungrySnake game = new HungrySnake(10, 10, 1, 1);
        char[][] board = game.board();

        board[5][6] = '2'; //Fruta
        game.move('E');
        board[5][7] = '2'; //Fruta
        game.move('E');
        board[5][8] = '2'; //Fruta
        game.move('E');
        board[5][9] = '2'; //Fruta
        game.move('E');
        board[5][10] = '2'; //Fruta
        game.move('E');

        assertEquals(6, game.size());
    }

    @Test 
    public void shouldKnowThePositionOfTheHead(){
        Snake snake = new Snake(10,10,'N');

        snake.move(5,6,false);
        assertArrayEquals(new int[] {5,6}, snake.getHead());

        snake.move(5,7,true);
        assertArrayEquals(new int[] {5,7}, snake.getHead());

        snake.move(6,7,true);
        assertArrayEquals(new int[] {6,7}, snake.getHead());
    }

    @Test
    public void shouldNotBePossibleToMoveIntoBodyObstaclesAndLimit(){
        Board board = new Board(10, 10, 1, 1, 'N');

        char[][] scenary = board.getBoard();

        scenary[5][6] = '1';
        scenary[5][4] = '3';
        scenary[4][5] = '5';

        //A pasto si se mueve
        scenary[6][5] = '0';

        assertEquals(false, board.checkColission(5,6)[0]);
        assertEquals(false, board.checkColission(5,4)[0]);
        assertEquals(false, board.checkColission(4,5)[0]);
        assertEquals(true, board.checkColission(6,5)[0]);
    }

    @Test 
    public void shouldSetFiveAsMinimumSize(){
        HungrySnake game = new HungrySnake(1, 1, 1, 1);
        char[][] board =  game.board();
        
        int rowsLength = 7;
        int columnsLength = 7;

        assertEquals(rowsLength, board.length);
        assertEquals(columnsLength, board[0].length);
    }

    @Test
    public void shouldNotMoveIntoItsOwnBody(){
        HungrySnake game = new HungrySnake(10, 10, 0, 0);
        char[][] board = game.board();

        board[5][6] = '2'; //Fruta
        game.move('E');
        board[5][7] = '2'; //Fruta
        game.move('E');
        board[5][8] = '2'; //Fruta
        game.move('E');
        board[5][9] = '2'; //Fruta
        game.move('E');

        assertEquals(5, game.size());

        board[6][9] = '0';    
        board[6][8] = '0';

        assertTrue(game.move('S'));
        assertTrue(game.move('W'));
        assertFalse(game.move('N')); //Chocaria con el cuerpo de la snake
        assertEquals(5, game.size());
    }

    @Test 
    public void shouldNotMoveThroughTheLimits(){
        HungrySnake game = new HungrySnake(10, 10, 0, 0);
        assertTrue(game.move('E'));
        assertTrue(game.move('E'));
        assertTrue(game.move('E'));
        assertTrue(game.move('E'));
        assertTrue(game.move('E'));
        assertFalse(game.move('E'));
    }

    @Test 
    public void shouldNotMoveThroughObstacles(){
        HungrySnake game = new HungrySnake(10, 10, 0, 0);
        char[][] board = game.board();

        board[5][6] = '3'; //Obstaculo
        assertFalse(game.move('E'));
    }

    @Test
    public void shouldAppearANewFruitAfterEating(){
        HungrySnake game = new HungrySnake(10, 10, 0, 0);
        game.board()[5][6] = '2';
        game.move('E');

        char[][] board = game.board();

        int fruitsCont = 0;

        for (char[] rows : board)
            for (char content : rows)
                if (content == '2') fruitsCont++;
        
        assertEquals(1, fruitsCont); 
    }
}