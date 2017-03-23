package com.ofrancois.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/addDeck")
public class AddDeckController {
 
      @RequestMapping(method = RequestMethod.GET)
        public String getIndexPage() {
            return "deckBuilder";
        }
 
}
