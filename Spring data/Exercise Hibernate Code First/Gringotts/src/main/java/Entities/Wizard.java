package Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "wizards")
public class Wizard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(columnDefinition = "TEXT", length = 1000)
    private String notes;
    @Column(nullable = false)
    private int age;

    @OneToOne
    @JoinColumn
    private WizardWand wizardWand;

    @OneToMany
    private List<Deposit> deposit;




}
