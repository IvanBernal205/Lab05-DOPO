package presentation;
import domain.HungrySnake;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;


public class HungrySnakeGUI extends JFrame{
    ArrayList<JPanel> panels;
    private HungrySnake game;
    //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite
    private Color grass;
    private Color snake;
    private Color fruits;
    private Color obstacles;
    private Color head;
    private final Color limit = Color.ORANGE;
    private boolean isGameOver = false;


    public HungrySnakeGUI(){
        this.setTitle("HungrySnake");
        panels = new ArrayList<>();

        game = new HungrySnake(10, 10, 1, 2);
        grass = new Color(0, 100, 0);  
        snake = Color.GREEN;    
        fruits = Color.RED;   
        obstacles = Color.BLACK;
        head = Color.BLUE;       
        this.prepareView();
        this.prepareController();
        this.updateBoardDesign();
        

    }

    public void prepareView(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width;
        int height = pantalla.height;
        //tamaño
        Dimension SIZE = new Dimension(width/2, height/2);
        this.setSize(SIZE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.prepareViewMenu();
        this.prepareViewBoard();
        this.prepareViewColor();
    }

    public void prepareViewMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menu1 = new JMenu("Archivo");
        menuBar.add(menu1);

        JMenuItem newOption = new JMenuItem("Nuevo");
        JMenuItem openOption = new JMenuItem("Abrir");
        JMenuItem saveOption = new JMenuItem("Salvar");
        JMenuItem exitOption = new JMenuItem("Salir");

        menu1.add(newOption);
        menu1.add(openOption);
        menu1.add(saveOption);
        menu1.add(exitOption);
    }

    public void prepareViewBoard(){

        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel center = new JPanel();

        JLabel fruitsCont = new JLabel("Frutas: 0");
        north.add(fruitsCont);

        char[][] board = game.board();
        int rows = board.length;
        int columns = board[0].length;

        GridLayout scenary = new GridLayout(rows, columns, 1, 1);
        center.setLayout(scenary);
        center.setBackground(Color.WHITE);

        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);

