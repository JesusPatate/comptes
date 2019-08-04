package fr.jesuspatate.comptes.spi;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
class DbAccount {

    enum Type {
        ASSET, EXPENSE, INCOME
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private DbAccount parent;
}
