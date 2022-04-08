package uz.yangitexnologiya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.yangitexnologiya.entity.attachment.Attachment;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String fullName ;
    private String email;
    private String phoneNumber;

    @OneToOne
    private Resyume resyume;

    @OneToOne
    private Address address;

    @OneToOne
    private Attachment attachment;

}
