package azbn.booknest.entity;

import azbn.booknest.enums.AuthorRole;
import jakarta.persistence.*;

@Entity
@Table(name = "book_authors",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"book_id", "author_id", "role"}
        ))
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AuthorRole role;

    protected BookAuthor() {
    }

    public BookAuthor(Book book, Author author, AuthorRole role) {
        this.book = book;
        this.author = author;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Author getAuthor() {
        return author;
    }

    public AuthorRole getRole() {
        return role;
    }
}