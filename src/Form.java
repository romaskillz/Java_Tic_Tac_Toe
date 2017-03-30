import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RSKILLZ on 30.03.2017.
 */
public class Form extends JFrame {
    private Map map;

    public Form() {
        setTitle("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(600, 200);
        setSize(500, 554);
        setLayout(new BorderLayout());
        map = new Map(3);
        add(map, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout());
        add(bottom, BorderLayout.SOUTH);
        JButton exitGame = new JButton("Exit Game");
        JButton startNew = new JButton("Start New Game");

        bottom.add(startNew);

        bottom.add(exitGame);
        SNGame sngame = new SNGame(this);

        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sngame.setVisible(true);

            }
        });

        setVisible(true);
    }

    public void createNewGameField(int count, int dotsToWin) {
        map.startNewGame(count, dotsToWin);
    }
}