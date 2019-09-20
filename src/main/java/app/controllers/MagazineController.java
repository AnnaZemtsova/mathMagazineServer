package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Comment;
import app.model.Magazine;
import app.model.Subscriber;
import app.service.MagazineService;
import app.service.MailService;
import app.service.SubscriberService;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Content-Disposition")
@RestController
@RequestMapping("/magazine")
class MagazineController {
    private MagazineService magazineService;
    private SubscriberService subscriberService;
    private MailService mailService;

    @Autowired
    public MagazineController(MagazineService magazineService, SubscriberService subscriberService, MailService mailService) {
        this.magazineService = magazineService;
        this.subscriberService = subscriberService;
        this.mailService = mailService;
    }

        @PostMapping(value = "/upload/{filename}")
    public String create(@RequestBody byte[] user, @PathVariable String filename){
        Magazine magazine = new Magazine(user, filename);
        magazineService.savePDF(magazine);
        List<Subscriber> subscribers = subscriberService.getEmails();
        mailService.sendEmail(magazine, subscribers);
        return "{\"status\": \"OK\"}";
    }

/* @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String create(@RequestPart("file0") MultipartFile user){
      System.out.println(user);
     /* Magazine magazine = new Magazine(user, "");
      magazineService.savePDF(magazine);
      List<Subscriber> subscribers = subscriberService.getEmails();
     / mailService.sendEmail(magazine, subscribers);*/
      /*return "{\"status\": \"OK\"}";
  }*/

    @GetMapping(value = "/getMagazine/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[]  getMagazineById(@PathVariable int id) {
        Magazine magazine = magazineService.getPDFbyId(id);
        return  magazine.getBody();
    }

    @GetMapping("/getAllMagazines")
    public List<Magazine> getAllPdf() {
        return magazineService.getAllPDF();
     }

    @GetMapping("/getAmountPDF")
    public int getAmountPdf() {
        return magazineService.getAllPDF().size();
    }

    @GetMapping(value = "/addComment/{id}/{comment}")
    public String addComment(@PathVariable int id,
                             @PathVariable String comment){
        Comment tmpCom = new Comment(comment);
        magazineService.addComment(id, tmpCom);
        return "{\"status\": \"OK\"}";
    }

    @GetMapping(value = "/getComments/{magazineId}")
    public  String getComments(@PathVariable int magazineId){
        List<Comment> result = magazineService.getComments(magazineId);
        return result.toString().replace("[","{").replace("]","}");
    }

}