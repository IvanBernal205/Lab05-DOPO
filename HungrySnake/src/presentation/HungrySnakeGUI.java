package presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import java.io.File;


public class HungrySnakeGUI extends JFrame{
    ArrayList<JPanel> panels;


    public HungrySnakeGUI(){
        this.setTitle("HungrySnake");
        panels = new ArrayList<>();
        this.prepareView();
        this.prepareController();
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

        GridLayout scenary = new GridLayout(10, 10, 1, 1);
        center.setLayout(scenary);
        center.setBackground(Color.WHITE);

        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);

        refresh(100, center, null);
    }

    private void refresh(int n, JPanel center, Color color){
        if (color == null) color = new Color(0, 100, 0);
        for (int i = 0 ; i < n ; i++){
            JPanel cell = new JPanel();
            cell.setBackground(color);
            center.add(cell);
            panels.add(cell);
        }
    }

    public void prepareViewColor(){
        JMenuBar menuBar = this.getJMenuBar();

        JMenu menu = new JMenu("Color");
        menuBar.add(menu);

        JMenuItem menuColor = new JMenuItem("Cambiar Color Tablero");
        menu.add(menuColor);
    }

    public void prepareControllerColor(){
        JMenu menuBar = this.getJMenuBar().getMenu(1);
        JMenuItem color = menuBar.getItem(0);

        color.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Seleccione el color", null);
                if (newColor != null) changeColorScenary(newColor);
            }
        });
    }

    private void changeColorScenary(Color color){
        for (JPanel jp : panels){
            jp.setBackground(color);
        }
    }

    public void prepareController(){
        addWindowListener(new WindowAdapter(){
          @Override 
          public void windowClosing(WindowEvent e){
            confirm();
          }
        });
        prepareControllerMenu();
        prepareControllerColor();;
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