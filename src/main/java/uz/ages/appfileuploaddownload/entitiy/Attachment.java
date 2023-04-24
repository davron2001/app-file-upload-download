package uz.ages.appfileuploaddownload.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String title;

    private Double price;

    private String description; // description

    private String category;

    private String image;

    @OneToOne
    private Rating rating;
}
