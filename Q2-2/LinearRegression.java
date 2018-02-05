/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent for multiple variables
 *
 * @author Owen Anderson
 * Student number: 300011168
 * Course: ITI 1121-A
 * Assignment: 1
 * Question: 2.2
 *
 */

public class LinearRegression{


	/** 
     * Number of features (usually "n" in litterature) 
     */
	private int nbreOfFeatures;

	/** 
     * Number of samples (usually "m" in litterature) 
     */
	private int nbreOfSamples;


	/** 
     * the nbreOfFeatures X nbreOfSamples Matrix of samples
     */
	private double[][] samplesMatrix;

	/** 
     * the nbreOfSamples Matrix of samples target values
     */
	private double[] samplesValues;

	/** 
     * the nbreOfFeatures Matrix theta of current hypthesis function
     */
	private double[] theta;


	/** 
     * number of samples received so far
     */
	private int currentNbreOfSamples;

	/** 
     * a place holder for theta during descent calculation
     */
	private double[] tempTheta;


	/** 
     * counts how many iterations have been performed
     */
	private int iteration;


	/** 
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is theta[i]=0.0 for all i
     * 
     * @param n the number of features that we will have
     * @param m the number of samples that we will have
	 *
     */
 	public LinearRegression(int n, int m){

		n++;
		// Initilize all the arrays based on num of features and samples
		this.nbreOfFeatures = n;
		this.nbreOfSamples = m;
		this.samplesMatrix = new double[m][n];
		this.samplesValues = new double[m];
		this.theta = new double[n];
		this.tempTheta = new double[n];
		
		// Add a 1 to the first column of samples matrix
		for(int i = 0; i < m; i++){
			this.samplesMatrix[i][0] = 1;
		}
	}

	/** 
     * Add a new sample to samplesMatrix and samplesValues
     * 
     * @param x the vectors of samples
     * @param y the coresponding expected value
     *
	 */
	public void addSample(double[] x, double y){

        for(int i = 0; i < this.nbreOfFeatures - 1; i++){
            this.samplesMatrix[this.currentNbreOfSamples][i + 1] = x[i];
        }
		this.samplesValues[this.currentNbreOfSamples] = y;
		this.currentNbreOfSamples++;

	}

	/** 
     * Returns the current hypothesis for the value of the input
     * @param x the input vector for which we want the current hypothesis
     * 
	 * @return h(x)
	 */

	private double hypothesis(double[] x){

        double total = this.theta[0];
		for(int i = 1; i < this.nbreOfFeatures; i++){
			total += this.theta[i] * x[i];
        }
        
		return total;
	}

	/** 
     * Returns a string representation of hypthesis function
     * 
	 * @return the string "theta0 x_0 + theta1 x_1 + .. thetan x_n"
	 */

	public String currentHypothesis(){

		String hypo = "";
		for(int i = 0; i < this.nbreOfFeatures; i++){
            hypo += this.theta[i];

            if(i > 0){
                hypo += " x_" + i;
            }

			// Add the plus sign on all iterations except the last one
			if(i < this.theta.length - 1){
				hypo += " + ";
			}
		}
		return hypo;
	}

	/** 
     * Returns the current cost
     * 
	 * @return the current value of the cost function
	 */

	public double currentCost(){

		// Add up all the diffrences between the hypothesis and the actual value
		double total = 0;
		for(int i = 0; i < this.nbreOfSamples; i++){
			total += Math.pow(this.hypothesis(this.samplesMatrix[i]) - this.samplesValues[i], 2);
		}

		// divide by number of samples
		return total / this.nbreOfSamples;
	}

	/** 
     * runs several iterations of the gradient descent algorithm
     * 
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */

	public void gradientDescent(double alpha, int numberOfSteps) {

		for(int i = 0; i < numberOfSteps; i++){
			this.singleGradDescent(alpha);
		}
		this.iteration += numberOfSteps;
	}
	
	/**
	 * Runs a single iteration of gradien descent
	 * 
	 * @param alpha the learning rate
	 */
	private void singleGradDescent(double alpha){

        for(int i = 0; i < this.nbreOfFeatures; i++){
            this.tempTheta[i] = 0;
        }

		// Do the sum part of the equation
		for(int i = 0; i < this.nbreOfSamples; i++){

			// Calculate the diff which will be the same for all features
			double diff = this.hypothesis(this.samplesMatrix[i]) - this.samplesValues[i];

			// Multiply by the x sample which is diffrent for eath feature so has to be done
			// in a sepperate for loop
			for(int o = 0; o < this.nbreOfFeatures; o++){
				this.tempTheta[o] += diff * this.samplesMatrix[i][o];
			}			
		}

		// Update the theta values
		for(int i = 0; i < this.nbreOfFeatures; i++){
			this.theta[i] -= (2 * alpha / this.nbreOfSamples) * this.tempTheta[i];
		}
	}


	/** 
     * Getter for theta
     *
	 * @return theta
	 */

	public double[] getTheta(){

		return this.theta;

	}

	/** 
     * Getter for iteration
     *
	 * @return iteration
	 */

	public int getIteration(){

		return this.iteration;

	}
}
