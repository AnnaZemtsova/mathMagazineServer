package app.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Subscriber;
import app.repository.SubscriberDAO;
import app.service.SubscriberService;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberDAO subscriberDAO;

    @Autowired
    public SubscriberServiceImpl(SubscriberDAO subscriberDAO)
    {
        this.subscriberDAO = subscriberDAO;
    }


    @Override
    @Transactional
    public void subscribe(String name, String email) {
        subscriberDAO.subscribe(name,email);
    }

    @Override
    @Transactional
    public List<Subscriber> getEmails() {
        return subscriberDAO.getEmails();
    }
}
