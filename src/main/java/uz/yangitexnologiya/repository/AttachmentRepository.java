package uz.yangitexnologiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.yangitexnologiya.entity.attachment.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
