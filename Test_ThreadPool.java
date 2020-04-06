package ga;


public class Test_ThreadPool extends junit.framework.TestCase {

	/** test constructor and getters */
	public void test_constructor(){
		ThreadPool tp = new ThreadPool();
		assertTrue(tp.getPoolsize()==8);
		assertTrue(tp.getQueue().isEmpty());
		assertTrue(tp.getThreads().length==8);

	}
	
	/** test execute */
	public void test_execute() {
		ThreadPool tp = new ThreadPool();
		
		class testTask implements Runnable {
			@Override
			public void run() {

				for (int i = 0 ; i < 10 ; i++) {
					System.out.print(i);
				}

			}
		}
		
		// the first thread should take the first task and run it
		tp.execute(new testTask());
		assertTrue(tp.getThreads()[0].getState()!=Thread.State.WAITING);
		assertTrue(tp.getThreads()[1].getState()==Thread.State.WAITING);
		
		// the second thread should take the second task and run it
		tp.execute(new testTask());
		assertTrue(tp.getThreads()[1].getState()!=Thread.State.WAITING);
		assertTrue(tp.getThreads()[2].getState()==Thread.State.WAITING);
		
		// wait until those tasks are done
		while(!tp.isFinished()||!tp.isWaiting()) {
			continue;
		}
		
		System.out.println();
		System.out.println("This is supposed to be not in order: ");
		ThreadPool tp1 = new ThreadPool();
		for (int i = 0 ; i < 10 ; i++) {
			tp1.execute(new testTask());		
		}
		// we can also see that they are not printed in order, which means 
		// there are multithreads printing concurrently
		
		// wait until those tasks are done
		while(!tp1.isFinished()||!tp1.isWaiting()) {
			continue;
		}
		
	}
	
	/** test isWaiting and isFinished */
	public void test_booleans() {
		ThreadPool tp = new ThreadPool();
		assertTrue(tp.isWaiting());
		assertTrue(tp.isFinished());
		
		class testTask implements Runnable {
			@Override
			public void run() {

				for (int i = 0 ; i < 10 ; i++) {
					System.out.print(i);
				}

			}
		}
		
		tp.execute(new testTask());
		assertTrue(!tp.isWaiting());
		assertTrue(!tp.isFinished());
	}
	
	
	
}
