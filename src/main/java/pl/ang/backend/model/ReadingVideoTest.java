package pl.ang.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ReadingVideoTest")
public class ReadingVideoTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reading_video_test_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "link")
    private String link;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reading_video_test_id")
    private List<Question> questions;

}
