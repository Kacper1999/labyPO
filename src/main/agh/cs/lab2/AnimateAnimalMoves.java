package agh.cs.lab2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class AnimateAnimalMoves extends JFrame {
    private JTextArea textArea;
    private AbstractWorldMap map;
    private MoveDirection[] directions;
    private int move = 0;
    private JButton button = new JButton("Next");

    AnimateAnimalMoves(AbstractWorldMap map, MoveDirection[] directions) {
        this.map = map;
        this.directions = directions;
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Jungle");

        JPanel panel = new JPanel();

        textArea = new JTextArea(15, 20);
        textArea.setText(map.toString());

        ButtonListener buttonListener = new ButtonListener();
        button.addActionListener(buttonListener);

        panel.add(button);
        panel.add(textArea);

        this.add(panel);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == button && directions.length > move) {
                map.executeOrder(directions[move], move);
                move++;
                textArea.setText("");
                textArea.append(map.toString());
            }
        }
    }

    private class TextFieldListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {
            String[] order = {String.valueOf(keyEvent.getKeyChar())};
            OptionsParser op = new OptionsParser();
            map.run(op.parse(order));
            textArea.setText("");
            textArea.append(map.toString());
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
}
