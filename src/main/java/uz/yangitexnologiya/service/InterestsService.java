package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.InterestsDto;
import uz.yangitexnologiya.entity.Interests;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.repository.InterestsRepository;
import uz.yangitexnologiya.repository.ResyumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InterestsService {

    @Autowired
    InterestsRepository interestsRepository;

    @Autowired
    ResyumeRepository resyumeRepository;


    public ApiResponse findAll() {
        List<Interests> all = interestsRepository.findAll();
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getOneId(Integer id) {
        Optional<Interests> byId = interestsRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li interests mavjud emas",false);
        }
        return new ApiResponse("Mana",true,byId.get());
    }

    public ApiResponse add(InterestsDto interestsDto) {
        Optional<Resyume> byId = resyumeRepository.findById(interestsDto.getResumeId());
        if (!byId.isPresent()) {
            return new ApiResponse("Resyume mavjud emas",false);
        }
        List<Interests> all = interestsRepository.findAll();
        if (!all.isEmpty()) {
            return new ApiResponse("Interests avvaldan mavjud",false);
        }
        Interests interests=new Interests();
        interests.setText(interestsDto.getText());
        interests.setResyume(byId.get());
        Interests save = interestsRepository.save(interests);
        return new ApiResponse("Qo'shildi",true,save);
    }


    public ApiResponse edit(Integer id, InterestsDto interestsDto) {
        Optional<Interests> byId = interestsRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Interests mavjud emas",false);
        }
        Interests interests = byId.get();
        interests.setText(interestsDto.getText());
        interests.setResyume(byId.get().getResyume());
        Interests save = interestsRepository.save(interests);
        return new ApiResponse("O'zgartirildi",true,save);
    }


    public ApiResponse delet(Integer id) {
        Optional<Interests> byId = interestsRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Interests mavjud emas",false);
        }
        interestsRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true,byId.get());
    }
}
