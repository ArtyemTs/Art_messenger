package art.artmessenger.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    // Данные которые будут приходить с Гугла
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    // Создадим сами
    private LocalDateTime lastVisit;
}
