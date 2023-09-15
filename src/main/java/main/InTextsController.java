package main;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InTextsController {

    //@PostMapping("/inTexts")
    @RequestMapping(value = "/inTexts", method = RequestMethod.POST)
    public String addInTexts(String inTexts) {
            return inTexts.concat("Hello, World!");
    }
}
