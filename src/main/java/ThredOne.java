import java.util.ArrayList;

public class ThredOne extends Thread {

   protected static ArrayList<Event> events = new ArrayList<>();
   private int counter = 1;

    public ThredOne(String name) {
        super(name);
    }
    @Override
    public void run() {
        try{
                for (Event e : events) {
                    System.out.println("DATA" + counter++);
                    System.out.println(String.format("ID: %s, DATE: %s,",
                            e.getEvent_id(),
                            e.getEvent_date()));
                    Thread.sleep(250);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
