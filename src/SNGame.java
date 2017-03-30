import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RSKILLZ on 30.03.2017.
 */
public class SNGame extends JFrame {

    private Form owner;


    public SNGame(Form form){
        owner = form;
        setTitle("Options");
        setSize(300,250);
        setLocation(700,300);
        setResizable(false);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JLabel gameMode = new JLabel("Игроки:");
        gameMode.setFont(new Font("Arial",Font.BOLD,16));
        gameMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gameMode);
        ButtonGroup bg = new ButtonGroup();
        JRadioButton jrb1 = new JRadioButton("Игрок vs Игрок");
        bg.add(jrb1);
        jrb1.setSelected(true);
        jrb1.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(jrb1);
        JRadioButton jrb2 = new JRadioButton("Игрок vs Компьютер");
        bg.add(jrb2);
        jrb2.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(jrb2);

        JLabel fieldSize = new JLabel("Размер поля: 3");
        fieldSize.setFont(new Font("Arial",Font.BOLD,16));
        fieldSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(fieldSize);

        JSlider dotsToWinSlider = new JSlider(3,10);

        JSlider slider = new JSlider(3,10);
        slider.setPaintTicks(true);
        slider.setValue(3);
        slider.setAlignmentY(Component.LEFT_ALIGNMENT);
        add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fieldSize.setText("Размер поля: "+slider.getValue());
                dotsToWinSlider.setMaximum(slider.getValue());
            }
        });

        JLabel dotsToWinLabel = new JLabel("Выигрыш при: 3");
        dotsToWinLabel.setFont(new Font("Arial",Font.BOLD,16));
        dotsToWinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(dotsToWinLabel);


        dotsToWinSlider.setPaintTicks(true);
        dotsToWinSlider.setValue(3);
        dotsToWinSlider.setAlignmentY(Component.LEFT_ALIGNMENT);
        add(dotsToWinSlider);

        dotsToWinSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dotsToWinLabel.setText("Выигрыш при: "+dotsToWinSlider.getValue());
            }
        });


        JButton jbOK = new JButton("OK");
        jbOK.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jbOK);


        jbOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.createNewGameField(slider.getValue(), dotsToWinSlider.getValue());
                setVisible(false);
            }
        });
        setVisible(false);
    }
}
