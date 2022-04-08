package uz.yangitexnologiya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EducationDto {
    private String specializationName ;
    private String educationName;
    private Integer resumeId;
}
