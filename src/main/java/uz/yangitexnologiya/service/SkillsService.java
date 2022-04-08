package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.LanguageDto;
import uz.yangitexnologiya.dto.SkillsDto;
import uz.yangitexnologiya.entity.Language;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.entity.Skills;
import uz.yangitexnologiya.repository.ResyumeRepository;
import uz.yangitexnologiya.repository.SkillsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SkillsService {

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    ResyumeRepository resyumeRepository;


    public ApiResponse all() {
        List<Skills> all = skillsRepository.findAll();
        return new ApiResponse("Mana",true,all);
    }


    public ApiResponse getOneId(Integer id) {
        Optional<Skills> byId = skillsRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li skills yoq",false);
        }
        return new ApiResponse("Mana",true,byId.get());
    }


    public ApiResponse add(SkillsDto skillsDto) {
        Optional<Resyume> byId = resyumeRepository.findById(skillsDto.getResumeId());
        if (byId.isPresent()) {
        return new ApiResponse("Qo'shilmadi ",false);
            }
        Skills skills=new Skills();
        skills.setName(skillsDto.getName());
        skills.setResyume(byId.get());
        Skills save = skillsRepository.save(skills);
        return new ApiResponse("Qo'shildi",true , save);
    }


    public ApiResponse edit(Integer id, SkillsDto skillsDto) {
        Optional<Skills> skillsOptional = skillsRepository.findById(id);
        if (!skillsOptional.isPresent()) {
            return new ApiResponse("Bunday id li rezyume mavjud emas",false);
        }
        Skills skills = skillsOptional.get();
        skills.setName(skillsDto.getName());
        skills.setResyume(resyumeRepository.getById(skillsDto.getResumeId()));
        Skills save = skillsRepository.save(skills);
        return new ApiResponse("O'zgartirildi",true,save);
    }


    public ApiResponse delet(Integer id) {

        Optional<Skills> byId = skillsRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li skills avjud emas",false);
        }
        skillsRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true);
    }
}
