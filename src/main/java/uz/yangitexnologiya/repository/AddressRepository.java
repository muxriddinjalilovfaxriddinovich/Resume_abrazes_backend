package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yangitexnologiya.entity.Address;

public interface AddressRepository extends JpaRepository<Address , Integer> {

}
