package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.Certificates;
import uz.yangitexnologiya.entity.Resyume;

@Repository
public interface ResyumeRepository extends JpaRepository<Resyume, Integer> {

}
