package voidane.tictactoe.com.Frame;

import voidane.tictactoe.com.Actions.OnPlayerTurn;
import voidane.tictactoe.com.Actions.ResetGame;

import javax.swing.*;
import java.awt.*;

public class TTTFrame {

    JFrame panel;
    JButton[] slots = new JButton[9];
    JTextPane display;
    String userTurn = "x";
    JButton reset;
    private boolean isWinner;

    /***
     * Creates the GUI window.
     * @param createFrane: true when we want to create a window program for tic-tac-toe
     */
    public TTTFrame(boolean createFrane) {
        if (createFrane) {
            createJFrame();
            createSlotButtons();
            createDisplayText();
            createResetButton();

            // Fix any background and text for buttons or fields.
            panel.repaint();
        }
    }

    /**
     * Creates the reset button
     */
    private void createResetButton() {
        reset = new JButton("RESET");
        reset.setBounds(165, 350, 90, 30);
        reset.setBackground(Color.WHITE);
        ResetGame listenerReset = new ResetGame(this);
        reset.addActionListener(listenerReset);
        panel.add(reset);
    }

    /**
     * Resets the game to start another.
     */
    public void resetGame() {

        for (JButton slot : slots) {
            slot.setText("");
        }

        this.isWinner = false;
        createDisplayText();
        System.out.println("Reset the game");
        panel.repaint();
    }

    /**
     * Creates the founding base for the GUI
     */
    private void createJFrame() {
        panel = new JFrame("Tic Tac Toe");
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.getContentPane().setBackground(Color.black);
        panel.setSize(450,450);
        panel.setLayout(null);
        panel.setVisible(true);
    }

    /**
     * The display text will always show whose turn it is
     */
    private void createDisplayText() {
        display = new JTextPane();
        display.setBounds(100,20,220, 55);
        display.setBackground(Color.ORANGE);

        // Use HTML for styled font
        display.setContentType("text/html");

        display.setText("""
                <html>
                    <center>
                        <font color="white">
                            Current Turn
                        <br>
                            x
                        </font>
                    </center>
                </html>
                """
        );

        panel.add(display);
        panel.repaint();
    }

    /**
     * Sets the winner inside the display text box.
     * @param winner the user that won.
     */
    public void setWinnerDisplayText(String winner) {
        display.setText(
                """
                    <html>
                        <center>
                            <font size="+5">
                                Winner!
                """ +
                        winner
                + """           
                            </font>
                        </center>
                    </html>
                """
        );
    }

    /**
     * Creates each of the grid slots as buttons for tic-tac-toe for users to use
     */
    private void createSlotButtons(){


        for ( int i = 0; i < slots.length ; i++ )
        {
            slots[i] = new JButton();
            slots[i].setActionCommand("" + i);

            // For on reset
            slots[i].setText("");

            slots[i].setBackground(Color.WHITE);
            Font buttonFont = new Font(slots[i].getFont().getName(), slots[i].getFont().getStyle(), 35);
            slots[i].setFont(buttonFont);
        }

        slots[0].setBounds(100, 100, 70,70);
        slots[1].setBounds(175, 100, 70,70);
        slots[2].setBounds(250, 100, 70,70);
        slots[3].setBounds(100, 175, 70,70);
        slots[4].setBounds(175, 175, 70,70);
        slots[5].setBounds(250, 175, 70,70);
        slots[6].setBounds(100, 250, 70,70);
        slots[7].setBounds(175, 250, 70,70);
        slots[8].setBounds(250, 250, 70,70);

        OnPlayerTurn onPlayerTurn = new OnPlayerTurn(this);

        for (JButton slot : slots) {
            panel.add(slot);
            slot.addActionListener(onPlayerTurn);
        }

        panel.revalidate();
        panel.repaint();

    }

    /**
     * @return the base GUI frame
     */
    public JFrame getFramePanel(){
        return this.panel;
    }

    /**
     * Get a button from the grid at a specie location
     * @param index : The location of the button you are getting
     * @return : the location of the button specified.
     */
    public JButton getButtonGridList(int index) {
        return this.slots[index];
    }

    /**
     * @return all the buttons as JButton array
     */
    public JButton[] getAllButtonGridList() {
        return this.slots;
    }

    /**
     * @return the programs display text showing whose turn it is
     */
    public JTextPane getTurnDisplayText() {
        return this.display;
    }

    /**
     * Switch the players turn with encoded HTML to the new user
     * this should be x or o decided from TTTFrame$getUserTurn
     * @param user should be x or o to replace the user turn
     */
    public void setUserTurnDisplayText(String user){
        display.setText("""
                <html>
                    <center>
                        <font color="white">
                            Current Turn
                        <br>
                        """ +
                            user
                        + """
                        </font>
                    </center>
                </html>
                """
        );
    }

    /**
     * x = odd, o = even
     * @return x or o
     */
    public String getUserTurn(int turn) {
        if (turn % 2 == 0)
            return "x";
        return "o";
    }

    public void setUserTurn(String userTurn) {
        this.userTurn = userTurn;
    }

    public String getUserTurnNow() {
        return this.userTurn;
    }

    public boolean isWinner() {
        return this.isWinner;
    }

    public void setWinner() {
        this.isWinner = true;
    }
}
