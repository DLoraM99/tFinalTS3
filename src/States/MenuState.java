package States;

import java.awt.Graphics;
import java.util.ArrayList;
import GameObject.Constantes;
import Graphifs.Recursos;
import ui.Action;
import ui.Button;

public class MenuState extends State{

	private ArrayList<Button> buttons;

	public MenuState() {
		buttons = new ArrayList<Button>();
                

		buttons.add(new Button(
				Recursos.greyBtn,
				Recursos.blueBtn,
				Constantes.WIDTH / 2- Recursos.greyBtn.getWidth()/2,
				Constantes.HEIGHT / 2 - Recursos.greyBtn.getHeight()*2,
				Constantes.PLAY,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new GameStates());
					}
				}
				));

		buttons.add(new Button(
				Recursos.greyBtn,
				Recursos.blueBtn,
				Constantes.WIDTH / 2 - Recursos.greyBtn.getWidth()/2,
				Constantes.HEIGHT / 2 + Recursos.greyBtn.getHeight()* 4 ,
				Constantes.EXIT,
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
                
                buttons.add(new Button(
				Recursos.greyBtn,
				Recursos.blueBtn,
				Constantes.WIDTH / 2 - Recursos.greyBtn.getWidth()/2,
				Constantes.HEIGHT / 2 + Recursos.greyBtn.getHeight()* 2 ,
				Constantes.CREDITS,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new AcercaDe());
					}
				}
				));
                
                buttons.add(new Button(
				Recursos.greyBtn,
				Recursos.blueBtn,
				Constantes.WIDTH / 2 - Recursos.greyBtn.getWidth()/2,
				Constantes.HEIGHT / 2,
				Constantes.HIGH_SCORES,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new ScoreState());
					}
				}
				));
                
	}


	@Override
	public void update() {
		for(Button b: buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for(Button b: buttons) {
			b.draw(g);
		}
	}

}