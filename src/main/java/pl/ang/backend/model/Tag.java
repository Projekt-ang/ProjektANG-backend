package pl.ang.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "text", length = 2047)
    private String text;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<ReadingVideoTest> tests;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<BlankInsertTest> blankTests;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Sentence> sentences;
}
