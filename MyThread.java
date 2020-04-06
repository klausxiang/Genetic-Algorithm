package ga;


/** 
 * MyThread class that inherit Thread class and function as a thread, its constructor takes 
 * input motherPool and its run() method is synchronized
 */
public class MyThread extends Thread {
	
    /**
     * indicates which pool this thread is belonged to
     */
	protected ThreadPool motherPool;
	
    /**
     * Constructor that initiate a Mythread object with motherPool specified
     */
	public MyThread(ThreadPool nm) {
		motherPool = nm;
	}
	
    /**
     * Method that run the next task from the queue in the motherPool
     */
    public synchronized void run() {
        Runnable task;

        while (true) {
            synchronized (motherPool.queue) {
                while (motherPool.queue.isEmpty()) {
                    try {
                    	motherPool.queue.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Error occurred while queue is waiting: " + e.getMessage());
                    }
                }
                
                // poll next task from the mother pool
				task = (Runnable) motherPool.queue.poll();
				
            }
            // run the task
            try {
                task.run();
            } catch (RuntimeException e) {
                System.out.println("Error occurred while running task: " + e.getMessage());
            }
        }
    }
}
