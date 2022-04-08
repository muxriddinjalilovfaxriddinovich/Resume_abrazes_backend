package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.InterestsDto;
import uz.yangitexnologiya.repository.InterestsRepository;
import uz.yangitexnologiya.service.InterestsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/interests")
public class InterestsController {

    @Autowired
    InterestsService interestsService;

    @GetMapping
    public HttpEntity<?> all(){
        ApiResponse all=interestsService.findAll();
        return ResponseEntity.status(all.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse oneId=interestsService.getOneId(id);
        return ResponseEntity.status(oneId.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(oneId);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody InterestsDto interestsDto){
        ApiResponse add=interestsService.add(interestsDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(add);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @Valid @RequestBody InterestsDto interestsDto){
        ApiResponse edit=interestsService.edit(id,interestsDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet=interestsService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(delet);
    }
}
