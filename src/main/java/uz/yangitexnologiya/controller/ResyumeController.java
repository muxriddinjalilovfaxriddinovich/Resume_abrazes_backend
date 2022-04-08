package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.ResyumeDto;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.service.ResyumeService;

import java.util.List;

@RestController
@RequestMapping("/resyume")
public class ResyumeController {


    @Autowired
    ResyumeService resyumeService;

    @GetMapping
    public HttpEntity<?> all(){
        List<Resyume> all = resyumeService.all();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Resyume oneId = resyumeService.getOneId(id);
        return ResponseEntity.ok().body(oneId);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody ResyumeDto resyumeDto){
     ApiResponse apiResponse = resyumeService.add(resyumeDto);
     return ResponseEntity.ok().body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> edit(@PathVariable Integer id , @RequestBody ResyumeDto resyumeDto){
        ApiResponse apiResponse=resyumeService.edit(id,resyumeDto);
        return ResponseEntity.ok().body(apiResponse);
    }


}
