package pl.ang.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ReadingVideoTest")
public class ReadingVideoTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reading_video_test_id")
    private Long id;

    @Column(name = "text", length = 12000)
    private String text;

    @Column(name = "name", length = 2047)
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "link", length = 2047)
    private String link;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reading_video_test_id")
    private List<Question> questions;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "reading_video_test_tag", joinColumns = @JoinColumn(name = "reading_video_test_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "reading_video_test_role", joinColumns = @JoinColumn(name = "reading_video_test_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
