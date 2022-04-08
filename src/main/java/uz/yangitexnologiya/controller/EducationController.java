package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.EducationDto;
import uz.yangitexnologiya.entity.Education;
import uz.yangitexnologiya.service.EducationService;
import uz.yangitexnologiya.service.ResyumeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {

    @Autowired
    EducationService educationService;

    @Autowired
    ResyumeService resyumeService;

    @GetMapping
    public HttpEntity<?> all(){
       List<Education> educationList = educationService.all();
       return ResponseEntity.ok().body(educationList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        Education oneId=educationService.getOneId(id);
        return ResponseEntity.ok().body(oneId);
    }

    @PostMapping("/{id}")
    public  HttpEntity<?> add(@Valid @RequestBody EducationDto educationDto){
        ApiResponse apiResponse=educationService.add(educationDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @Valid @RequestBody EducationDto educationDto){
        ApiResponse apiResponse=educationService.edit(id,educationDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse apiResponse=educationService.delet(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

}
