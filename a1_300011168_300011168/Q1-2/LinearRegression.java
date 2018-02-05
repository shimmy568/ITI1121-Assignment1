/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent with 1 variable.
 *
 * @author Owen Anderson
 * Student number: 300011168
 * Course: ITI 1121-A
 * Assignment: 1
 * Question: 1.2
 *
 */

public class LinearRegression {


	/** 
     * Number of samples (usually "m" in litterature) 
     */
	private int nbreOfSamples;

	/** 
     * the sample vector
     */
	private double[] samples;

	/** 
     * the samples target values
     */
	private double[] samplesValues;

	/** 
     * the current hypthesis function: theta0 + theta1 x
     */
	private double theta0, theta1;


	/** 
     * used to ensure that the object is ready
     */
	private int currentNbreOfSamples;



	/** 
     * counts how many iterations have been performed
     */
	private int iteration;


	/** 
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is y = 0;
     * 
     * 
     * @param m the number of samples that we will have
	 *
     */
 	public LinearRegression(int m){
        this.nbreOfSamples = m;
        this.samples = new double[m];
        this.samplesValues = new double[m];
	}

	/** 
     * Adds a new sample to sample and to samplesValues. This
     * method must be iteratively called with all the samples
     * before the gradient descent can be started
     * 
     * @param x the new sample
     * @param y the corresponding expected value
     *
	 */
	public void addSample(double x, double y){
        this.samples[this.currentNbreOfSamples] = x;
        this.samplesValues[this.currentNbreOfSamples] = y;
        this.currentNbreOfSamples++;
	}

	/** 
     * Returns the current hypothesis for the value of the input
     * @param x the input for which we want the current hypothesis
     * 
	 * @return theta0 + theta1 x
	 */
	private double hypothesis(double x){
		return this.theta0 + this.theta1 * x;
	}

	/** 
     * Returns a string representation of hypthesis function
     * 
	 * @return the string "theta0 + theta1 x"
	 */
	public String currentHypothesis(){
        return this.theta0 + "+" + this.theta1 + "x";
	}

	/** 
     * Returns the current cost
     * 
	 * @return the current value of the cost function
	 */
	public double currentCost(){
		double total = 0;

        for(int i = 0; i < this.nbreOfSamples; i++){
            double value = this.hypothesis(this.samples[i]);
            total += Math.pow(value - this.samplesValues[i], 2);
        }

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

			// Do the sum part of each gradient decent calculation 
			double total0 = 0;
			double total1 = 0;
			for(int o = 0; o < this.samples.length; o++){
				total0 += (this.hypothesis(this.samples[o]) - this.samplesValues[o]);
				total1 += (this.hypothesis(this.samples[o]) - this.samplesValues[o]) * this.samples[o];
			}

			// divide both by m
			total0 /= this.nbreOfSamples;
			total1 /= this.nbreOfSamples;

			// And then multiply them by 2 (from 2 over m) and alpha
			this.theta0 -= 2 * alpha * total0;
			this.theta1 -= 2 * alpha * total1;

		}
		
		// Track how many iterations we just did
		this.iteration += numberOfSteps;
	}



	/** 
     * Getter for theta0
     *
	 * @return theta0
	 */

	public double getTheta0(){
		return this.theta0;
	}

	/** 
     * Getter for theta1
     *
	 * @return theta1
	 */

	public double getTheta1(){
		return this.theta1;
	}

	/** 
     * Getter for samples
     *
	 * @return samples
	 */

	public double[] getSamples(){
		return this.samples;
	}

	/** 
     * Getter for getSamplesValues
     *
	 * @return getSamplesValues
	 */

	public double[] getSamplesValues(){
		return this.samplesValues;
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
