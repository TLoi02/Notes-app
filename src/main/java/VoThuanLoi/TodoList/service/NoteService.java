package VoThuanLoi.TodoList.service;

import VoThuanLoi.TodoList.entity.Note;
import VoThuanLoi.TodoList.entity.Users;
import VoThuanLoi.TodoList.repository.NoteRepository;
import VoThuanLoi.TodoList.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepo;
    @Autowired
    private UsersRepository usersRepo;

    public List<Note> getListNoteWithEmail(String email){
        //Get user login
        Users getUser = usersRepo.findByEmail(email);
        return noteRepo.findByUsers(getUser);
    }

    public void addNote(Note note){
        noteRepo.save(note);
    }

    public Note getNoteById(Long id){
        return noteRepo.findById(id).orElse(null);
    }

    public void deleteNote(Long id){
        noteRepo.deleteById(id);
    }
}
