package ma.bank.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "VIREMENT")
@Entity
@Data
@RequiredArgsConstructor
@ToString
public class Virement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "CompteId")
    private Long CompteId;

    @Column(name = "BeneficierId")
    private  Long BeneficierId;

    @Column(name = "Montant")
    private double Montant;

    @Column(name = "Motif")
    private String Motif;

}
