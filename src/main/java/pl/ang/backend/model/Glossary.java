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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "glossary_tag", joinColumns = @JoinColumn(name = "glossary_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "glossary_role", joinColumns = @JoinColumn(name = "glossary_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
