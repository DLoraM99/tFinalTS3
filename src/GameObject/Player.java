
package GameObject;

import Graphifs.Recursos;
import Math.Vector2D;
import States.GameStates;
import input.KeyBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import jnaves.window;

public class Player extends MovingObject{

	private Vector2D heading;	
	private Vector2D acceleration;

	private boolean accelerating = false;
	private Chronometer fireRate;

	private boolean spawning, visible;

	private Chronometer spawnTime, flickerTime;

	

	public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameStates gameState) {
		super(position, velocity, maxVel, texture, gameState);
		heading = new Vector2D(0, 1);
		acceleration = new Vector2D();
		fireRate = new Chronometer();
		spawnTime = new Chronometer();
		flickerTime = new Chronometer();
		
	}

	@Override
	public void update() 
	{

		if(!spawnTime.isRunning()) {
			spawning = false;
			visible = true;
		}

		if(spawning) {

			if(!flickerTime.isRunning()) {

				flickerTime.run(Constantes.FLICKER_TIME);
				visible = !visible;

			}

		}

		if(KeyBoard.SHOOT &&  !fireRate.isRunning() && !spawning)
		{		
			gameState.getMovingObjects().add(0,new Laser(
					getCenter().add(heading.scale(width)),
					heading,
					 Constantes.LASER_VEL,
                                         angle,
                                         Recursos.redlasser,
					gameState
					));
			fireRate.run(Constantes.FIRERATE);
			
		}

		

		if(KeyBoard.RIGHT)
			angle += Constantes.DELTAANGLE;
		if(KeyBoard.LEFT)
			angle -= Constantes.DELTAANGLE;

		if(KeyBoard.UP)
		{
			acceleration = heading.scale(Constantes.ACC);
			accelerating = true;
		}else
		{
			if(velocity.getMagnitude() != 0)
				acceleration = (velocity.scale(-1).normalize()).scale(Constantes.ACC/2);
			accelerating = false;
		}

		velocity = velocity.add(acceleration);

		velocity = velocity.limit(maxVel);

		heading = heading.setDirection(angle - Math.PI/2);

		position = position.add(velocity);

		if(position.getX() > Constantes.WIDTH)
			position.setX(0);
		if(position.getY() > Constantes.HEIGHT)
			position.setY(0);

		if(position.getX() < -width)
			position.setX(Constantes.WIDTH);
		if(position.getY() < -height)
			position.setY(Constantes.HEIGHT);


		fireRate.update();
		spawnTime.update();
		flickerTime.update();
		collidesWith();
	}

    @Override
    public void recoger(){
        spawning= false;
        spawnTime.run(Constantes.SPAWNING_TIME);
    }
    @Override
	public void Destroy() {
		spawning = true;
		spawnTime.run(Constantes.SPAWNING_TIME);
		
		if(!gameState.subtractLife()) {
			gameState.gameOver();
			super.Destroy();
		}
		resetValues();

	}
    private void resetValues() {

		angle = 0;
		velocity = new Vector2D();
		position = GameStates.PLAYER_START_POSITION;
	}

	@Override
	public void draw(Graphics g) {

		if(!visible)
			return;

		Graphics2D g2d = (Graphics2D)g;

		AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX() + width/2 + 5,
				position.getY() + height/2 + 10);

		AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX() + 5, position.getY() + height/2 + 10);

		at1.rotate(angle, -5, -10);
		at2.rotate(angle, width/2 -5, -10);

		if(accelerating)
		{
			g2d.drawImage(Recursos.speed, at1, null);
			g2d.drawImage(Recursos.speed, at2, null);
		}



		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

		at.rotate(angle, width/2, height/2);

		g2d.drawImage(texture, at, null);

	}

	public boolean isSpawning() {return spawning;}

}
    
