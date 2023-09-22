package demogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener , KeyListener{
    private boolean play=false;
    private int score=0;
    private int totalBrick=27;     //To increase the number of bricks
    private Timer timer;           //It is used to decide the timing of the ball
    private int delay=8;
    private int ballposX=170;               //We can maximise the speed of the ball as per our need.
    private int ballposY=350;    
    private int ballXdir=-1;
    private int ballYdir=-2;
    private int playerX=350;
    private MapGenerator map;
    
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true); 
        setFocusTraversalKeysEnabled(true);
      
        
        timer=new Timer(delay,this);   //Used to give the timing to the ball
        timer.start();
        
        map=new MapGenerator(3,9); // Setting the rows and coloumns for the bricks
                                     //Or to increase the no of bricks in the Brick Breaker
        
        
    }
     
    public void paint(Graphics g){   // Using this we will paint the Frame
        
        //Black Canvas
        g.setColor(Color.black);    // From here we can change the color
        g.fillRect(1,1,692,592);
        
        //Border
        g.setColor(Color.yellow);   //g.fillRect(int x, int y, int width, int height)
        g.fillRect(0,0,692,3);
        g.fillRect(0,0,3,592);
        g.fillRect(683,0,3,592);    //Need to see here
        
        //bricks
        map.draw((Graphics2D)g);  //Used for drwaing the bricks
        
        
        //paddle
        g.setColor(Color.green);    //Giving the color to the paddle
        g.fillRect(playerX,550,100,8);
        
        //ball
        g.setColor(Color.red);
        g.fillOval(ballposX,ballposY,20,20);  //From here we can change the position of the Ball.
        
        //color
        g.setColor(Color.green);
        g.setFont(new Font("serif",Font.BOLD,20));  //Used to set the font of the Score
        g.drawString("Score: "+score,550,30);       //Used to set the dimension of the Score
        
        //Gameover
        if(ballposY>=570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            
            g.setColor(Color.green);      //Used for setting the font when the game was over    
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GameOver!!,Score :"+score,200,300);
            
            
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Enter to Restart !!",220,350);  //For Retsart the Game we Use this 
            
        }
        
        if(totalBrick<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            
            g.setColor(Color.green);      //Used for setting the font when the game was over    
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Congratulations You Won!!,Score :"+score,200,300);
            
            
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Enter to Restart !!",220,350);  //For Retsart the Game we Use this 
            
        }
        
    }
    
     private void moveLeft(){          // Used to move the paddle left
        play=true;
        playerX-=20;
    }
    
    private void moveRight(){         //Used to move the paddle right
        play=true;
        playerX+=20;
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode()==KeyEvent.VK_LEFT){    //Used to move the poaddle to the left in the frame
            if(playerX<=0)
                playerX=0;
            else
            moveLeft();
        }
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){    //Used to move the paddle to the left in the frame
            
            if(playerX>=600)            //This is used to stop the paddle to going outside from the frame
                playerX=600;
            else
            moveRight();
        }
        
        if(e.getKeyCode()== KeyEvent.VK_ENTER){    //If we press the enter after restarting the game the same flow would be initiated  by putting the below values
                if(!play){
                    score=0;
                    totalBrick=27;
                    ballposX=170;
                    ballposY=350;
                    ballXdir=-1;
                    ballYdir=-2;
                    playerX=320;
                    
                    map=new MapGenerator(3,9);
                }
        }
        
        
        repaint();          // This is use to move the paddle from the Right and the left.
    }

    
     @Override
    public void actionPerformed(ActionEvent e) {
        if(play){
            
            if(ballposX<=0){
                    ballXdir=-ballXdir;  //Ball not going from outside of the frame
            }
            if(ballposX>=670){
                ballXdir=-ballXdir;
            }
            
            if(ballposY<=0){
                    ballYdir=-ballYdir;
            }
            
            Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
            Rectangle paddleRect=new Rectangle(playerX,550,100,8);
            
            if(ballRect.intersects(paddleRect)){   //This is used to stop the ball from the paddle to going outside
                ballYdir=-ballYdir;
            }
            
            A: for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++)
                {
                if(map.map[i][j]>0){
                    
                    int width=map.brickWidth;
                    int height=map.brickHeight;
                    int brickXpos=80+j*width;
                    int brickYpos=50+i*height;
                    
                    Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);
                    
                    if(ballRect.intersects(brickRect)){  //Used for ball hit to the Brick and get back on the paddle
                        
                        map.setBrick(0,i,j);
                        totalBrick--;
                        score+=5;           //Used to set the score on the per hitting in the brick
                        
                        if(ballposX+19<=brickXpos || ballposX+1>=brickXpos+width){
                            ballXdir=-ballXdir;
                        }
                        else{
                                    ballYdir=-ballYdir;
                        }
                        
                        break A; //Used if ball hit the brick and then get back to the for Loop
                    }
                }    
              }
            }
            
            ballposX+=ballXdir;
            ballposY+=ballYdir;
        }
        
        repaint();   //This is used to move the ball smoothly
    }

    
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
      
    }
    
}

