package uz.yangitexnologiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.CertificatesDto;
import uz.yangitexnologiya.service.CertificateService;

import javax.validation.Valid;

@RestController
@RequestMapping("/certificate")
public class CertificatesController {

    @Autowired
    CertificateService certificateService;


    @GetMapping
    private HttpEntity<?> getAll(){
        ApiResponse all=certificateService.getAll();
        return ResponseEntity.status(all.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneId(@PathVariable Integer id){
        ApiResponse getoneId = certificateService.getOneId(id);
        return ResponseEntity.status(getoneId.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(getoneId);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CertificatesDto certificatesDto){
        ApiResponse apiResponse=certificateService.add(certificatesDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id , @Valid @RequestBody CertificatesDto certificatesDto){
        ApiResponse apiResponse=certificateService.edit(id,certificatesDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse apiResponse=certificateService.delet(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
}
