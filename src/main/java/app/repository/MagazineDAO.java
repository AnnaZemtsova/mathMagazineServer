package app.repository;

import app.model.Comment;
import app.model.Magazine;
import java.util.List;

public interface MagazineDAO {
    public List<Magazine> getAllPDF();

    public Magazine getPDFbyId(int id);

    public void savePDF(Magazine magazine);

    public void deletePDFById(int id);

    public void addComment(int id, Comment comment);

    public List<Comment> getComments(int magazineId);

}
