package demogame;

import javax.swing.JFrame;

public class MainClass {
    public static void main(String args[]){
          
        JFrame f=new JFrame();
        f.setTitle("Brick Breaker");
        f.setSize(700,600);
        f.setLocationRelativeTo(null); //Position right in the Corner
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Used to exit the application
        f.setVisible(true); //Used to make the screen visible on the screen
        f.setResizable(false); //Used to resize the Screen as per user wants
        
        
        GamePlay gameplay=new GamePlay(); //Creating an Object for the gameplay class
        f.add(gameplay);
       

    }
    
}
