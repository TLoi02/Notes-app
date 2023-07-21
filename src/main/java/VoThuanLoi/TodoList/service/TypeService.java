package VoThuanLoi.TodoList.service;

import VoThuanLoi.TodoList.entity.Type;
import VoThuanLoi.TodoList.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepo;

    public Type getTypeByName(String name){
        return typeRepo.findByName(name);
    }
}
