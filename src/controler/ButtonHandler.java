package controler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.*;
import org.omg.CORBA.Environment;

public class ButtonHandler extends KeyAdapter {

    public ButtonHandler() {         
    }
  //This function will be used as soon as a key is released.
    public void keyPressed(KeyEvent key) {
    }   
    //This function will be used as soon as a key is released. they KeyEvent key we can use to determine what key we just released
    public void keyReleased(KeyEvent key) {
        switch (key.getKeyCode()) {
           case KeyEvent.VK_UP:
        	   Environnement.agent.goUp();
        	   Environnement.newCycle = true;
                break;
           case KeyEvent.VK_DOWN:
        	   Environnement.agent.goDown();
        	   Environnement.newCycle = true;
               break;
           case KeyEvent.VK_LEFT:
              Environnement.agent.goLeft();
              Environnement.newCycle = true;
               break;
           case KeyEvent.VK_RIGHT:
        	   Environnement.agent.goRight();
        	   Environnement.newCycle = true;
               break;
           case KeyEvent.VK_A:
        	   Environnement.agent.lancerCaillou();
        	   System.out.println("Agent : jette un caillou");
        	   Environnement.newCycle = true;
               break;
           case KeyEvent.VK_Z:
        	   Environnement.agent.goSortir();
        	   System.out.println("Agent : demande a sortir");
        	   Environnement.newCycle = true;
               break;
           case KeyEvent.VK_SPACE:
        	   Environnement.agent.doCycle();	// lancement du cycle de l agent
        	   Environnement.newCycle = true;	// notifie nouveau cycle
        }
    }
    
   
   public void keyTyped(KeyEvent key) {
      
   }
}