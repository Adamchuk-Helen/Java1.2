package entities;

import javax.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name="tbl_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 250, nullable = false)
    private String name;

}
