package fr.jesuspatate.comptes.spi;

import javax.persistence.*;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        final DbAccount other = (DbAccount) object;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
