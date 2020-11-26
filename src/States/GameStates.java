// estado de juego play - pausa entre otros
package States;

import GameObject.Chronometer;
import GameObject.Constantes;
import GameObject.Message;
import GameObject.MeteoroTINY;
import GameObject.MovingObject;
import GameObject.Player;
import GameObject.Size;
import GameObject.Size.size2;
import GameObject.meteoro;
import Graphifs.Animacion;
import Graphifs.Recursos;
import Graphifs.Text;
import bd.*;

import Math.Vector2D;
import io.JSONParser;
import io.ScoreData;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import static javafx.scene.text.Font.font;

public class GameStates extends State{
 public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constantes.WIDTH/2 - Recursos.player.getWidth()/2,
			Constantes.HEIGHT/2 - Recursos.player.getHeight()/2);
 private Player player;   
 private ArrayList<MovingObject>movingObjects= new ArrayList<MovingObject>();
 private ArrayList<Animacion> explosions = new ArrayList<Animacion>();
 private ArrayList<Message> messages = new ArrayList<Message>();
 private int meteors;
 private int oro;
 private int score=0;
 private int lives = 3;
 private int waves=1;

 private Chronometer gameOverTimer;
private boolean gameOver;

private Chronometer ufoSpawner;


 
 
    public GameStates(){
       player = new Player(new Vector2D(Constantes.WIDTH/2 - Recursos.player.getWidth()/2,
				Constantes.HEIGHT/2 - Recursos.player.getHeight()/2), new Vector2D(), Constantes.PLAYER_MAX_VEL, Recursos.player, this);
	movingObjects.add(player);
        meteors=1;// numero de meteoritos
        oro=2;
        StartWave();
        StartORO();
       ufoSpawner = new Chronometer();

		
    }
   public void addScore(int value, Vector2D position) {
		score += value;
		messages.add(new Message(position, true, "+"+value+" score", Color.WHITE, false, Recursos.fontMed));
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
    
    private void StartORO(){
       double x,y;
       
       for(int i=0;i<oro;i++){
           x=i % 2==0? Math.random()*Constantes.WIDTH:0;
           y=i % 2==0 ? 0 : Math.random()*Constantes.HEIGHT;
           BufferedImage texture = Recursos.Tiny2[(int)(Math.random()*Recursos.Tiny2.length)];
           
           movingObjects.add(new MeteoroTINY(
                   new Vector2D(x,y),
                   new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                   Constantes.oro_VEL*Math.random()+1,
                   texture,
                   this,
                   size2.TINY2 
                   
           ));
        
       }
       oro++;//para auemntar el oro dentro del juego
    }
    private void StartWave(){
       messages.add(new Message(new Vector2D(Constantes.WIDTH/2, Constantes.HEIGHT/2), false,
				"WAVE "+waves, Color.WHITE, true, Recursos.fontBig));
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
           
       }for(int i=0;i<oro;i++){
           x=i % 2==0? Math.random()*Constantes.WIDTH:0;
           y=i % 2==0 ? 0 : Math.random()*Constantes.HEIGHT;
           BufferedImage texture = Recursos.Tiny2[(int)(Math.random()*Recursos.Tiny2.length)];
           
           movingObjects.add(new MeteoroTINY(
                   new Vector2D(x,y),
                   new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                   Constantes.oro_VEL*Math.random()+1,
                   texture,
                   this,
                   size2.TINY2 
                   
           ));
           }
       meteors++;//para auemntar los meteoros dentro del juego
       oro++;//para auemntar el oro dentro del juego
    }
    
    public void playExplosion(Vector2D position){//para detectar el vector que coliciona 
		explosions.add(new Animacion(
				Recursos.exp,
				50,
				position.subtract(new Vector2D(Recursos.exp[0].getWidth()/2, Recursos.exp[0].getHeight()/2))
				));
	}
    public void update(){
       for(int i = 0; i < movingObjects.size(); i++) {

			MovingObject mo = movingObjects.get(i);

			mo.update();
			if(mo.isDead()) {
				movingObjects.remove(i);
				i--;
			}

		}

		for(int i = 0; i < explosions.size(); i++){
			Animacion anim = explosions.get(i);
			anim.update();
			if(!anim.isRunning()){
				explosions.remove(i);
			}

		}
/*
		if(gameOver && !gameOverTimer.isRunning())  codigo error tenerlo en cuenta 
			try {
				ArrayList<ScoreData> dataList = JSONParser.readFile();
				dataList.add(new ScoreData(score));
				JSONParser.writeFile(dataList);

			} catch (IOException e) {
				e.printStackTrace();
			}
			State.changeState(new MenuState());
		}
*/               
 


		

		for(int i = 0; i < movingObjects.size(); i++)
			if(movingObjects.get(i) instanceof meteoro)
				return;

		StartWave();
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        for(int i = 0; i < messages.size(); i++) {
			messages.get(i).draw(g2d);
			if(messages.get(i).isDead())
				messages.remove(i);
		}
        for(int i=0;i<movingObjects.size();i++)
            movingObjects.get(i).draw(g);
        
        for(int i = 0; i < explosions.size(); i++){
            Animacion anim = explosions.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),
		null);

		}
        drawScore(g);
	drawLives(g);
       
    }    
     
    private void drawScore(Graphics g) {
		
		Vector2D pos = new Vector2D(850, 25);
		
		String scoreToString = Integer.toString(score);
		
		for(int i = 0; i < scoreToString.length(); i++) {
			
			g.drawImage(Recursos.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))],
					(int)pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX() + 20);
			
		}
		
	}
private void drawLives(Graphics g){

		if(lives < 1)
			return;

		Vector2D livePosition = new Vector2D(25, 25);

		g.drawImage(Recursos.life, (int)livePosition.getX(), (int)livePosition.getY(), null);

		g.drawImage(Recursos.numbers[10], (int)livePosition.getX() + 40,
				(int)livePosition.getY() + 5, null);

		String livesToString = Integer.toString(lives);

		Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());

		for(int i = 0; i < livesToString.length(); i ++)
		{
			int number = Integer.parseInt(livesToString.substring(i, i+1));

			if(number <= 0)
				break;
			g.drawImage(Recursos.numbers[number],
					(int)pos.getX() + 60, (int)pos.getY() + 5, null);
			pos.setX(pos.getX() + 20);
		}

	}
public ArrayList<MovingObject> getMovingObjects(){
        return movingObjects;
    }
public ArrayList<Message> getMessages() {
		return messages;
	}
public Player getPlayer() {
		return player;
	}
public boolean subtractLife() {
		lives --;
		return lives > 0;
	}

public void gameOver() {
		Message gameOverMsg = new Message(
				PLAYER_START_POSITION,
				true,
				"GAME OVER",
				Color.WHITE,
				true,
				Recursos.fontBig);

		this.messages.add(gameOverMsg);
		//gameOverTimer.run(Constantes.GAME_OVER_TIME); codigo error tener en cuenta el cambio sale en un if en update
		gameOver = true;
                try {
				ArrayList<ScoreData> dataList = JSONParser.readFile();
				dataList.add(new ScoreData(score));
				JSONParser.writeFile(dataList);
                                
                                System.out.println(score);
                                
                                Marcador m = new Marcador();
                                m.setPuntaje(score);
                                ScoreData d = new ScoreData(score);                                                              
                                String date = d.getDate();
                                m.setFecha(date); 
                                
                                connector con = new connector();        
                                con.RegistrarMarcador(m);

			} catch (IOException e) {
				e.printStackTrace();
			}
			State.changeState(new MenuState());
	}

}
