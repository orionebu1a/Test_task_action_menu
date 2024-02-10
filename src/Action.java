import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Action {
    private String description;
    private LocalDateTime date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Action(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[]" +
                description + " - " +
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
