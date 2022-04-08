package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.CertificatesDto;
import uz.yangitexnologiya.entity.Certificates;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.repository.CertificatesRepository;
import uz.yangitexnologiya.repository.ResyumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {

    @Autowired
    CertificatesRepository certificatesRepository;

    @Autowired
    ResyumeRepository resyumeRepository;


    public ApiResponse getAll() {
        List<Certificates> all = certificatesRepository.findAll();
        return new ApiResponse("Mana",true,all);

    }

    public ApiResponse getOneId(Integer id) {
        Optional<Certificates> byId = certificatesRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li Certificate mavjud emas", true);
        }
        return new ApiResponse("Mana", true,byId.get());
    }

    public ApiResponse add(CertificatesDto certificatesDto) {
        Optional<Resyume> optionalResyume = resyumeRepository.findById(certificatesDto.getResumeId());
        if (!optionalResyume.isPresent()) {
            return new ApiResponse("Bunday id resume mavjud emas", false);
        }
        Certificates certificates = new Certificates();
        certificates.setName(certificatesDto.getName());
        certificates.setResyume(optionalResyume.get());
        Certificates save = certificatesRepository.save(certificates);
        return new ApiResponse("Qo'shildi", true, save);
    }


    public ApiResponse edit(Integer id, CertificatesDto certificatesDto) {
        Optional<Certificates> byId = certificatesRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id certificate mavjud emas",false);
        }
        Certificates certificates = byId.get();
        certificates.setName(certificatesDto.getName());
        certificates.setResyume(byId.get().getResyume());
        Certificates save = certificatesRepository.save(certificates);
        return new ApiResponse("O'zgardi",true,save);
    }


    public ApiResponse delet(Integer id) {
        Optional<Certificates> byId = certificatesRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li certificate mavjud emas",false);
        }
        certificatesRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true , byId.get());
    }
}

