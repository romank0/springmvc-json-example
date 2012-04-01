package org.romanko.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.romako.simpleirc.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {

//    @Autowired
//    private UserDao userDao;

	private List<Message> messages = new ArrayList<Message>();
	
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public
    @ResponseBody List<Message> getAllMessages() {
        return messages;
    }

    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void postMessage(@RequestBody Message message) {
    	messages.add(message);
    }
}