import java.io.IOException;
import java.util.LinkedList;

public class BlockingQueue<T> {
    private final LinkedList<T> queue;
    private final int capacity;

    public BlockingQueue(int capacity){
        if(capacity<=0)
            throw  new IllegalArgumentException("Queue capacity must be greater than zero");
        this.capacity=capacity;
        this.queue= new LinkedList<>();
    }
    //Method to add item to queue
    public synchronized void put(T item) throws IOException, InterruptedException {
        //wait until there is space in queue
        while (queue.size()==capacity){
            wait();
        }
        //Add item to queue
        queue.addLast(item);
        //notify waiting threads that an item has been added
        notifyAll();

    }
    //Method to retrieve and remove item from queue
    public synchronized T take() throws InterruptedException{
        while(queue.isEmpty()){
            wait();
        }
        //Remove item from queue
        T item =queue.removeFirst();
        notifyAll();
        return item;
    }

    //Method to get current size of the queue
    public synchronized int size(){
        return queue.size();
    }


}
