package uz.yangitexnologiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.yangitexnologiya.dto.ApiResponse;
import uz.yangitexnologiya.dto.UsersDto;
import uz.yangitexnologiya.entity.Address;
import uz.yangitexnologiya.entity.Resyume;
import uz.yangitexnologiya.entity.Users;
import uz.yangitexnologiya.repository.AddressRepository;
import uz.yangitexnologiya.repository.AttachmentRepository;
import uz.yangitexnologiya.repository.ResyumeRepository;
import uz.yangitexnologiya.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    ResyumeRepository  resyumeRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AttachmentRepository attachmentRepository;



    public List<Users> all() {
        List<Users> all = usersRepository.findAll();
        return all;
    }


    public ApiResponse getOneId(Integer id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (!usersOptional.isPresent()) {
            return new ApiResponse("bunday id li user mavjud emas",false);
        }
        return new ApiResponse("Mana",true,usersOptional.get());
    }


    public ApiResponse add(UsersDto usersDto) {
        Optional<Resyume> resyumeOptional = resyumeRepository.findById(usersDto.getResumeId());
        if (!resyumeOptional.isPresent()) {
            return new ApiResponse("Resyume mavjud emas",false);
        }
        Users users=new Users();
        users.setFullName(usersDto.getFullName());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        users.setEmail(usersDto.getEmail());

        Address address=new Address();
        address.setCity(usersDto.getCity());
        address.setDistrict(usersDto.getDistrict());
        address.setState(usersDto.getState());
        addressRepository.save(address);

        users.setResyume(resyumeOptional.get());
        users.setAddress(address);
        users.setAttachment(attachmentRepository.getById(usersDto.getAttachmentId()));
        Users save = usersRepository.save(users);
        return new ApiResponse("Qo'shildi",true,save);
    }

    public ApiResponse edit(Integer id, UsersDto usersDto) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (!usersOptional.isPresent()) {
            return new ApiResponse("Bunday id li user mavjud emas",false);
        }

        Users users = usersOptional.get();
        users.setEmail(usersDto.getEmail());
        users.setFullName(usersDto.getFullName());
        users.setPhoneNumber(usersDto.getPhoneNumber());

        Address address = users.getAddress();
        address.setState(usersDto.getState());
        address.setCity(usersDto.getCity());
        address.setDistrict(usersDto.getDistrict());
        addressRepository.save(address);
        users.setAddress(address);
        users.setResyume(resyumeRepository.getById(usersDto.getResumeId()));
        users.setAttachment(attachmentRepository.getById(usersDto.getResumeId()));
        Users save = usersRepository.save(users);
        return new ApiResponse("O'zgartirildi",true,save);
    }


    public ApiResponse delet(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Bunday id li user mavjud emas",false);
        }
        usersRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true,usersRepository.findAll());
    }
}
