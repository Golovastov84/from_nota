package main;

import org.springframework.web.bind.annotation.*;

@RestController
public class InTextsController {

    //@PostMapping("/inTexts")
    @RequestMapping(value = "/inTexts", method = RequestMethod.POST)
    public String addInTexts(String inTexts) {
            return inTexts.concat("Hello, World!");
    }
}
