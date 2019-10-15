package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question")
    private String question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reading_video_test_id", referencedColumnName = "reading_video_test_id", nullable = false)
    private ReadingVideoTest readingVideoTest;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

}
