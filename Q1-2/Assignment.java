/**
 * The class  <b>Assignment</b> is used to
 * test our LinearRegression class. It uses the
 * provided class <b>Display</b> to show the results
 *
 * @author Owen Anderson
 * Student number: 300011168
 * Course: ITI 1121-A
 * Assignment: 1
 * Question: 1.2
 *
 */

public class Assignment {


	/** 
     * Random generator 
     */
	private static java.util.Random generator = new java.util.Random();

		/** 
     * In this second method, we will select a line at random.
     * 	1) we select a line y = ax + b, with a randomly selected
     * between -100 and +100 and b randomly selected between 
     * -250 and +250
     * 	2) we add 500 samples randomly selected on the line
     * between -100 and +300. For each sample we add a "noise" 
     * randomly selected between -1000 and +1000 (that is, for
     * each randomly selected x, we add the sample (x, ax+b+noise).
     * where "noise" is randomly selected between -1000 and 1000
     *  3) We create an instance of Display
     *  4) we iterate gradient descent (find a number of iterations,
     * a number of updates to the instance of Display, and a 
     * step alpha that seems to work
     */
	private static void randomLine(){

        int a = generator.nextInt(201) - 100;
        int b = generator.nextInt(501) - 250;

        LinearRegression l = new LinearRegression(500);

		// Add samples
        for(int i = 0; i < 500; i++){
            int x = generator.nextInt(401) - 100;
            int num = a * x + b;
            int noise = generator.nextInt(2001) - 1000;
            l.addSample(x, num + noise);
        }

        Display d = new Display(l);
        d.setTarget(a, b);

        for(int i = 0; i <= 10000; i += 1000){
            System.out.println("Current Hyptothesis: " + l.currentHypothesis());
            System.out.println("Current Cost: " + l.currentCost());
            System.out.println("Aiming for: " + b + "+" + a + "x");
            d.update();
            l.gradientDescent(0.000041, 5000);
        }
	}


	public static void main(String[] args) {

	    //StudentInfo.display();

		System.out.println("randomLine");
		randomLine();

	}

}
