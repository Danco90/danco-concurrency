import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public int incrementAndGet() {
        return count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}

public class AtomicIntegerExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        AtomicCounter atomicCounter = new AtomicCounter();

        for(int i = 0; i < 10; i++) {
//        	System.out.print("Submitting task(incrementAndGet) to thread pool..");
            executorService.submit(() -> { atomicCounter.incrementAndGet();
            		System.out.println("task submited to thread="+Thread.currentThread());
            		
            	}
            );
            
        }

        executorService.shutdown();
        executorService.awaitTermination(6, TimeUnit.SECONDS);

        System.out.println("ExecutorService. Final Count is : " + atomicCounter.getCount());
        
        ScheduledExecutorService scheduledexecService = Executors.newSingleThreadScheduledExecutor();

        AtomicCounter atomicCounter2 = new AtomicCounter();
        
        for(int i = 0; i < 10; i++) {
	       /* ScheduledFuture<Integer> future = */scheduledexecService.scheduleWithFixedDelay(() -> {
	           
	        	/*return */atomicCounter2.incrementAndGet();
	        	System.out.println("Runnable count ="+atomicCounter2.getCount());
	        }, 1, 5,TimeUnit.SECONDS);
	        
//	        if (future.isDone() && !future.isCancelled()) {
//	            try {
//	                System.out.println("Callable interface counter :" +future.get());
//	            } catch (InterruptedException | ExecutionException e) {
//	                e.printStackTrace();
//	            }
//	        }
        }
        
        scheduledexecService.shutdown();
//        scheduledexecService.awaitTermination(6, TimeUnit.SECONDS);

        System.out.println("ScheduledExecutorService. Final Count2 is : " + atomicCounter2.getCount());
//        
//        AtomicCounter atomicCounter3 = new AtomicCounter();
//        ScheduledFuture<?> scheduledFuture = scheduledexecService.schedule(() -> {
//            // ...
//        	System.out.println("Runnable interface");
//        	atomicCounter3.incrementAndGet();
////            return "Hello world";
//        }, 1, TimeUnit.SECONDS);
//
//
//        scheduledexecService.shutdown();
//        scheduledexecService.awaitTermination(6, TimeUnit.SECONDS);
//
//        System.out.println("ExecutorService. Final Count3 is : " + atomicCounter2.getCount());
//        
        
    }
}
