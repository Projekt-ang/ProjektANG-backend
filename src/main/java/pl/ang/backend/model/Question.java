package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "question_id")
    private List<Answer> answers;

}
