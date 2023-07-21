package VoThuanLoi.TodoList.controller;

import VoThuanLoi.TodoList.entity.Note;
import VoThuanLoi.TodoList.entity.Type;
import VoThuanLoi.TodoList.entity.Users;
import VoThuanLoi.TodoList.repository.UsersRepository;
import VoThuanLoi.TodoList.service.NoteService;
import VoThuanLoi.TodoList.service.TypeService;
import VoThuanLoi.TodoList.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String index(Model model,
                        @AuthenticationPrincipal OAuth2User oauth2User){
        //Get account login
        String getEmail = oauth2User.getAttribute("email");
        String getName = oauth2User.getAttribute("name");
        String getUserName = oauth2User.getAttribute("login");  //Have username when login by Github

        //Check user in database
        Users checkUsers = null;
        if (getUserName == null){
            checkUsers =  usersService.getUserByMail(getEmail);
        }
        else
            checkUsers= usersService.getUserByMail(getUserName);

        //Add user to database
        if (checkUsers == null){
            Users u = new Users();
            String email = (getUserName == null) ? getEmail : getUserName;
            u.setEmail(email);
            u.setName(getName);
            usersService.Add(u);
        }


        //Add list note with email login
        model.addAttribute("listNote", noteService.getListNoteWithEmail((getUserName == null) ? getEmail : getUserName ));
        model.addAttribute("nameUser",getName);

        return "index";
    }

    @PostMapping("/create")
    public String createNote(@Param("note") String note,
                             @AuthenticationPrincipal OAuth2User oauth2User){
        String email = (oauth2User.getAttribute("login") == null) ?
                        oauth2User.getAttribute("email") :
                        oauth2User.getAttribute("login");
        Users getUser = usersService.getUserByMail(email);
        //Users getUser = usersService.getUserByMail("vothuanloi110802@gmail.com");
        Type getType = typeService.getTypeByName("In progress");

        Note tempt = new Note();
        tempt.setContent(note);
        tempt.setUsers(getUser);
        tempt.setType(getType);

        noteService.addNote(tempt);

        return "redirect:/";
    }

    @GetMapping("/finish/{id}")
    public String finish(@PathVariable("id") Long id){
        Note getNote = noteService.getNoteById(id);
        Type getType = typeService.getTypeByName("Complete");
        getNote.setType(getType);
        noteService.addNote(getNote);

        return "redirect:/";
    }

    @GetMapping("/unfinish/{id}")
    public String unfinish(@PathVariable("id") Long id){
        Note getNote = noteService.getNoteById(id);
        Type getType = typeService.getTypeByName("In progress");
        getNote.setType(getType);
        noteService.addNote(getNote);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return "redirect:/";
    }
}