        createScenaryCells(rows * columns, center, null);
    }

    private void createScenaryCells(int n, JPanel center, Color color){
        if (color == null) color = new Color(0, 100, 0);
        for (int i = 0 ; i < n ; i++){
            JPanel cell = new JPanel();
            cell.setBackground(color);
            center.add(cell);
            panels.add(cell);
        }
    }

    private void refresh(){
        game = new HungrySnake(10, 10, 1, 2);
        isGameOver = false;
        updateBoardDesign();
    }

    public void prepareViewColor(){
        JMenuBar menuBar = this.getJMenuBar();
        //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite
        JMenu menu = new JMenu("Configuracion");
        menuBar.add(menu);

        JMenuItem menuColorGrass = new JMenuItem("Color Tablero");
        menu.add(menuColorGrass);

        JMenuItem menuColorSnake = new JMenuItem("Color de Snake");
        menu.add(menuColorSnake);

        JMenuItem menuColorFruits = new JMenuItem("Color de las Frutas");
        menu.add(menuColorFruits);

        JMenuItem menuColorObstacles = new JMenuItem("Color de los Obstaculos");
        menu.add(menuColorObstacles);

        JMenuItem menuColorHead = new JMenuItem("Color de la cabeza");
        menu.add(menuColorHead);

        JMenuItem refresh = new JMenuItem("Refrescar");
        menu.add(refresh);
    }

    public void prepareControllerColor(){
        JMenu menuBar = this.getJMenuBar().getMenu(1);

        JMenuItem grassColor = menuBar.getItem(0);
        JMenuItem snakeColor = menuBar.getItem(1);
        JMenuItem fruitsColor = menuBar.getItem(2);
        JMenuItem obstaclesColor = menuBar.getItem(3);
        JMenuItem headColor = menuBar.getItem(4);
        JMenuItem refresh = menuBar.getItem(5);

        grassColor.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Seleccione el color", null);
                if (newColor != null) {
                    grass = newColor;
                    updateBoardDesign();
                }
            }
        });

        snakeColor.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Seleccione el color", null);
                if (newColor != null) {
                    snake = newColor;
                    updateBoardDesign();
                }
            }
        });

        fruitsColor.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Seleccione el color", null);
                if (newColor != null) {
                    fruits = newColor;
                    updateBoardDesign();
                }
            }
        });

        obstaclesColor.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Seleccione el color", null);
                if (newColor != null) {
                    obstacles = newColor;
                    updateBoardDesign();
                }
            }
        });

        headColor.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Seleccione el color", null);
                if (newColor != null) {
                    head = newColor;
                    updateBoardDesign();
                }
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                refresh();
                }
            });
    }


    public void prepareController(){
        addWindowListener(new WindowAdapter(){
          @Override 
          public void windowClosing(WindowEvent e){
            confirm();
          }
        });
        prepareControllerMenu();
        prepareControllerColor();
        prepareControllerGame();
    }

    public void prepareControllerMenu(){
        JMenu menuBar = this.getJMenuBar().getMenu(0);

        JMenuItem openOption = menuBar.getItem(1);
        JMenuItem saveOption = menuBar.getItem(2);
        JMenuItem exitOption = menuBar.getItem(3);

        //Salir
        exitOption.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                confirm();
            }
        });

        //Salvar
        saveOption.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e){
                JFileChooser selector = new JFileChooser();
                
                int result = selector.showSaveDialog(null);

                //0 indica que selecciono guardar
                if (result == 0){
                    File fileToSave = selector.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "La funcionalidad de salvar se encuentra en construccion\n" +
                        "Nombre del archivo: " + fileToSave.getName()
                     );
                }
            }
        });

        //Abrir
        openOption.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                JFileChooser selector = new JFileChooser();

                int result = selector.showOpenDialog(null);

                //0 indica que selecciono abrir
                if (result == 0){
                    File selectedFile = selector.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "La funcionalidad de abrir se encuentra en construccion\n" + 
                        "Nombre del archivo: " + selectedFile.getName()
                    );
                }
            }            
        });
    }

    public void prepareControllerGame(){
            addKeyListener(new KeyAdapter() {
                @Override 
                public void keyPressed(KeyEvent e){
                    if (isGameOver){
                        JOptionPane.showMessageDialog(null, "Ya perdiste, no te puedes mover");
                        return;
                    }
                    char direction = ' ';
                    int key = e.getKeyCode();

                    switch (key) {
                        case 87:
                            direction = 'N';
                            break;
                        case 65:
                            direction = 'W';
                            break;
                        case 83:
                            direction = 'S';
                            break;
                        case 68:
                            direction = 'E';
                            break;
                    }
                    if (direction != ' '){
                        isGameOver = !game.move(direction);
                        if (!isGameOver) updateBoardDesign();
                        else JOptionPane.showMessageDialog(null, "Perdiste");
                    }
                }
        });
    }

    public void updateBoardDesign(){
        char[][] board = game.board();
        int posCell = 0;
        
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                JPanel cell = panels.get(posCell);
                char element = board[i][j];
                //0 Pasto  1 Serpiente  2 Frutas  3 Obstaculos  4 Cabeza  5 Limite
                switch (element){
                    case '0':
                        cell.setBackground(grass); 
                        break;
                    case '1':
                        cell.setBackground(snake);
                        break;
                    case '2':
                        cell.setBackground(fruits);
                        break;
                    case '3':
                        cell.setBackground(obstacles);
                        break;
                    case '4':
                        cell.setBackground(head);
                        break;
                    case '5':
                        cell.setBackground(limit);
                        break;
                }
                posCell++;
            }
        }
    }

    private void confirm(){
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?","Confimar salida",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        //0 indica que pulso yes
        if (respuesta == 0) System.exit(0); 
    }
    public static void main(String[] args){
        HungrySnakeGUI hungrySnake = new HungrySnakeGUI();
        hungrySnake.setVisible(true);
    }
}