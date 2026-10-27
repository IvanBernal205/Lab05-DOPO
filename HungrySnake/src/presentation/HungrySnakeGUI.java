package presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HungrySnakeGUI extends JFrame{

    public HungrySnakeGUI(){
        this.setTitle("HungrySnake");
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

    public void prepareController(){
        addWindowListener(new WindowAdapter(){
          @Override 
          public void windowClosing(WindowEvent e){
            confirm();
          }  
        });
    }

    private void confirm(){
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?","Confimar salida",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0) System.exit(0);
    }

        public static void main(String[] args){
        HungrySnakeGUI hungrySnake = new HungrySnakeGUI();
        hungrySnake.setVisible(true);
    }

}