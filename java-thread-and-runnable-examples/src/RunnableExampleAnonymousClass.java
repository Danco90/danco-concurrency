/**
 * Created by rajeevkumarsingh on 08/05/17.
 */
public class RunnableExampleAnonymousClass {

    public static void main(String[] args) {
        System.out.println("Inside main thread: " + Thread.currentThread().getName());

        System.out.println("Creating Runnable ...");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside thread: " + Thread.currentThread().getName());
            }
        };

        System.out.println("Creating Thread...");
        Thread thread = new Thread(runnable);

        System.out.println("Starting Thread...");
        thread.start();
    }
}
