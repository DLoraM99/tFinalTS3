
package Graphifs;


import java.awt.Font;
import java.awt.image.BufferedImage;


public class Recursos {
    public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 46;
    public static BufferedImage player;
    //effects
    public static BufferedImage speed;
    // explosion
    public static BufferedImage[] exp = new BufferedImage[10];
    //laser
    public static BufferedImage bluelasser,greenlasser,redlasser;
    //Meteors
    
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tiny = new BufferedImage[2];
     // numbers
    public static BufferedImage[] numbers = new BufferedImage[11];
    // power ups
    public static BufferedImage[] Tiny2= new BufferedImage[2] ;
    // ui
    public static BufferedImage blueBtn;
    public static BufferedImage greyBtn;
    public static BufferedImage Fondo;

    
    //vida
    public static BufferedImage life;
    // fonts

	public static Font fontBig;
	public static Font fontMed;
        
    public static void init(){
        player = IMAGENESySONIDO.ImageLoader("/naves/player.png");
        
        speed = IMAGENESySONIDO.ImageLoader("/effects/fire08.png");
        
        bluelasser = IMAGENESySONIDO.ImageLoader("/lasers/laserBlue01.png");
        redlasser = IMAGENESySONIDO.ImageLoader("/lasers/laserRed01.png");
        greenlasser = IMAGENESySONIDO.ImageLoader("/lasers/laserGreen11.png");
        life = IMAGENESySONIDO.ImageLoader("/life/life.png");
        fontBig = IMAGENESySONIDO.loadFont("/fonts/futureFont.ttf", 42);
        Fondo = IMAGENESySONIDO.ImageLoader("/Fondo/espacio.jpg");

	fontMed = IMAGENESySONIDO.loadFont("/fonts/futureFont.ttf", 20);
         for(int i=0; i<Tiny2.length;i++){
            Tiny2[i]=IMAGENESySONIDO.ImageLoader("/meteors/oro"+(i+1)+".png");
        }
        for(int i=0; i<bigs.length;i++){
            bigs[i]=IMAGENESySONIDO.ImageLoader("/meteors/big"+(i+1)+".png");
        }
         for(int i=0; i<meds.length;i++){
            meds[i]=IMAGENESySONIDO.ImageLoader("/meteors/meds"+(i+1)+".png");
        }
          for(int i=0; i<smalls.length;i++){
            smalls[i]=IMAGENESySONIDO.ImageLoader("/meteors/smalls"+(i+1)+".png");
        }
           for(int i=0; i<tiny.length;i++){
            tiny[i]=IMAGENESySONIDO.ImageLoader("/meteors/tiny"+(i+1)+".png");
        }
           for(int i = 0; i < exp.length; i++){
			exp[i] = IMAGENESySONIDO.ImageLoader("/Explosion/"+i+".png");
           }
          for (int i = 0; i < numbers.length; i++) {
            numbers[i] = IMAGENESySONIDO.ImageLoader("/numbers/" + i + ".png");
        }
         greyBtn = IMAGENESySONIDO.ImageLoader("/iu_I/grey_button.png");
         blueBtn = IMAGENESySONIDO.ImageLoader("/iu_I/blue_button.png");
        loaded = true;
           
           
    }
    public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadFont(path, size);
	}
	

}
