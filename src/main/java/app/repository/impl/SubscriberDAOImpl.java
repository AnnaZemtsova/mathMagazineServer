package app.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import app.model.Subscriber;
import app.repository.SubscriberDAO;
import java.util.List;

@Repository
public class SubscriberDAOImpl implements SubscriberDAO {

    private EntityManager entityManager;


    @Autowired
    public SubscriberDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public void subscribe(String name, String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(new Subscriber(name, email));
    }

    @Override
    public List<Subscriber> getEmails() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Subscriber> theQuery =
                currentSession.createQuery(
                        "FROM Subscriber");
        List<Subscriber> result = theQuery.list();
        return result;
    }
}
