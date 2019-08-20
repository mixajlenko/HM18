public class EventParametrs {

    private String priority;
    private String log_level;
    private String source;

    public EventParametrs(String priority, String log_level, String source) {
        this.priority = priority;
        this.log_level = log_level;
        this.source = source;
    }

    public String getPriority() {
        return priority;
    }

    public String getLog_level() {
        return log_level;
    }

    public String getSource() {
        return source;
    }
}
