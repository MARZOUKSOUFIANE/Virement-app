package ma.bank.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BENEFICIER")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Beneficier {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rib")
    private Long rib;

    @Column(name = "nom")
    private String nom;

}
