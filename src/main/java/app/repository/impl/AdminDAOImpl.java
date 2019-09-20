package app.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import app.model.Admin;
import app.repository.AdminDAO;

@Repository
public class AdminDAOImpl implements AdminDAO {
    private EntityManager entityManager;

    @Autowired
    public AdminDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public Admin singin(String login, String password) {
        Session currentSession = entityManager.unwrap(Session.class);
        Admin admin;
        Query<Admin> theQuery =
                currentSession.createQuery(
                        "from Admin where login=:login AND password=:password").
                        setParameter("login", login).setParameter("password", password);
        try {
             admin = theQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
       return admin;
    }
}
