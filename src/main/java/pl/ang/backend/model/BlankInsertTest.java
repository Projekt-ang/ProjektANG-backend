package pl.ang.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BlankInsertTest")
public class BlankInsertTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blank_insert_test_id")
    private Long id;

    @Column(name = "text", length = 12000)
    private String text;
    
    @Column(name = "name", length = 2047)
    private String name;

    @Column(name = "author")
    private String author;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "blank_insert_test_id")
    private List<BlankSymbol> blankSymbols;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "blank_insert_test_tag", joinColumns = @JoinColumn(name = "blank_insert_test_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "blank_insert_test_role", joinColumns = @JoinColumn(name = "blank_insert_test_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
