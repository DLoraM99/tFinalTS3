
package GameObject;

import Math.Vector2D;
import States.GameStates;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class MovingObject extends GameObject {
protected Vector2D velocity;
protected AffineTransform  at;
protected double angle;
protected double maxVel;
protected int width;
protected int height;
protected GameStates gameState;
protected boolean Dead;

    public MovingObject(Vector2D position,Vector2D velocity,double maxVel, BufferedImage texture,GameStates gameState) {
        super(position,texture);
        this.velocity = velocity;
        this.maxVel=maxVel;
        this.gameState=gameState;
        width=texture.getWidth();
        height=texture.getHeight();
        angle = 0;
        
	Dead = false;
    }

 
    protected void collidesWith(){
        ArrayList<MovingObject>movingObjects=gameState.getMovingObjects();
        for(int i=0;i<movingObjects.size();i++){
            MovingObject m= movingObjects.get(i);
            if(m.equals(this))
                continue;
            double distance=m.getCenter().subtract(getCenter()).getMagnitude();
            if(distance < m.width/2 + width/2 && movingObjects.contains(this)){
                objectCollision(m, this);
            }
        
        }
    
    }
    private void objectCollision(MovingObject a , MovingObject b){
                if(a instanceof Player && ((Player)a).isSpawning()) {
			return;
		}
		if(b instanceof Player && ((Player)b).isSpawning()) {
			return;
		}
		
		
                        
                       
              if(a instanceof MeteoroTINY && b instanceof Player){ 
                a.recoger();
              }else if(!(a instanceof meteoro && b instanceof meteoro)||!(a instanceof MeteoroTINY || b instanceof MeteoroTINY)){
                  gameState.playExplosion(getCenter());
                  a.Destroy();
		  b.Destroy();
               
                
                        
			
		
        }
    }
    protected void recoger(){
        Dead=false;
        gameState.getMovingObjects().remove(this);
    }
    protected void Destroy(){
        Dead = true;
		if(!(this instanceof Laser))
		 gameState.getMovingObjects().remove(this);	
    }
    
    protected Vector2D getCenter(){
        return new Vector2D (position.getX()+width/2,position.getY()+height/2);
    }
    public boolean isDead() {return Dead;}
}
