package pl.ang.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BlankSymbol")
public class BlankSymbol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blank_symbol_id")
    private Long id;

    @Column(name = "symbol_index")
    private int index;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "blank_symbol_id")
    private List<Answer> answers;

}
