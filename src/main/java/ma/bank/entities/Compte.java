package ma.bank.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "COMPTE")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Compte {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cNumero")
    private int cNumero;

    @Column(name = "cSolde")
    private double cSolde;

    @Column(name = "CodeBanque")
    private int CodeBanque;


}
