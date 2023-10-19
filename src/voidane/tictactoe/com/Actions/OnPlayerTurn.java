package voidane.tictactoe.com.Actions;

import voidane.tictactoe.com.CheckIfWinner;
import voidane.tictactoe.com.Frame.TTTFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class OnPlayerTurn implements ActionListener {

    private int turn = 1;
    private String userTurn = "x";
    TTTFrame frame;

    public OnPlayerTurn(TTTFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (frame.isWinner())
            return;

        userTurn = frame.getUserTurn(turn);
        JButton button = frame.getButtonGridList(Integer.parseInt(e.getActionCommand()));

        if (!button.getText().isEmpty()) {
            return;
        }

        frame.setUserTurnDisplayText(userTurn);
        frame.setUserTurn(userTurn);

        if (Objects.equals(userTurn, "x"))
            this.userTurn = "o";
        else
            this.userTurn = "x";

        button.setText(userTurn);

        CheckIfWinner winner = new CheckIfWinner(frame, e);
        turn++;
    }

}