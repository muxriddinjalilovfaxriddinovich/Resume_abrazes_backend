package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.Certificates;
@Repository
public interface CertificatesRepository extends JpaRepository<Certificates , Integer> {

}
