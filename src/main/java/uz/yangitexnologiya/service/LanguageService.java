package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.LanguageDto;
import uz.yangitexnologiya.entity.Language;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.repository.LanguageRepository;
import uz.yangitexnologiya.repository.ResyumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    ResyumeRepository resyumeRepository;

    public ApiResponse all() {
        List<Language> all = languageRepository.findAll();
        return new ApiResponse("Mana",true,all);
    }


    public ApiResponse getOneId(LanguageDto languageDto) {
        boolean byLanguageName = languageRepository.existsByLanguageName(languageDto.getLanguageName());
        if (byLanguageName) {
            Language language=new Language();
            Language save = languageRepository.save(language);
            return new ApiResponse("mana "+save.getId(),true);
        }
        return new ApiResponse("bunday id li yoq",false );
    }


    public ApiResponse add(LanguageDto languageDto) {
        Optional<Resyume> byId = resyumeRepository.findById(languageDto.getResumeId());
        if (byId.isPresent()) {

        boolean byLanguageName = languageRepository.existsByLanguageName(languageDto.getLanguageName());
        if (!byLanguageName) {
        Language language=new Language();
            language.setLanguageName(languageDto.getLanguageName());
            language.setResyume(byId.get());
            Language save = languageRepository.save(language);
            return new ApiResponse("Qo'shildi"+save.getId(),true , save);
        }
        }
        return new ApiResponse("Qo'shilmadi ",false);
    }


    public ApiResponse edit(Integer id, LanguageDto languageDto) {
        Optional<Language> byId = languageRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li til mavjud emas",false);
        }

        Language language = byId.get();
        language.setLanguageName(languageDto.getLanguageName());
        language.setResyume(resyumeRepository.getById(languageDto.getResumeId()));
        Language save = languageRepository.save(language);
        return new ApiResponse("O'zgartirildi",true,save);
    }


    public ApiResponse delet(Integer id) {
        Optional<Language> byId = languageRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li til mavjud emas",false);
        }
        languageRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true);
    }

    public ApiResponse search(String name) {

        List<Language> languages;
        if (name.equals("uz")) {
            languages = languageRepository.findAll();
            return new ApiResponse("1", true);
        }else if (name.equals("eng"))
            languages=languageRepository.findAll();
            return new ApiResponse("2",true);

//        return new ApiResponse(null,true);
    }
}
