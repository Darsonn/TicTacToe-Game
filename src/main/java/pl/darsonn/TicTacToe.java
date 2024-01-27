package pl.darsonn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    public JPanel panel1;
    private JButton button5;
    private JButton button7;
    private JButton button8;
    private JButton button2;
    private JButton button3;
    private JButton button9;
    private JButton button6;
    private JButton button4;
    private JButton button1;
    private JLabel round;
    private JButton exitButton;
    private JButton restartButton;

    private char player;
    private char[][] fields;
    private JButton[] buttons;

    public TicTacToe() {
        player = 'X';
        fields = new char[3][3];
        buttons = new JButton[]{button5, button7, button8, button2, button3, button9, button6, button4, button1};

        for (JButton button : buttons) {
            button.addActionListener(createButtonListener());
        }
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitButton) {
                    System.exit(0);
                } else {
                    restartGame();
                }
            }
        };
        exitButton.addActionListener(listener);
        restartButton.addActionListener(listener);
    }

    private ActionListener createButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();

                if (clickedButton.isEnabled()) {
                    clickedButton.setText(String.valueOf(player));
                    clickedButton.setEnabled(false);

                    int row, col;

                    if (clickedButton == button1 || clickedButton == button2 || clickedButton == button3) {
                        row = 0;
                    } else if (clickedButton == button4 || clickedButton == button5 || clickedButton == button6) {
                        row = 1;
                    } else {
                        row = 2;
                    }

                    if (clickedButton == button1 || clickedButton == button4 || clickedButton == button7) {
                        col = 0;
                    } else if (clickedButton == button2 || clickedButton == button5 || clickedButton == button8) {
                        col = 1;
                    } else {
                        col = 2;
                    }

                    fields[row][col] = player;

                    if (checkWin(player)) {
                        if(JOptionPane.showOptionDialog(panel1, "Player " + player + " won! Play again?",
                                "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, null, 0) == 0) {
                            restartGame();
                        } else {
                            System.exit(0);
                        }
                    } else {
                        if(checkDraw()) {
                            if(JOptionPane.showOptionDialog(panel1, "Draw! Play again?",
                                    "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                    null, null, 0) == 0) {
                                restartGame();
                            } else {
                                System.exit(0);
                            }
                        }
                    }

                    player = player == 'X' ? 'O' : 'X';
                    round.setText(String.valueOf(player));
                }
            }
        };
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (fields[i][0] == player && fields[i][1] == player && fields[i][2] == player) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (fields[0][i] == player && fields[1][i] == player && fields[2][i] == player) {
                return true;
            }
        }

        if (fields[0][0] == player && fields[1][1] == player && fields[2][2] == player) {
            return true;
        }
        if (fields[0][2] == player && fields[1][1] == player && fields[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        int assigned = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(fields[i][j] != 0) {
                    assigned++;
                }
            }
        }
        return assigned == 9;
    }

    private void restartGame() {
        player = 'X';
        fields = new char[3][3];

        for(JButton button : buttons) {
            button.setEnabled(true);
            button.setText("");
        }
    }
}
