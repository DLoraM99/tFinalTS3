
package GameObject;

import Graphifs.Recursos;
import java.awt.image.BufferedImage;


public enum Size {
    BIG(2,Recursos.bigs) ,MED(2,Recursos.smalls) ,SMALLS(2,Recursos.tiny) ,TINY(0,null) ;
    
    public int quantity;
    
    public BufferedImage[] textures;
    
    private Size(int quantity, BufferedImage[] textures){
        this.quantity = quantity;
        this.textures = textures;
    }
    
        public enum size2{
    SMALLS(2,Recursos.Tiny2),TINY2(0,null);
    public int quantity;
    
    public BufferedImage[] textures;
    
    private size2(int quantity, BufferedImage[] textures){
        this.quantity = quantity;
        this.textures = textures;
}}
}
