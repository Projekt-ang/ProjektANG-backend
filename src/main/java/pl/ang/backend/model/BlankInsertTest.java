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

    @Column(name = "text")
    private String text;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "blank_insert_test_id")
    private List<BlankSymbol> blank_symbols;

}
