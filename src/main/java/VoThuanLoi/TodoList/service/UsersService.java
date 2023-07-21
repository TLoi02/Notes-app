package VoThuanLoi.TodoList.service;

import VoThuanLoi.TodoList.entity.Users;
import VoThuanLoi.TodoList.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepo;

    public Users getUserByMail(String email){
        return usersRepo.findByEmail(email);
    }
    public void Add(Users users){
        usersRepo.save(users);
    }
}
