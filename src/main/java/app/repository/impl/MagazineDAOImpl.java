package app.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import app.model.Comment;
import app.model.Magazine;
import app.repository.MagazineDAO;
import java.util.List;

@Repository
public class MagazineDAOImpl implements MagazineDAO {
    private EntityManager entityManager;


    @Autowired
    public MagazineDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }



    @Override
    public void savePDF(Magazine magazine) {
        Session session =  entityManager.unwrap(Session.class);
        session.save(magazine);
    }

    @Override
    public List<Magazine> getAllPDF() {
        List<Magazine> magazines;
        Session session =  entityManager.unwrap(Session.class);
        Query<Magazine> query = session.createQuery("from Magazine",
                    Magazine.class);
        magazines = query.getResultList();
        return magazines;
    }

    @Override
    public Magazine getPDFbyId(int id) {
        Session session =  entityManager.unwrap(Session.class);
        Magazine magazine = session.get(Magazine.class, id);
        return magazine;
    }


    @Override
    public void deletePDFById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery =
                currentSession.createQuery(
                        "delete from Magazine where id=:magId");
        theQuery.setParameter("magId", id);
        theQuery.executeUpdate();
    }

    @Override
    public void addComment(int id, Comment comment) {
        Session currentSession = entityManager.unwrap(Session.class);
        Magazine magazine = currentSession.get(Magazine.class, id);
        magazine.add(comment);
        currentSession.save(magazine);
    }

    @Override
    public List<Comment> getComments(int magazineId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery(
                        "from Comment where id_magazine=:magazineId", Comment.class);
        theQuery.setParameter("magazineId", magazineId);
        List<Comment> result = theQuery.list();
        result.forEach(next -> {
            System.out.println(next);
        });
        return result;
    }

}
