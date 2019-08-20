public class Event {

    private String event_id;
    private String event_date;

    public Event(String event_id, String event_date) {
        this.event_id = event_id;
        this.event_date = event_date;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_date() {
        return event_date;
    }

}


