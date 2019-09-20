package app.service;

import app.model.Magazine;
import app.model.Subscriber;
import java.util.List;

public interface MailService {
    public void sendEmail(Magazine magazine, List<Subscriber> subscribers);
}
