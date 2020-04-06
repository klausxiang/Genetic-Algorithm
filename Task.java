package ga;

/**
 * Task class that implements Runnable, and run() method compute the fitness of
 * given chromosome with the given circle
 */
public class Task implements Runnable {
	/**
	 * fields that indicates for which Chromosome we need to compute fitness
	 * and using which circle in the process of computation
	 */
	protected Cr _cr;
	protected Circle _circle;
	
	/**
	 * Constructor that creates a new task with specified Cr and Circle
	 * @param cr
	 * @param circle
	 */
	public Task(Cr cr, Circle circle) {
		_cr = cr;
		_circle  = circle;
	}

	/**
	 * Method that compute the fitness (what each task supposed to do) for each Cr
	 * and set the new fitness to the Chromosome
	 */
	@Override
	public  void run() {
		int values[] = _cr.getValues();
		double fitness = _circle.fitness( values );
		_cr.setFitness( fitness );
	}
}
