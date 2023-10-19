package voidane.tictactoe.com.Actions;

import voidane.tictactoe.com.Frame.TTTFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetGame implements ActionListener {

    TTTFrame frame;

    public ResetGame(TTTFrame frame) {
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        frame.resetGame();
    }

}
