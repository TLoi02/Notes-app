package VoThuanLoi.TodoList.repository;

import VoThuanLoi.TodoList.entity.Note;
import VoThuanLoi.TodoList.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUsers(Users users);
}
