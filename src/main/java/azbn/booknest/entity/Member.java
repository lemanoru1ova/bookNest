package azbn.booknest.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();

    protected Member() {
    }

    public Member(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.joinedAt = LocalDate.now();
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
        loan.setMember(this);
    }
}