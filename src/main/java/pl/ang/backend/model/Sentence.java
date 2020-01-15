package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Sentence")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sentence_id")
    private Long id;

    @Column(name = "polish_sentence", length = 6000)
    private String polishSentence;

    @Column(name = "english_sentence", length = 6000)
    private String englishSentence;

    @Column(name = "eng_level")
    private String engLevel;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "sentence_tag", joinColumns = @JoinColumn(name = "sentence_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

}
