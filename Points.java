import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Points extends JPanel {

	static final int numOfNeurons=900;
	static final int numOfInputs=300;
	static int maxITR=500;
	static Point2D.Double[][] ni= {SOM.initializeNuerons(numOfNeurons), SOM.initializeInputsCircle(numOfInputs)};;
	static final int edgeX=1270/4;
	static final int edgeY=650/4;



	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		Point2D.Double[] neurons = ni[0];
		Point2D.Double[] inputs = ni[1];
		SOM.radius=neurons.length/2;
		neurons = SOM.sortX(neurons);
		g2d.setColor(Color.black);
		for (int i = 0; i < neurons.length-1; i++) {
			g2d.drawLine((int)(neurons[i].x*edgeX),(int)( neurons[i].y*edgeY),(int)(neurons[i].x*edgeX),(int)( neurons[i].y*edgeY));

		}
		g2d.setColor(Color.blue); //input
		for (int i = 0; i < inputs.length; i++) {
			g2d.fillOval((int)(inputs[i].x*edgeX),(int)(inputs[i].y*edgeY) , 10, 10);
		}
		g2d.setColor(Color.yellow); //neurons at first
		for (int i = 0; i < neurons.length; i++) {
			g2d.fillOval((int)(neurons[i].x*edgeX),(int)(neurons[i].y*edgeY) , 10, 10);
		}

		for (int itr = 0; itr < maxITR; itr++) {


			for (int i = 0; i < inputs.length; i++) {
				int j = SOM.checkClosest(neurons, inputs[i],i);
				SOM.updateWeight(neurons, j, inputs[i]);

			}
			SOM.updateLerningRate(itr);
			SOM.radius--;

		}	g2d.setColor(Color.green); 
		//neurons at last
		for (int i = 0; i < inputs.length; i++) {
			//g2d.drawLine((int)(neurons[SOM.conected[i]].x*edgeX),(int)( neurons[SOM.conected[i]].y*edgeY),(int)(inputs[i]. x*edgeX),(int)(inputs[i].y*edgeY));
			g2d.fillOval((int)(inputs[i].x*edgeX),(int)(inputs[i].y*edgeY) , 10, 10);
		}



		g2d.setColor(Color.red);
		for (int i = 0; i < neurons.length; i++) {
			g2d.fillOval((int)(neurons[i].x*edgeX),(int)(neurons[i].y*edgeY) , 10, 10);
		}
		for (int i = 0; i < neurons.length-1; i++) {
			g2d.drawLine((int)(neurons[i].x*edgeX),(int)( neurons[i].y*edgeY),(int)(neurons[i+1].x*edgeX),(int)( neurons[i+1].y*edgeY));

		}

	}

	public static void main(String[] args) throws IOException {

		Points points = new Points();
		JFrame frame = new JFrame("Points");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(points);
		frame.setSize(250,200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


}
