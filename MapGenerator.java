package demogame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int map[][];                  //Variable to generate the brick
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row,int col){
        
        map=new int[row][col];
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j]=1;
            }
        }
        brickWidth=540/col;              //Used to set the Dimension of the Brick in rows and cols
        brickHeight=150/row;
        
    }

    MapGenerator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void setBrick(int value,int r,int c){
        map[r][c]=value;
    }
    
    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0; j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColor(Color.white);           //Used to set the Brick Color
                    g.fillRect(j*brickWidth+80,i*brickHeight+80,brickWidth, brickHeight);    //Used to set the Bricks in the center
                    
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));  // Used to give the Dimension of the Brick that is small and big
                    g.drawRect(j*brickWidth+80,i*brickHeight+80,brickWidth, brickHeight);
                    
                }
            }
      }
      
    }
            
 }

