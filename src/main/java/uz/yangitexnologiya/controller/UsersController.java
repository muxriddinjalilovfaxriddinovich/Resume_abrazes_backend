package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.UsersDto;
import uz.yangitexnologiya.entity.Users;
import uz.yangitexnologiya.service.UsersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {


    @Autowired
    UsersService usersService;


    @GetMapping
    public HttpEntity<?> all(){
       List<Users> all = usersService.all();
       return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse getOneId=usersService.getOneId(id);
        return ResponseEntity.status(getOneId.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(getOneId);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody UsersDto usersDto){
        ApiResponse add= usersService.add(usersDto);
    return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(add);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @Valid @RequestBody UsersDto usersDto){
        ApiResponse edit=usersService.edit(id,usersDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet=usersService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(delet);
    }
}
