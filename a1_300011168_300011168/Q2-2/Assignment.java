/**
 * The class  <b>Assignment</b> is used to
 * test our LinearRegression class. 
 *
 * @author Owen Anderson
 * Student number: 300011168
 * Course: ITI 1121-A
 * Assignment: 1
 * Question: 2.2
 *
 */

public class Assignment {


	/** 
     * Random generator 
     */
	private static java.util.Random generator = new java.util.Random();

    /** 
     * In this first method, we are simply using sample points that are
     * on a straight plane. We will use the plane z= x + 2x.
     * In his method, 
     * 	1) we create an instance of LinearRegression.
     * 	2) we add 2,000 samples from the plane z= x + 2x as follows:
     * 		add the sample [(i, 2i), 5i] for 0<=i<=999
     * 		add the sample [(2i, i), 4i] for 0<=i<=999
     *  3) we iterate gradient descent 10,000, printing out the
     * current hypothesis and the current cost every 1,000 
     * iterations, using a step alpha of 0.000000003
     */
    private static void setPlane(){

        // Add samples
        LinearRegression l = new LinearRegression(2, 2000);
		for(int i = 0; i < 1000; i++){
            l.addSample(new double[]{i, 2d * i}, 5d * i);
            l.addSample(new double[]{2d * i, i}, 4d * i);
        }

        for(int i = 0; i <= 10; i++){
            System.out.println("Current Hyptothesis: " + l.currentHypothesis());
            System.out.println("Current Cost: " + l.currentCost());
            l.gradientDescent(0.000000003, 1000);
        }

	}

	/** 
     * In this second method, we will select a plane at random.
     * 	1) we select a line z = ax + by + c, with a, b and c
     * randomly selected between -100 and +100
     * 	2) we add 5000 samples randomly selected on the plane
     * with x and y both randomly selected between 50 and 4000.
     * For each sample we add a "noise" 
     * randomly selected between -20 and +20 (that is, for
     * each randomly selected x and y we add the sample 
     * [(x,y), ax+by+c+noise).
     * where "noise" is randomly selected between -20 and 20
     * 4) we iterate gradient descent (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     */

	private static void randomPlane(){

        int a = generator.nextInt(201) - 100;
        int b = generator.nextInt(201) - 100;
        int c = generator.nextInt(201) - 100;
        
        LinearRegression l = new LinearRegression(2, 5000);
        for(int i = 0; i < 5000; i++){
            int x = generator.nextInt(4000 - 50) + 50;
            int y = generator.nextInt(4000 - 50) + 50;
            int value = a * x + b * y + c + generator.nextInt(41) - 20;
            l.addSample(new double[]{x, y}, value);
        }

        for(int i = 0; i <= 600; i += 200){
            System.out.println("Current Hyptothesis: " + l.currentHypothesis());
            System.out.println("Current Cost: " + l.currentCost());
            System.out.println("Aiming For: " + generateAim(new int[]{c, a, b}) + "\n");
            l.gradientDescent(0.00000001, 200);
        }
	}


    /**
     * Generates the aim string for the output 
     * 
     * @param vals - The values to print along with the x_n's with the constant as the first value
     */
    private static String generateAim(int[] vals){
        String hypo = "";
		for(int i = 0; i < vals.length; i++){
            hypo += vals[i];

            if(i > 0){
                hypo += " x_" + i;
            }

			// Add the plus sign on all iterations except the last one
			if(i < vals.length - 1){
				hypo += " + ";
			}
		}
		return hypo;
    }

	public static void main(String[] args) {

		StudentInfo.display();

		System.out.println("randomPlane");
		randomPlane();

	}

}
