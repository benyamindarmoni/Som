import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SOM {

	static double learningRate = 0.5;
	static int conected[] ;
	static int radius;

	static void updateWeight(Point2D.Double[] neurons, int j, Point2D.Double input) {
		int count=0;
		for (int i = j; count< radius+1&&i<neurons.length; i++) {
			neurons[i]=	updateNeuron(neurons[i],input);			
			count++;
		}
		count =0;
		for (int i = j-1; i>=0&&count<radius; i--) {
			neurons[i]=updateNeuron(neurons[i],input);
			count++;
		}

	}

	private static Point2D.Double updateNeuron(Point2D.Double neuron, Point2D.Double input) {
		double distance=neuron.distance(input);	
		Double exp = Math.pow(Math.E, (-1 * distance * distance) / (2 * learningRate * learningRate));
		Point2D.Double sub = new Point2D.Double(input.getX() - neuron.getX(), input.getY() - neuron.getY());
		Point2D.Double result = new Point2D.Double(neuron.getX() + (learningRate * sub.getX() * exp), neuron.getY()
				+ (learningRate * sub.getY() * exp));
		neuron =new Point2D.Double( result.x,result.y);

		return neuron;

	}

	static int checkClosest(Point2D.Double[] neurons, Point2D.Double input,int ind) {

		double minDistance = Double.MAX_VALUE;
		int closetNeuronIndex = 0;
		for (int i = 0; i < neurons.length; i++) {
			Double tempDistance = neurons[i].distance(input);
			if (tempDistance < minDistance) {
				minDistance = tempDistance;
				closetNeuronIndex = i;
			}
		}
		conected[ind]=closetNeuronIndex;
		return closetNeuronIndex;
	}

	public static Point2D.Double[] initializeNuerons(int nSize) {

		Point2D.Double[] neurons = new Point2D.Double[nSize];
		for (int i = 0; i < neurons.length; i++) {
			Double x = Math.random();
			neurons[i] = new Point2D.Double(x, 0);
		}
		return neurons;
	}

	public static Point2D.Double[] initializeInputs( int inputSize) {
		conected=new int[inputSize];
		Point2D.Double[] inputs = new Point2D.Double[inputSize];
		for (int i = 0; i < inputs.length; i++) {
			Double x =Math.random();
			Double y =Math.random();
			inputs[i] = new Point2D.Double(x, y);
		}
		return inputs;
	}
	public static Point2D.Double[] initializeInputsHalf( int inputSize) {
		conected=new int[inputSize];
		Point2D.Double[] inputs = new Point2D.Double[inputSize];
		int size=(3*inputs.length)/4;
		int i = 0;
		for (; i < size; i++) {
			Double x =Math.random()*(0.75-0.25)+0.25;
			Double y =Math.random()*(0.75-0.25)+0.25;
			inputs[i] = new Point2D.Double(x, y);
		}
		for (; i < inputs.length; i++) {
			Double x =Math.random();
			Double y =Math.random();
			inputs[i] = new Point2D.Double(x, y);
		}
		return inputs;
	}
	public static Point2D.Double[] initializeInputsLine( int inputSize) {
		conected=new int[inputSize];
		Point2D.Double[] inputs = new Point2D.Double[inputSize];
		for (int i = 0; i < inputs.length; i++) {
			Double x =Math.random();
			Double y;
			if(Math.round( Math.random())==1){
				y=1-x;
			}
			else{
				y=x;
			}
			inputs[i] = new Point2D.Double(x, y);
		}

		return inputs;
	}


    public static Point2D.Double[] initializeInputsCircle(int inputSize) {
        conected = new int[inputSize];
        Point2D.Double[] dots = new Point2D.Double[inputSize];
        int counter = 0;
        double bigRadius = 4;
        double smallRadius = 1;
        while (counter < inputSize) {
            double  x = Math.random() * (4 - 0+1);
            double y = Math.random() * (4 - 0+1);
            double res = (x-2)*(x-2) + (y-2)*(y-2);
            if(res <= bigRadius && res >= smallRadius){
                dots[counter] = new Point2D.Double(x,y);
                counter++;
            }
        }
        return dots;
    }

//	private static void printPoint(Point2D.Double[] neurons, Point2D.Double[] inputs) {
//		for (int i = 0; i < neurons.length; i++) {
//			System.out.print("(" + neurons[i].getX() + ", " + neurons[i].getY() + "), ");
//
//		}
//		System.out.println();
//		for (int i = 0; i < inputs.length; i++) {
//			System.out.print("(" + inputs[i].getX() + ", " + inputs[i].getY() + "), ");
//
//		}
//	}

	static Point2D.Double[] sortX(Point2D.Double[] neurons) {
		Double[] temp = new Double[neurons.length];
		for (int i = 0; i < neurons.length; i++) {
			temp[i] = neurons[i].getX();
		}
		Arrays.sort(temp);
		for (int i = 0; i < neurons.length; i++) {
			neurons[i].setLocation(temp[i], 0);
		}
		return neurons;
	}

	public static void updateLerningRate(int itr) {
		learningRate*=Math.pow((Math.E),(itr*-1)/Points.maxITR);
	}
}