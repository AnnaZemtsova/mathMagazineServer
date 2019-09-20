package app.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Comment;
import app.model.Magazine;
import app.repository.MagazineDAO;
import app.service.MagazineService;
import java.util.List;

@Service
public class MagazineServiceImpl implements MagazineService
{

    private MagazineDAO magazineDAO;

    @Autowired
    public MagazineServiceImpl(MagazineDAO magazineDAO)
    {
        this.magazineDAO = magazineDAO;
    }

    @Override
    @Transactional
    public List<Magazine> getAllPDF() {
        List<Magazine> magazines = magazineDAO.getAllPDF();
        return magazines;
    }

    @Override
    @Transactional
    public Magazine getPDFbyId(int id) {
        Magazine magazine = magazineDAO.getPDFbyId(id);
        return magazine;
    }

    @Override
    @Transactional
    public void addComment(int id, Comment comment){
        magazineDAO.addComment(id, comment);
    }

    @Override
    @Transactional
    public List<Comment> getComments(int magazineId) {
        return magazineDAO.getComments(magazineId);
    }

    @Override
    @Transactional
    public void savePDF(Magazine magazine) {
        magazineDAO.savePDF(magazine);
    }

    @Override
    @Transactional
    public void deletePDFById(int id) {
        magazineDAO.deletePDFById(id);
    }
}
