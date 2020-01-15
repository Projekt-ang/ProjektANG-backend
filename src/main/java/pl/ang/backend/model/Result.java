package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @Column(name = "points")
    private Long points;

    @Column(name = "maxPoints")
    private Long maxPoints;

    @Column(precision = 5, scale = 2)
    private Integer percentage;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="reading_video_test_id")
    private ReadingVideoTest readingVideoTest;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "blank_insert_test_id")
    private BlankInsertTest blankInsertTest;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

}
