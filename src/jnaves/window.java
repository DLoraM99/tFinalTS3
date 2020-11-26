
package jnaves ;

import GameObject.Constantes;
import Graphifs.Recursos;
import static Graphifs.Recursos.Fondo;
import States.GameStates;
import States.LoadingState;
import States.MenuState;
import States.State;
import input.KeyBoard;
import input.MouseInput;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;



public class window extends JFrame implements Runnable{
    
    private static final long serialVersionUID = 1L;
    public static final int WIDTH=1000, HEIGHT=600;
    private Canvas canvas;
    //hilo para que no se sobrecargue el juego con las acciones
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
   
    //para nivelar la velocidad
    private final int FPS = 60;
    private double TARGETTIME=1000000000/FPS;
    private double delta=0;
    private int AVERAGEFPS=FPS;
    
    //private GameStates gameState;//coneccion con estado de juego 
    private KeyBoard KeyBoard;
    private MouseInput mouseInput;
    
    
    public window(){
        
        //tienen que ver con la pantalla 
        setTitle("Space rocks");
        setSize(Constantes.WIDTH,Constantes.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
        
        //canvas
        canvas=new Canvas();
        KeyBoard=new KeyBoard();
        mouseInput = new MouseInput();
        
        canvas.setPreferredSize(new Dimension(Constantes.WIDTH,Constantes.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constantes.WIDTH,Constantes.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constantes.WIDTH,Constantes.HEIGHT));
        canvas.setFocusable(true);
        
        
        
        
        add(canvas);
        canvas.addKeyListener(KeyBoard);
        canvas.addMouseListener(mouseInput);
        canvas.addMouseMotionListener(mouseInput);
        setVisible(true);
    }

    
    public static void main(String[] args) {
        new window().start();
        
    }
    
    private void update(){
        //metodo para actualizar 
        KeyBoard.update();
        //gameState.update();
        State.getCurrentState().update();
        
        
    }
    
    private void draw(){
        bs=canvas.getBufferStrategy();
        if(bs==null){
            canvas.createBufferStrategy(3);
            return;
        }
        g=bs.getDrawGraphics();
        // para diBujar en el canva
        
	g.setColor(Color.BLACK);
        
	g.fillRect(0, 0, WIDTH, HEIGHT);

	//gameState.draw(g);
        g.drawImage(Fondo, 0, 0, getWidth(), getHeight(), null);
        State.getCurrentState().draw(g);
        
        g.setColor(Color.WHITE);
        

	g.drawString(""+AVERAGEFPS, 10, 20);
        
        
        
        //-----------------------
        g.dispose();
        bs.show();
    }
    
    private void init(){
        Thread loadingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Recursos.init();
			}
		});
        //Recursos.init();//llamar a recursos para cargar los metodos
        //gameState = new GameStates();
        //State.changeState(new MenuState());
        State.changeState(new LoadingState(loadingThread));
    }
    
    @Override
    public void run(){
        long now=0;
        long lastTime=System.nanoTime();
        int frames =0;
        long time =0;
        
        init();
        
        while(running){
            now=System.nanoTime();
            delta +=(now-lastTime)/TARGETTIME;
            time += (now - lastTime);
            lastTime = now;
            
            if(delta >=1){
                update();
                draw();
                delta--;
                frames ++;
                
            }
            
            //manejo de fotogramas 
            if(time >=1000000000){
                AVERAGEFPS = frames;
                frames=0;
                time =0;
            }
        }
        stop();
        
    }
    private void start(){
        thread=new Thread(this);
        thread.start();
        running=true;
    }
    private void stop(){
        try {
            thread.join();
            running=false;
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }

    
}
