
package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import GameObject.Constantes;
import Graphifs.Recursos;
import Graphifs.Loader;
import Graphifs.Text;
import Math.Vector2D;

public class LoadingState extends State{

	private Thread loadingThread;

	private Font font;

	public LoadingState(Thread loadingThread) {
		this.loadingThread = loadingThread;
		this.loadingThread.start();
		font = Loader.loadFont("/fonts/futureFont.ttf", 38);
	}

	@Override
	public void update() {
		if(Recursos.loaded) {
			State.changeState(new MenuState());
			try {
				loadingThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void draw(Graphics g) {
		GradientPaint gp = new GradientPaint(
				Constantes.WIDTH / 2 - Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 - Constantes.LOADING_BAR_HEIGHT / 2,
				Color.WHITE,
				Constantes.WIDTH / 2 + Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 + Constantes.LOADING_BAR_HEIGHT / 2,
				Color.BLUE
				);

		Graphics2D g2d = (Graphics2D)g;

		g2d.setPaint(gp);

		float percentage = (Recursos.count / Recursos.MAX_COUNT);

		g2d.fillRect(Constantes.WIDTH / 2 - Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 - Constantes.LOADING_BAR_HEIGHT / 2,
				(int)(Constantes.LOADING_BAR_WIDTH * percentage),
				Constantes.LOADING_BAR_HEIGHT);

		g2d.drawRect(Constantes.WIDTH / 2 - Constantes.LOADING_BAR_WIDTH / 2,
				Constantes.HEIGHT / 2 - Constantes.LOADING_BAR_HEIGHT / 2,
				Constantes.LOADING_BAR_WIDTH,
				Constantes.LOADING_BAR_HEIGHT);

		Text.drawText(g2d, "SPACE SHIP GAME", new Vector2D(Constantes.WIDTH / 2, Constantes.HEIGHT / 2 - 50),
				true, Color.WHITE, font);


		Text.drawText(g2d, "LOADING...", new Vector2D(Constantes.WIDTH / 2, Constantes.HEIGHT / 2 + 40),
				true, Color.WHITE, font);

	}

}
