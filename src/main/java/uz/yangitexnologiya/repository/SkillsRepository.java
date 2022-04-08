package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer> {

}
