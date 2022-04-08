package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.EducationDto;
import uz.yangitexnologiya.entity.Education;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.repository.EducationRepository;
import uz.yangitexnologiya.repository.ResyumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ResyumeRepository resyumeRepository;

    public ApiResponse add(EducationDto educationDto) {

        Optional<Resyume> byId = resyumeRepository.findById(educationDto.getResumeId());
        if (!byId.isPresent()) {
            return new ApiResponse("Resyume mavjud emas",false);
        }
        Education education= new Education();
        education.setEducationName(educationDto.getEducationName());
        education.setSpecializationName(educationDto.getSpecializationName());
        education.setResyume(byId.get());
        Education save = educationRepository.save(education);
        return new ApiResponse("Qo'shildi", true,save);

    }

    public List<Education> all() {
        List<Education> all = educationRepository.findAll();
        return all;
    }


    public Education getOneId(Integer id) {
        Education byId = educationRepository.getById(id);
        return byId;
    }

    public ApiResponse edit(Integer id, EducationDto educationDto) {
        Optional<Education> educationOptional = educationRepository.findById(id);
        if (!educationOptional.isPresent()) {
            return new ApiResponse("Bunday id education yoq", false);
        }
        Education education = educationOptional.get();
        education.setEducationName(educationDto.getEducationName());
        education.setSpecializationName(educationDto.getSpecializationName());
        education.setResyume(educationOptional.get().getResyume());
        Education save = educationRepository.save(education);
        return new ApiResponse("O'zgardi",true,save);
    }


    public ApiResponse delet(Integer id) {
        Optional<Education> byId = educationRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li education yoq",false);
        }
        educationRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true);
    }
}
