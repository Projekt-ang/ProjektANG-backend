package pl.ang.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Confirmation_token")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        token = UUID.randomUUID().toString();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 60*24);
        expiryDate = cal.getTime();
    }
}
