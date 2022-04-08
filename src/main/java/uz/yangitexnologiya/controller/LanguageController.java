package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.LanguageDto;
import uz.yangitexnologiya.service.LanguageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping
    public HttpEntity<?> all(){
        ApiResponse all= languageService.all();
        return ResponseEntity.status(all.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(all);
    }

    @GetMapping("/")
    public HttpEntity<?> getOneId(@RequestBody LanguageDto languageDto){
        ApiResponse getOneId = languageService.getOneId(languageDto);
        return ResponseEntity.status(getOneId.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(getOneId);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody LanguageDto languageDto){
        ApiResponse add=languageService.add(languageDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(add);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @Valid @RequestBody LanguageDto languageDto){
        ApiResponse edit = languageService.edit(id,languageDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet= languageService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(delet);
    }
    @GetMapping("/search")
    public HttpEntity<?> search(@RequestBody String name){
        ApiResponse response=languageService.search(name);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
}
