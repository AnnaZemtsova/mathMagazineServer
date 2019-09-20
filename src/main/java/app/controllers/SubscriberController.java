package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.model.Subscriber;
import app.service.SubscriberService;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/subscription")
public class SubscriberController {
    private SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService)
    {
        this.subscriberService = subscriberService;
    }


    @GetMapping(value = "/subscribe/{name}/{email}")
    public void  subscribe(@PathVariable String name,
                           @PathVariable String email) {
      subscriberService.subscribe(name, email);
    }

    @GetMapping(value = "/emails")
    public List<Subscriber> subscribe() {
        return subscriberService.getEmails();
    }

}
