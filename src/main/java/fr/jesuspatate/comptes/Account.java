package fr.jesuspatate.comptes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
class Account {

    enum Type {
        ASSET, EXPENSE, INCOME
    }

    @Id
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Account parent;
}
