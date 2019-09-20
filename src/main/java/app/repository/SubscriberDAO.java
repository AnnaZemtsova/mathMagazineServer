package app.repository;

import app.model.Subscriber;
import java.util.List;

public interface SubscriberDAO {
    public void subscribe(String name, String email);
    public List<Subscriber> getEmails();
}
