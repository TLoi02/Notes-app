package VoThuanLoi.TodoList.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name  = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String name;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Note> notes;
}
