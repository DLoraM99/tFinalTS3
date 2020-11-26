
package GameObject;

import GameObject.Constantes;
import GameObject.MovingObject;
import GameObject.Size;
import GameObject.Size.size2;
import Math.Vector2D;
import States.GameStates;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;




public class MeteoroTINY extends MovingObject {
private Size size2;

    public MeteoroTINY(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameStates gameState,size2 size2) {
        super(position, velocity, maxVel, texture, gameState);
        this.size2=this.size2;
        this.velocity=velocity.scale(maxVel);
    }

    

    

    @Override
    public void update() {
        position=position.add(velocity);
         //para que no salga de las ventanas
          if(position.getX()>Constantes.WIDTH)
              position.setX(0);
          if(position.getY()>Constantes.HEIGHT)
              position.setY(0);
          
          if(position.getX()<0)
              position.setX(Constantes.WIDTH);
          if(position.getY()<0)
              position.setY(Constantes.HEIGHT);
          angle += Constantes.DELTAANGLE/2;       
    }
       @Override  
   public void recoger(){		
		gameState.addScore(Constantes.oro_SCORE,position);
                super.Destroy();
		
	}
    @Override
    public void Destroy(){		
		gameState.addScore(Constantes.destrucoro_SCORE,position);
                super.Destroy();
		
	}
    @Override
    public void draw(Graphics g) {
         Graphics2D g2d=(Graphics2D)g;
        at=AffineTransform.getTranslateInstance(position.getX(),position.getY());
        at.rotate(angle,width/2,height/2);
        g2d.drawImage(texture,at,null);
    }
    

    public Size getsize2(){
        return size2;
       
    }

}