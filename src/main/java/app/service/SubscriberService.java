package app.service;

import app.model.Subscriber;
import java.util.List;

public interface SubscriberService {

    public void subscribe(String name, String email);
    public List<Subscriber> getEmails();
}
