package org.romanko.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.romako.simpleirc.Message;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/message")
public class MessageController {

//    @Autowired
//    private UserDao userDao;

	private List<Message> messages = new ArrayList<Message>(Arrays.asList(new Message("aaa")));
	
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody List<Message> getAllMessages() {
        return messages;
    }

    @RequestMapping(value ="/{text}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMessage(@PathVariable("text") String text) {
    	messages.add(new Message(text));
    }
    
    /*
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void postMessage(@RequestBody Message message) {
    	messages.add(message);
    }*/
}