import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by RSKILLZ on 30.03.2017.
 */
public class Map extends JPanel {
    private int linesCount;
    private static final int PANEL_SIZE = 500;
    private int cellSize;
    private int[][] field;
    private final static int PLAYER1_DOT = 1;
    private final static int PLAYER2_DOT = 2;
    private final static int EMPTY = 0;
    private int dotsToWin;
    private Random rnd = new Random();
    private boolean gameOver;
    private String gameOverMsg;



    public Map(int linesCount){
        startNewGame(linesCount, linesCount);
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int cmx, cmy;
                cmx = e.getX()/cellSize;
                cmy = e.getY()/cellSize;
                if (setDot(cmx,cmy,PLAYER1_DOT)) {
                    if(!gameOver) {
                        checkWin(PLAYER1_DOT);
                        isFieldFull();
                        repaint();
                        aiTurn();
                    }
                }
            }
        });

    }

    public void startNewGame(int linesCount, int dotsToWin){
        this.linesCount = linesCount;
        this.dotsToWin = dotsToWin;
        cellSize = PANEL_SIZE/linesCount;
        field = new int[linesCount][linesCount];
        gameOver = false;
        gameOverMsg = "";
        repaint();
    }

    public  boolean setDot(int x, int y, int dot){
        if(field[x][y]==EMPTY){
            field[x][y] = dot;
            return  true;
        }
        return false;
    }

    public boolean isFieldFull(){
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if(field[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        gameOver = true;
        gameOverMsg = "DRAW";
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < linesCount; i++) {
            g.drawLine(0,cellSize*i,PANEL_SIZE,i*cellSize);
            g.drawLine(cellSize*i,0,cellSize*i,PANEL_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if(field[i][j] != EMPTY){
                    if(field[i][j] == PLAYER1_DOT){
                        g.setColor(Color.red);
                    }
                    if(field[i][j] == PLAYER2_DOT){
                        g.setColor(Color.blue);
                    }
                    g.fillOval(i*cellSize+2,j*cellSize+2,cellSize-4,cellSize-4);
                }
            }
        }
        if(gameOver){

            Font f = new Font("Arial",Font.BOLD,48);
            FontMetrics metr = getFontMetrics(f);
            g.setColor(Color.gray);
            g.fillRect(0,195,500,80);
            g.setFont(f);
            g.setColor(Color.black);
            g.drawString(gameOverMsg, (PANEL_SIZE - metr.stringWidth(gameOverMsg)) / 2 - 2, 248);
            g.setColor(Color.yellow);
            g.drawString(gameOverMsg, (PANEL_SIZE - metr.stringWidth(gameOverMsg)) / 2, 250);
        }

    }

    public void aiTurn(){
        if(gameOver) return;
        int x,y;
        do{
            x = rnd.nextInt(linesCount);
            y = rnd.nextInt(linesCount);
        }while(!setDot(x,y,PLAYER2_DOT));
        checkWin(PLAYER2_DOT);
        isFieldFull();
        repaint();
    }




    public boolean checkWin(int ox) {
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (checkLine(i, j, 1, 0, dotsToWin, ox) || checkLine(i, j, 0, 1, dotsToWin, ox )
                        || checkLine(i, j, 1, 1, dotsToWin, ox) || checkLine(i, j, 1, -1, dotsToWin, ox)) {
                    gameOver = true;
                    if(ox == PLAYER1_DOT){
                        gameOverMsg = "PLAYER 1 WON!";
                    }
                    if(ox == PLAYER2_DOT){
                        gameOverMsg = "PLAYER 2 WON!";
                    }
                    return true;
                }


            }
        }
        return false;
    }

    public boolean checkLine(int cx, int cy, int vx, int vy, int l, int ox) {
        if (cx + l * vx > linesCount || cy + l * vy > linesCount || cy + l * vy < -1 || cx + l * vx < -1) return false;
        for (int i = 0; i < l; i++) {
            if (field[cx + i * vx][cy + i * vy] != ox) return false;
        }
        return true;
    }
}