package art.artmessenger.controller;

import art.artmessenger.domain.Message;
import art.artmessenger.domain.Views;
import art.artmessenger.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message") //Все обращения начинающиеся с этго перенаправляются на этот контроллер
public class MessageController {

    // 8.
    // Подключаем БД
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

//    (-8) private int counter = 4;
//    // 2.
//    // Приложение возвращает список предопределенных сообщений
//    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>(){{
//        add(new HashMap<String, String>(){{put("id", "1"); put("text", "First massage");}});
//        add(new HashMap<String, String>(){{put("id", "2"); put("text", "Second massage");}});
//        add(new HashMap<String, String>(){{put("id", "3"); put("text", "Third massage");}});
//    }};

    @GetMapping
    @JsonView(Views.IdName.class) // См. Message
    public List<Message> list (){
        return messageRepo.findAll();
        //(-8) public List<Map<String, String>> list (){
        // return messages;    //А при запросе на http://localhost:8080/message на страничку будет возвращаться список сообщений в JSON
    }

    // 3.
    // Сождадим мэппинг для получения единичного сообщения по его id

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class) // См. Message
    public Message getOne(@PathVariable("id") Message message){
        return message;
    // (-8)public Map<String, String> getOne(@PathVariable String id){ // @PathVariable, указывающая на то, что данный параметр
                                                                // получается из адресной строки

//            return messeges.stream()
//                .filter(message -> message.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new); //Возвратите содержащееся значение, если оно присутствует, в противном случае выведите исключение, которое будет создано предоставленным поставщиком.
        // 4.
    // Создаеи свое NotFoundException

        // Код по поиску сообщений выносим в отдельный метод getMessege
       // return getMessage(id);
    }

//   (-8) private Map<String, String> getMessage(@PathVariable String id) {
//        return messages.stream()
//                .filter(message -> message.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }

    // 5.
    // Добавление объектов. PostMapping
    @PostMapping
    // Возвращает единственный инстанс Map<String, String>
    public Message create (@RequestBody Message messege) {

        messege.setCreationDate(LocalDateTime.now()); // Вывод поля с датой
        return messageRepo.save(messege);
    }
    // (-8) public Map<String, String> create (@RequestBody Map<String, String> messege) {//HTTP-запрос кроме заголовков и параметров
        // имеет также основную часть - тело запроса.
        // Её содержимое также может быть распознано как параметр в методе контроллера.
        // Для того, чтобы это произошло, необходимо указать @RequestBody в объявлении этого параметра

//        messege.put("id", String.valueOf(counter++)); // инкриментируем счетчик сообщений прежде чем отправить запрос
//
//        messages.add(messege);
//
//        return messege;
//    }

    // 6.
    // Обновление текущей записи. PutMapping
    @PutMapping("{id}")
    public Message update (@PathVariable("id") Message messageFromDB, // этот аргумент получаем через @PutMapping("{id}")
                                                                     // который передал пользователь прердал в URL
                           @RequestBody Message message) // Получим от пользователя в виде JSON который Spring разберет сам
                                                                    // и положет в объект Messagr
    {

        BeanUtils.copyProperties(message, messageFromDB, "id"); // Копирует все поля из.. в.. кроме..

        return messageRepo.save(messageFromDB);
    }
//   (-8) public Map<String, String> update (@PathVariable String id,
//                                        @RequestBody Map<String, String> message) {
//        Map<String, String> messageFromDB = getMessage(id); // Найденное сообщение помещаем в messageFromDB
//                                                            // т.к. уже образовалась небольшая БД
//        messageFromDB.putAll(message);

        // Добавляем @PathVariable String id, чтобы не затереть айдишник руками, а получать его из адр-й строки
//        messageFromDB.put("id", id);
//
//        return messageFromDB;
//    }

    // 7.
    // Запрос на удаление
    @DeleteMapping("{id}")

    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }
    //Ничего не возвращает.
    // Код 200 - удаление успешно
    // код 404 - запись не найдена
    // код 5ХХ - ошибка при  удалении
//   (-8) public void delete(@PathVariable String id){
//        Map<String, String> message = getMessage(id); // Здесь отрабатывает 404 -й
//
//        messages.remove(message);
//    }

//  1.
// @GetMapping
//    public String list(){
//        return "index";    //А при запросе на http://localhost:8080/message на страничку будет возвращаться index
//    }
 }
