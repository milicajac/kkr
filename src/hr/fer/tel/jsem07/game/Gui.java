


package hr.fer.tel.jsem07.game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;
/**
 * This class represents a graphical user interface for this game I made.
 * It draws a board on which a user plays by clicking.
 * The game's capital class is Table.java. This is still a highly ufinished 
 * version, there are still absolutely no optiones for modifying one's game 
 * instance.
 * @version 0.001 
 * @author miljac
 */


public class Gui
extends JPanel
implements MouseListener
{
  /**
   * The game board.
   */
  private Table table=new Table(); //tabla
  /**
   * Who's turn it is.
   */
  private State turn=State.cross;  // na kome je red
  /**
   * Who's is the first turn in a game
   */
  private State first=State.cross; //ko je prvi
  /**
   * The last move's coordinates
   */
  private int lastI, lastJ;  //koordinate zadnjeg poteza 
  
  /**
   * Used to switch turns between players.
   * @param s Initial state.
   * @return State opposite of the initial state.
   */  
  private State switchTurn(State s)
  {
    if (s==State.cross)
      return State.circle;
    else
      return State.cross;
  }
  
  
//sirina polja ce bit 17
  /**
   * This paints the board and marks on it.
   */
  public void paint( Graphics g )
  {
    g.clearRect( 0, 0, 425, 425 );
    for (int i=17;i<425;i+=17)
    {
      g.setColor( Color.lightGray );
      g.drawLine( i, 0, i, 425 );
      g.drawLine( 0, i, 425, i );
    }
    
    for (int i=1; i<=25; i++)
    {
      for (int j=1; j<=25;j++)
      {
        if (this.table.get(i,j)==State.circle)
          this.drawCircle( i, j, g );
        if (this.table.get( i, j )==State.cross)
          this.drawCross( i, j, g );
      }
    }
  }
  
  /**
   * The default and only constructor. It initializes a 
   * mouselistener and makes the first move. It's impolite 
   * to insist to be first in a first game 
   * all the time, but this is just a test version. 
   */
public Gui()
{
  addMouseListener(this);
  table.put( State.cross, 12, 12 );
  
}
  
  

/**
 * Draws a circle.
 * @param i x-coordinate on the board
 * @param j y-coordinate on the board
 * @param g graphics 
 */

  public void drawCircle(int i, int j, Graphics g) //i,j su koordinate na tabli
  {
    g.setColor( Color.blue );
    g.drawOval( (i-1)*17+2, (j-1)*17+2, 13, 13 );
  }
 
  /**
   * 
   * @param i x-coordinate on the board
   * @param j y-coordinate on the board
   * @param g graphics
   */
  public void drawCross(int i, int j, Graphics g)//i,j su koordinate na tabli
  {
    g.setColor( Color.red );
    g.drawLine( (i-1)*17+2, (j-1)*17+2, (i-1)*17+15, (j-1)*17+15 );
    g.drawLine((i-1)*17+15,(j-1)*17+2,(i-1)*17+2,(j-1)*17+15);
  
  }
  

  /**
   * Handles a mouseclick. Puts a circle on the place a user choose,
   * then initializes calculation of the next move and draws a cross
   * on a place calculated and put on the board by table.java. 
   * If someone has won in the meantime, shows an appropriate message.
   */

  
  public void mouseReleased(MouseEvent e)
  {
    int i= (int) (e.getX()/17)+1;//i,j su koordinate na tabli
    int j= (int) (e.getY()/17)+1;
    
    if ((i<=25)&&(j<=25)&&(i>0)&&(j>0))
      
     /* if (e.getButton() == 2 )//undo
      {
        table.put( State.empty, lastI, lastJ );
        this.repaint();
        this.turn=this.switchTurn( this.turn );
        return;
        
      }*/
      if ((this.table.get( i, j )==State.empty))//kad je gotovo end vraca ko je pobijedio
      {
        lastI=i;
        lastJ=j;
        
        //table.put( this.turn, i, j );
        //this.turn=this.switchTurn(this.turn);
        table.put( State.circle, i, j );
        this.paintImmediately( 0, 0, 425, 425 );
        if (table.end()!=State.empty)
        {
          JOptionPane.showMessageDialog( this, "Pobijedio je "+ ((table.end()==State.circle)? "kružiæ":"križiæ") +"!");
          this.table= new Table();
          this.first= this.turn= this.switchTurn( this.first );
          this.turn= this.first;
          
          table.put( State.cross, 12, 12 );

        }
        else
          table.putAutomatic( State.cross );
        
        
        
        
        this.repaint();
    
        if (table.end()!=State.empty)
        {
          JOptionPane.showMessageDialog( this, "Pobijedio je "+ ((table.end()==State.circle)? "kružiæ":"križiæ") +"!");
          this.table= new Table();
          this.first= this.turn= this.switchTurn( this.first );
          this.turn= this.first;

        }

      }
   }


  
  
  public void mouseClicked(MouseEvent e){  }
  public void mousePressed(MouseEvent e) {  }
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e){ }
  

}
