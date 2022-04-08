package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.Certificates;
import uz.yangitexnologiya.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    boolean existsByLanguageName(String name);

}
