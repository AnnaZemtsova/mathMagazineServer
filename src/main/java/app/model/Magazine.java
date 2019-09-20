package app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "magazine")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private int id;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Column(name = "content")
   private byte[] body;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "magazine", cascade = {CascadeType.ALL})
    private List<Comment> comments;

    public Magazine(){}
    public Magazine( byte[] body, String name){
        this.body = body;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void add(Comment comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setMagazine(this);
    }
    @Override
    public String toString() {
        return "{\"id"+ "\":\"" + this.id + "\"," +
                "\"body"+ "\":\""+Arrays.toString(this.body).replace("[","").
                replace("]","").replace(",","")+"\"}";


    }

}
