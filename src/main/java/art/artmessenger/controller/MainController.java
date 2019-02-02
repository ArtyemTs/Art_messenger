package art.artmessenger.controller;

import art.artmessenger.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){

        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", null);

        model.addAttribute("frontendData", data);

        return "main";
    }
//    public class MainController {
//        @GetMapping
//        public String main(Model model){
//    @GetMapping
//    public String main(Model model){
//        return "main";
//    }
}
