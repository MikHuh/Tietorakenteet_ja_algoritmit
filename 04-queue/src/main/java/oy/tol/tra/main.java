package oy.tol.tra;

public class main {

    public static void main(String[] args){
    QueueInterface<String> queue = new QueueImplementation<>();
    queue.init(String.class, 4);
    queue.enqueue("eka");
    queue.enqueue("toka");
    queue.enqueue("kolmas");
    queue.enqueue("neljäs");
    String removed = queue.dequeue();
    System.out.println("First enqueued: " + removed);
    removed = queue.dequeue();
    System.out.println("Second enqueued: " + removed);
    queue.enqueue("viides");
    queue.enqueue("kuudes");
    queue.enqueue("seitsemäs");
    queue.enqueue("kahdeksas");
    removed = queue.dequeue();
    System.out.println("Third enqueued: " + removed);
    }
}
