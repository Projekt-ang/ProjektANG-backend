package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "QuestionFillBlanks")
public class QuestionFillBlanks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "preblank", nullable = true)
    private String preblank;
    
    @NotNull
    @Column(name = "blankspace")
    private String blankspace;
    
    @Column(name = "postblank", nullable = true)
    private String postblank;
}
