import java.io.IOException;

public class BlockingQueueDemo {
    public static void main(String[] args)
    {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);
        //producer thread
        Thread producer = new Thread(()->{
            try
            {
                for(int i=1;i<10;i++){
                    System.out.println("Producing :"+i);
                    blockingQueue.put(i);
                    Thread.sleep(100);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        //consumer Thread
        Thread consumer = new Thread(()-> {
       try
       {
           for(int i=0;i<10;i++){
               int item= blockingQueue.take();
               System.out.println("Consumer: "+item);
               Thread.sleep(100);
           }
       } catch (InterruptedException e){
           Thread.currentThread().interrupt();

       }

        });

        producer.start();;
        consumer.start();

    }
}
