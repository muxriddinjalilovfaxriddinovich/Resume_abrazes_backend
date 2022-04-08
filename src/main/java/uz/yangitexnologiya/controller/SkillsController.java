package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.SkillsDto;
import uz.yangitexnologiya.service.SkillsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    SkillsService skillsService;


    @GetMapping
    public HttpEntity<?> all(){
        ApiResponse all=skillsService.all();
        return ResponseEntity.status(all.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse getOneId=skillsService.getOneId(id);
        return ResponseEntity.status(getOneId.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(getOneId);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody SkillsDto skillsDto){
        ApiResponse add=skillsService.add(skillsDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(add);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody SkillsDto skillsDto){
        ApiResponse edit=skillsService.edit(id,skillsDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(edit);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet=skillsService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(delet);
    }


}
