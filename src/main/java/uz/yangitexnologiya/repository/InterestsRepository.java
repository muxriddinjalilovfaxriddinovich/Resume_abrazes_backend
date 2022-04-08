package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.Certificates;
import uz.yangitexnologiya.entity.Interests;

@Repository
public interface InterestsRepository extends JpaRepository<Interests, Integer> {

}
