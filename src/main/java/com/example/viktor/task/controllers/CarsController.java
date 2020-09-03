package com.example.viktor.task.controllers;


import com.example.viktor.task.models.Cars;
import com.example.viktor.task.repo.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


//Контролер для работы с url-адресами

@Controller
public class CarsController {

    @Autowired   // - создание переменной, связанной с созданым репозиторием
    private CarsRepository carsRepository;

    @GetMapping("/cars")  // - сам url
    public String cars(Model model) {
        Iterable<Cars> cars = carsRepository.findAll();
        model.addAttribute("cars", cars);
        return "cars";   // - возврат соответствующего html-шаблона
    }


    @GetMapping("/cars/{id}")
    public String carDetails(@PathVariable(value = "id") long id, Model model) {    //получение динамического параметра из URL (id в данном случае)
        if (!carsRepository.existsById(id)) {    // - проверка, была ли найдена запись по определённому айди
            return "redirect:/cars";
        }

        Optional<Cars> car = carsRepository.findById(id);
        ArrayList<Cars> res = new ArrayList<>();
        car.ifPresent(res::add);    //приводим объект Optional к объекту ArrayList
        model.addAttribute("car", res);
        return "carsDetails";
    }


    @GetMapping("/cars/add")
    public String carAdd(Model model) {
        return "carsAdd";
    }

    //добавление автомобиля в таблицу

    @PostMapping("/cars/add")
    public String carAdd(@RequestParam String carModel, @RequestParam String ownerName, @RequestParam String ownerSurname, @RequestParam int price, Model model) {     // - получение введённых данных из формы
        Cars car = new Cars(carModel, ownerName, ownerSurname, price);    //сохранение автомобиля в таблице
        carsRepository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}/edit")
    public String carEdit(@PathVariable(value = "id") long id, Model model) {  //редактирование существующего автомобиля и проверка его наличия по айди
        if (!carsRepository.existsById(id)) {
            return "redirect:/cars";
        }

        Optional<Cars> car = carsRepository.findById(id);
        ArrayList<Cars> res = new ArrayList<>();
        car.ifPresent(res::add);
        model.addAttribute("car", res);
        return "carsEdit";
    }

    @PostMapping("/cars/{id}/edit")
    public String carEdit(@PathVariable(value = "id") long id, @RequestParam String carModel, @RequestParam String ownerName, @RequestParam String ownerSurname, @RequestParam int price, Model model) {
        Cars car = carsRepository.findById(id).orElseThrow();    //проверка, существует ли запись по заданному айди
        car.setCarModel(carModel);                      //установка новых значений для записи с помощью сеттеров
        car.setOwnerName(ownerName);
        car.setOwnerSurname(ownerSurname);
        carsRepository.save(car);
        return "redirect:/cars";
    }

    @PostMapping("/cars/{id}/remove")            //удаление записи по заданному айди
    public String carRemove(@PathVariable(value = "id") long id, Model model) {
        Cars car = carsRepository.findById(id).orElseThrow();
        carsRepository.delete(car);
        return "redirect:/cars";
    }

}
