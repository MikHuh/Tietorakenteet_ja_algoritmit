package oy.tol.tra;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DailyTasks {

   // private QueueInterface<String> dailyTaskQueue = null; Didn't work because
   // dailyTaskQueue can't be null
   private Timer timer = null;
   private static final int MAX_DAILY_TASKS = 100;
   private static final int TASK_DELAY_IN_SECONDS = 2 * 1000;
   QueueImplementation<String> dailyTaskQueue = new QueueImplementation<>();

   // Execute from the command line: java -jar
   // target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {

         dailyTaskQueue.init(String.class, MAX_DAILY_TASKS);
         readTasks();
         Timer timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               if (dailyTaskQueue.empty()) {
                  timer.cancel();
               } else {
                  System.out.println(dailyTaskQueue.dequeue());
               }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
         // 1. create a queue for daily tasks, which are strings.
         // 2. initialize the task queue with String.class and max number of daily tasks.
         // 3. read the tasks for today using readTasks()
         // 4. create Java Timer object to schedule your daily tasks.
         // 5. schedule the timer at fixed rate with a TimerTask,
         // using the delay values in the class member variable.
         // 5.1 in the timer task run:
         // 5.1.1 check if there are tasks in the queue
         // 5.1.2 if yes, print the task from the queue, removing it
         // 5.1.3 if not, cancel the timer.
         // 5. watch your day going by.

      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      }
   }

   private void readTasks() throws IOException {
      String tasks;
      tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
      String[] allTasks = tasks.split("\\r?\\n");
      int counter = 0;
      for (String task : allTasks) {
         dailyTaskQueue.enqueue(task);
         if (counter >= MAX_DAILY_TASKS) {
            System.out.println("Number of tasks: " + counter);
            break;
         }
      }
      System.out.println("Number of tasks: " + counter);
   }
}
