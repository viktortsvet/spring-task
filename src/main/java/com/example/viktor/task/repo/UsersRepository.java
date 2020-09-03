package com.example.viktor.task.repo;

import com.example.viktor.task.models.Users;
import org.springframework.data.repository.CrudRepository;


//создаём интерфейс-наследник, с помощью которого можно добавлять, удалять, изменять или получать данные из таблицы

public interface UsersRepository extends CrudRepository<Users, Long> {
}
