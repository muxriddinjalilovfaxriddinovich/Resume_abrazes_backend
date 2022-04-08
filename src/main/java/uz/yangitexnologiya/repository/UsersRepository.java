package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.Certificates;
import uz.yangitexnologiya.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
