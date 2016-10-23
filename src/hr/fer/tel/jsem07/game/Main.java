package hr.fer.tel.jsem07.game;

import javax.swing.JFrame;

public class Main
{

   public static void main(String[] args)
  {
    Gui gui = new Gui();
    JFrame jframe= new JFrame("Križiæ-kružiæ"); 

   
    jframe.getContentPane().add( gui );
    jframe.setSize( 433, 453 );
    jframe.setResizable( false );
    jframe.setVisible( true );
    jframe.setDefaultCloseOperation( 3 );
    
    

    
    
  }

}
