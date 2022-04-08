package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.ResyumeDto;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.repository.ResyumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResyumeService {

    @Autowired
    ResyumeRepository resyumeRepository;


    public List<Resyume> all() {
        List<Resyume> all = resyumeRepository.findAll();
        return all;
    }

    public Resyume getOneId(Integer id) {
        Resyume byId = resyumeRepository.getById(id);
        return byId;
    }

    public ApiResponse add(ResyumeDto resyumeDto) {
        Resyume resyume=new Resyume();
        resyume.setSpecialization(resyumeDto.getSpecialization());
        Resyume save = resyumeRepository.save(resyume);
        return new ApiResponse("Qo'shildi", true,save);
    }

    public ApiResponse edit(Integer id, ResyumeDto resyumeDto) {

        Optional<Resyume> byId = resyumeRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li Resyume mavjud emas",false);
        }
        Resyume resyume = byId.get();
        resyume.setSpecialization(resyumeDto.getSpecialization());
        Resyume save = resyumeRepository.save(resyume);
        return new ApiResponse("O'zgartirildi",true,save);
    }
}
