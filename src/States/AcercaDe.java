package States;

import GameObject.Constantes;
import Graphifs.Recursos;
import Graphifs.Text;
import Math.Vector2D;
import io.JSONParser;
import io.ScoreData;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import ui.Action;
import ui.Button;


public class AcercaDe extends State{
    private Button returnButton;

	private PriorityQueue<ScoreData> highScores;

	private Comparator<ScoreData> scoreComparator;

	private ScoreData[] auxArray;

	public AcercaDe() {
		returnButton = new Button(
				Recursos.greyBtn,
				Recursos.blueBtn,
				Recursos.greyBtn.getHeight(),
				Constantes.HEIGHT - Recursos.greyBtn.getHeight() * 2,
				Constantes.RETURN,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new MenuState());
					}
				}
			);

		scoreComparator = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
			}
		};

		highScores = new PriorityQueue<ScoreData>(10, scoreComparator);
              try {
			ArrayList<ScoreData> dataList = JSONParser.readFile();

			for(ScoreData d: dataList) {
				highScores.add(d);
			}

			while(highScores.size() > 10) {
				highScores.poll();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update() {
		returnButton.update();
	}

	@Override
	public void draw(Graphics g) {
		returnButton.draw(g);

		auxArray = highScores.toArray(new ScoreData[highScores.size()]);

		


		Vector2D scorePos = new Vector2D(
				Constantes.WIDTH / 2 + 1,
				150
				);
		
                Vector2D N1 = new Vector2D(
				Constantes.WIDTH / 2 - 1,
				100
				);
                
                Vector2D N2 = new Vector2D(
				Constantes.WIDTH / 2 - 1,
				100
				);
                
                Vector2D N3 = new Vector2D(
				Constantes.WIDTH / 2 - 1,
				100
				);
                
                Vector2D N4 = new Vector2D(
				Constantes.WIDTH / 2 - 1,
				100
				);
                
                Vector2D N5 = new Vector2D(
				Constantes.WIDTH / 2 - 1,
				100
				);

		Text.drawText(g, Constantes.CREDITS, scorePos, true, Color.BLUE, Recursos.fontBig);
		
		scorePos.setY(scorePos.getY() + 1000);
                N1.setY(N1.getY() + 120);
                N2.setY(N2.getY() + 160);
                N3.setY(N3.getY() + 200);
                N4.setY(N4.getY() + 240);
                N5.setY(N5.getY() + 280);

		for(int i = auxArray.length - 1; i > -1; i--) {

			ScoreData d = auxArray[i];
                        Text.drawText(g, Constantes.N1, N1, true, Color.WHITE, Recursos.fontBig);
                        Text.drawText(g, Constantes.N2, N2, true, Color.WHITE, Recursos.fontBig);
                        Text.drawText(g, Constantes.N3, N3, true, Color.WHITE, Recursos.fontBig);
                        Text.drawText(g, Constantes.N4, N4, true, Color.WHITE, Recursos.fontBig);
                        Text.drawText(g, Constantes.N5, N5, true, Color.WHITE, Recursos.fontBig);
                    	
		}


        }


        }
    

