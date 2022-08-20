package entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

//підключення бібліотек
@Data
@Entity
@Table(name="tbl_authors")


public class Author {
    //створення структури таблиці з анотаціями для бази даних
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(length = 200, nullable = false)
    private String fullName;
    @Column(length = 200)
    private String image;
    @OneToMany(mappedBy = "author")
    private List<Books> books;
}
