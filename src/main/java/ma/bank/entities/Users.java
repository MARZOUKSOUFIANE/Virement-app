package ma.bank.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "USERS")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Users {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Login")
    private  String Login;

    @Column(name = "password")
    private String Password;

}
