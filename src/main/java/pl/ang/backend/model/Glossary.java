package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Glossary")
public class Glossary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "glossary_id")
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "definition")
    private String definition;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "glossary_id")
    private List<Translation> translations;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "glossary_id")
    private List<UsageExample> usageExamples;

}
