package uz.yangitexnologiya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {

    private  String fullName ;
    private  String email;
    private  String phoneNumber;
    private Integer resumeId;
    private Integer attachmentId;
    private  String state;
    private  String district;
    private  String city;
}
