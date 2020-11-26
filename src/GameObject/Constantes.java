
package GameObject;

import javax.swing.filechooser.FileSystemView;

public class Constantes {
    //frame dimensiones
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    
    //player properties
    public static final int FIRERATE = 300;
    public static final double DELTAANGLE = 0.1;
    public static final double ACC = 0.2;
    public static final double PLAYER_MAX_VEL = 7.0;
    public static final long FLICKER_TIME = 200;
    public static final long SPAWNING_TIME = 3000;
    public static final long GAME_OVER_TIME= 3000;
    //laser propiedads
    public static final double LASER_VEL=15.0;
    
    //propiedades de meterors
    public static final double METEOR_VEL= 2.0;
    public static final int METEOR_SCORE = 20;
    public static long UFO_FIRE_RATE = 1000;

    //propiedades de oro
     public static final double oro_VEL= 2.0;
     public static final int oro_SCORE = 100;
     public static final int destrucoro_SCORE = 10;
     
    public static final String PLAY = "PLAY";	
    public static final String EXIT = "EXIT";
    public static final String CREDITS = "CREDITS";
    public static final int LOADING_BAR_WIDTH = 500;
    public static final int LOADING_BAR_HEIGHT = 50;
	
    public static final String RETURN = "RETURN";
    public static final String HIGH_SCORES = "HIGHEST SCORES";
	
    public static final String SCORE = "SCORE";
    public static final String DATE = "DATE";
    
    //Nombres
    public static final String N1 = "BENAVIDES CORDOVA, Angel";
    public static final String N2 = "ESTRELLA YAURI, Kelly";
    public static final String N3 = "HURTADO CONDORI, Jorge";
    public static final String N4 = "LORA MOLINA, Diego";
    public static final String N5 = "PRADO MIRANDA, Kevin";
   
    
    
    
    
    
    public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
			"\\Space_rocks\\data.json"; // data.xml if you use XMLParser

	// This variables are required to use XMLParser

	public static final String PLAYER = "PLAYER";
	public static final String PLAYERS = "PLAYERS";

       
}
