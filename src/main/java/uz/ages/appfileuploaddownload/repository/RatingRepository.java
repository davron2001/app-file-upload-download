package uz.ages.appfileuploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.appfileuploaddownload.entitiy.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
