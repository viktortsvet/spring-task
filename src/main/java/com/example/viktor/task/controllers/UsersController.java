package com.example.viktor.task.controllers;


import com.example.viktor.task.models.Users;
import com.example.viktor.task.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersController {

    @Autowired   //создание переменной, связанной с репозиторием
    private UsersRepository usersRepository;


    @GetMapping("/users")
    public String users(Model model) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/add")
    public String userAdd(Model model) {
        return "userAdd";
    }

    // Добавление пользователей

    @PostMapping("/users/add")
    public String userAdd(@RequestParam String name, @RequestParam String surname, Model model) {   //достаём данные из формы
        Users user = new Users(name, surname);   //создание объекта класса пользователя и сохраняем в нем данные, полученные из формы
        usersRepository.save(user);     //сохранение нового пользователя в таблице
        return "redirect:/users";      //перенаправление на общую страницу пользователей после создания нового пользователя
    }

    @PostMapping("/users/{id}/remove")
    public String blogPostUpdate(@PathVariable(value = "id") long id, Model model) {
        Users user = usersRepository.findById(id).orElseThrow();
        usersRepository.delete(user);
        return "redirect:/users";
    }

}
