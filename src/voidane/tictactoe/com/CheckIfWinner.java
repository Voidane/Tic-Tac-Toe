package voidane.tictactoe.com;

import voidane.tictactoe.com.Actions.OnPlayerTurn;
import voidane.tictactoe.com.Frame.TTTFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class CheckIfWinner {

    TTTFrame frame;
    OnPlayerTurn OPT;

    public CheckIfWinner(TTTFrame frame, ActionEvent e) {
        this.frame = frame;

        String lastPlayed = frame.getUserTurnNow();
        int spacePlayedInto = Integer.parseInt(e.getActionCommand());
        // Need to get the last player that played not current

        if (lastPlayed.equalsIgnoreCase("x"))
            lastPlayed = "o";
        else
            lastPlayed = "x";

        if (checkRows(spacePlayedInto) || checkColumns(spacePlayedInto) ||
                checkDiagonals(spacePlayedInto)) {
            frame.setWinner();
            frame.setWinnerDisplayText(lastPlayed);
        }
    }

    /**
     * Gets all rows and checks columns to see if anyone has won from there.
     * @param playedTurn the space the player played into
     * @return true if it's a win and false if not
     */
    private boolean checkRows(int playedTurn) {

        JButton[] gridList = frame.getAllButtonGridList();
        String[] slot = new String[3];

        // First Column we check if any of the rows match | o--> |
        if (playedTurn == 0 || playedTurn == 3 || playedTurn == 6) {
            for ( int i = 0 ; i < 3 ; i++ ) {
                slot[i] = gridList[playedTurn+i].getText();
            }
        }
        // Second Column we check if any of the rows match | <-o-> |
        else if (playedTurn == 1 || playedTurn == 4 || playedTurn == 7) {
            slot[0] = gridList[playedTurn].getText();
            slot[1] = gridList[playedTurn-1].getText();
            slot[2] = gridList[playedTurn+1].getText();
        }
        // Third Column we check if any of the rows match | <--o |
        else if (playedTurn == 2 || playedTurn == 5 || playedTurn == 8) {
            for ( int i = 0 ; i < 3 ; i++ ) {
                slot[i] = gridList[playedTurn-i].getText();
            }
        }

        return CheckIfNull(slot);
    }

    /**
     * Gets all columns and checks rows to see if anyone has won from there.
     * @param playedTurn the space the player played into
     * @return true if it's a win and false if not
     */
    private boolean checkColumns(int playedTurn) {
        JButton[] gridList = frame.getAllButtonGridList();
        String[] slot = new String[3];

        // First row we check if any of the columns match | o DOWN |
        if (playedTurn == 0 || playedTurn == 1 || playedTurn == 2) {
            slot[0] = gridList[playedTurn].getText();
            slot[1] = gridList[playedTurn+3].getText();
            slot[2] = gridList[playedTurn+6].getText();
        }
        // Second row we check if any of the columns match | DOWN |
        else if (playedTurn == 3 || playedTurn == 4 || playedTurn == 5) {
            slot[0] = gridList[playedTurn-3].getText();
            slot[1] = gridList[playedTurn].getText();
            slot[2] = gridList[playedTurn+3].getText();
        }
        // Third row we check if any of the columns match | DOWN o |
        else if (playedTurn == 6 || playedTurn == 7 || playedTurn == 8) {
            slot[0] = gridList[playedTurn-6].getText();
            slot[1] = gridList[playedTurn-3].getText();
            slot[2] = gridList[playedTurn].getText();
        }

        return CheckIfNull(slot);
    }

    /**
     * Gets all diagonals to see if any crosses have won.
     * @param playedTurn the space the player played into
     * @return true if it's a win and false if not
     */
    private boolean checkDiagonals(int playedTurn) {
        JButton[] gridList = frame.getAllButtonGridList();
        String[] slot = new String[3];

        // Each if check all the corners of the game
        if (playedTurn == 0) {
            slot[0] = gridList[playedTurn].getText();
            slot[1] = gridList[playedTurn+4].getText();
            slot[2] = gridList[playedTurn+8].getText();
        }
        else if (playedTurn == 2) {
            slot[0] = gridList[playedTurn].getText();
            slot[1] = gridList[playedTurn+2].getText();
            slot[2] = gridList[playedTurn+4].getText();
        }
        else if (playedTurn == 6) {
            slot[0] = gridList[playedTurn].getText();
            slot[1] = gridList[playedTurn-2].getText();
            slot[2] = gridList[playedTurn-4].getText();
        }
        else if (playedTurn == 8) {
            slot[0] = gridList[playedTurn].getText();
            slot[1] = gridList[playedTurn-4].getText();
            slot[2] = gridList[playedTurn-8].getText();
        }

        return CheckIfNull(slot);
    }

    private boolean CheckIfNull(String[] slot) {
        if (slot[0] == null|| slot[1] == null|| slot[2] == null) {
            return false;
        }

        System.out.println(slot[0] + " == " + slot[1] + " == " + slot[2]);
        return Objects.equals(slot[0], slot[1]) && Objects.equals(slot[1], slot[2]);
    }

}
