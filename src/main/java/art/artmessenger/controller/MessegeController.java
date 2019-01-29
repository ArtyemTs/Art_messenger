package art.artmessenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message") //Все обращения начинающиеся с этго перенаправляются на этот контроллер
public class MessegeController {

    // 2.
    // Приложение возвращает список предопределенных сообщений
    public List<Map<String, String>> masseges = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>(){{put("id", "1"); put("text", "First massage");}});
        add(new HashMap<String, String>(){{put("id", "2"); put("text", "Second massage");}});
        add(new HashMap<String, String>(){{put("id", "3"); put("text", "Third massage");}});
    }};

    @GetMapping
    public List<Map<String, String>> list (){
        return masseges;    //А при запросе на http://localhost:8080/message на страничку будет возвращаться список сообщений в JSON
    }

    // 3.
    // 

//  1.
// @GetMapping
//    public String list(){
//        return "index";    //А при запросе на http://localhost:8080/message на страничку будет возвращаться index
//    }
}
