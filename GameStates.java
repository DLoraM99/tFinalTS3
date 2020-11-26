// estado de juego play - pausa entre otros
package States;

import GameObject.Constantes;
import GameObject.MovingObject;
import GameObject.Player;
import GameObject.Size;
import GameObject.meteoro;
import Graphifs.Recursos;

import Math.Vector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameStates {

 private Player player;   
 private ArrayList<MovingObject>movingObjects= new ArrayList<MovingObject>();
 private int meteors;
    public GameStates(){
       player = new Player(new Vector2D(Constantes.WIDTH/2 - Recursos.player.getWidth()/2,
				Constantes.HEIGHT/2 - Recursos.player.getHeight()/2), new Vector2D(), Constantes.PLAYER_MAX_VEL, Recursos.player, this);
		movingObjects.add(player);
        meteors=2;
        StartWave();
    }
    public void divideMeteor(meteoro meteor){

		Size size = meteor.getSize();

		BufferedImage[] textures = size.textures;

		Size newSize = null;

		switch(size){
		case BIG:
			newSize =  Size.MED;
			break;
		case MED:
			newSize = Size.SMALLS;
			break;
		case SMALLS:
			newSize = Size.TINY;
			break;
		default:
			return;
		}

		for(int i = 0; i < size.quantity; i++){
			movingObjects.add(new meteoro(
					meteor.getPosition(),
					new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
					Constantes.METEOR_VEL*Math.random() + 1,
					textures[(int)(Math.random()*textures.length)],
					this,
					newSize
					));
		}	
	}
    private void StartWave(){
       double x,y;
       
       for(int i=0;i<meteors;i++){
           x=i % 2==0? Math.random()*Constantes.WIDTH:0;
           y=i % 2==0 ? 0 : Math.random()*Constantes.HEIGHT;
           BufferedImage texture = Recursos.bigs[(int)(Math.random()*Recursos.bigs.length)];
           
           movingObjects.add(new meteoro(
                   new Vector2D(x,y),
                   new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                   Constantes.METEOR_VEL*Math.random()+1,
                   texture,
                   this,
                   Size.BIG    
           ));
       }
       meteors++;//para auemntar los meteoros dentro del juego
    }
    public void update(){
        for(int i=0;i<movingObjects.size();i++)
            movingObjects.get(i).update();
        
        for(int i=0;i<movingObjects.size();i++)
            if(movingObjects.get(i) instanceof meteoro)
                    return;
        StartWave();
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        for(int i=0;i<movingObjects.size();i++)
            movingObjects.get(i).draw(g);
    }
    public ArrayList<MovingObject> getMovingObjects(){
        return movingObjects;
    }

    
}
