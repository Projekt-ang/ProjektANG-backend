package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "UsageExample")
public class UsageExample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usage_example_id")
    private Long id;

    @Column(name = "sentence")
    private String sentence;

}
