package uz.ages.appfileuploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.appfileuploaddownload.entitiy.AttachmentContent;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
//
//    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
//
//    Optional<AttachmentContent> findByAttachmentName(String name);
}
