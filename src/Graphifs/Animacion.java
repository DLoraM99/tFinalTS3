
package Graphifs;

import Math.Vector2D;
import java.awt.image.BufferedImage;

public class Animacion {
	private BufferedImage[] frames;
	private int velocity;
	private int index;
	private boolean running;
	private Vector2D position;
	private long time, lastTime;
    
  public Animacion(BufferedImage[] frames, int velocity, Vector2D position){
		this.frames = frames;
		this.velocity = velocity;
		this.position = position;
		index = 0;
		running = true;
		time = 0;
		lastTime = System.currentTimeMillis();
	}

	public void update(){

		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if(time > velocity){
			time  = 0;//aumentamos el tiempo para que se va corriendo las imagenes 
			index ++;
			if(index >= frames.length){
				running = false;//fianliza la animacion
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public Vector2D getPosition() {
		return position;
	}

	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
    
    
}
