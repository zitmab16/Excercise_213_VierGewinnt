package GUI;

import BuiLo.BL;
import BuiLo.Value;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUI extends JFrame {

    private BL bl = new BL();
    private JLabel[][] field = new JLabel[7][7];

    public GUI() throws HeadlessException {
        super("4-Gewinnt");
        initComponents();
        this.setVisible(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void initComponents() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(7, 7, 3, 3));

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0) {
                    JButton button = new JButton();
                    button.setOpaque(true);
                    button.setBackground(Color.gray);
                    button.setForeground(Color.white);
                    button.setHorizontalAlignment(JLabel.CENTER);
                    button.setText("V");
                    button.setName(i + "" + j);

                    button.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent event) {
                            onButtonClick(event);
                        }

                    });

                    container.add(button);
                } else {
                    JLabel label = new JLabel();
                    label.setOpaque(true);
                    label.setBackground(Color.black);
                    label.setForeground(Color.white);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setName(i + "" + j);
                    field[i][j] = label;
                    container.add(label);
                }

            }
        }
    }

    private void onButtonClick(java.awt.event.MouseEvent event) {
        try {
            JButton button = (JButton) event.getSource();
            int index = Integer.parseInt(button.getName());
            int column = index % 10;
            int row = bl.makeMove(column);

            JLabel label = field[row][column];

            BuiLo.Value val = bl.getValueAt(row, column);

            switch (val) {
                case X:
                    label.setBackground(Color.red);
                    break;
                case O:
                    label.setBackground(Color.blue);
                    break;
            }
            
            BuiLo.Value winner = bl.testWinner();
            
            if (winner != Value.EMPTY && winner != Value.DRAW) {
                JOptionPane.showMessageDialog(this, "Winner = " + winner);
                bl.reset();
                
                for (Component component : this.getContentPane().getComponents()) {
                    if(component instanceof JLabel){
                    JLabel lb = (JLabel) component;
                    lb.setBackground(Color.black); 
                    }
                    
                }
                
            }
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

}
