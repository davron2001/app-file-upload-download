package uz.ages.appfileuploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.appfileuploaddownload.entitiy.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
//    Optional<Attachment> findByName(String name);
}
