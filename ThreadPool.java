package ga;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ThreadPool class that works as a pool of thread and takes a list of task, each time a 
 * task is added to the list, it will notify the next avaliable thread to run the task
 */
public class ThreadPool {
	/**
	 *  set the number of threads to number of avaliable processors on this machine
	 */
	static int poolSize = Runtime.getRuntime().availableProcessors(); 

    /**
     * an array of thread in which we will put threads
     */
    protected MyThread[] workers;
    
    /**
     * a first in first out queue to store the tasks
     */
    protected LinkedBlockingQueue<Runnable> queue;
 
    /**
     * Constructor that create a threadPool with NumProcessors = processors of the machine
     * and turn them on
     */
    public ThreadPool() 
    {
        queue = new LinkedBlockingQueue<Runnable>();
        workers = new MyThread[poolSize];
 
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new MyThread(this);
            workers[i].start();
        }
        //test_constructor
    }
 
    /**
     * Method that excute a task by putting it in the linkedblokingqueue of tasks and 
     * notify avaliable thread to process it
     * 
     * @param task
     */
    public synchronized void execute(Runnable task) {
        synchronized (queue) {
        	queue.add(task);
        	queue.notify();
        }
        //test_execute
    }
    
    /**
     * Method that returns whether the tasks are finished
     * 
     * @return boolean whether the tasks are finished
     */
    public boolean isFinished() {
    	return queue.isEmpty();
    	//test_booleans
    }
    
    /**
     * Method that returns whether any thread is at the waiting state
     * 
     * @return boolean whether any thread is waiting state
     */
    public boolean isWaiting() {
    	for (MyThread thread : workers) {
    		if(!(thread.getState() == Thread.State.WAITING)) {
    			return false;	
    		}
    	}
    	return true;
    	//test_booleans
    }
    
    /**
     * Method that shuts down the thread pool
     */
    public void shutdown() {
        System.out.println("Shutting down thread pool");
        for (int i = 0; i < poolSize; i++) {
            workers[i] = null;
        }
    }

    /**
     * Method that returns the number of threads in the thread pool
     * 
     * @return poolSize
     */
    public int getPoolsize() {
    	return poolSize;
    }
    
    /**
     * Method that returns the queue in the thread pool
     * 
     * @return queue
     */
    public LinkedBlockingQueue<Runnable> getQueue() {
    	return queue;
    }
    
    /**
     * Method that returns the threadlist in the thread pool
     * 
     * @return queue
     */
    public MyThread[] getThreads() {
    	return workers;
    }
}
	    


