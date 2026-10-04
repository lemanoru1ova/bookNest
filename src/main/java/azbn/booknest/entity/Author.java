package azbn.booknest.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 100)
    private String country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookAuthor> bookAuthors = new HashSet<>();

    protected Author() {
    }

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Set<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void addBook(Book book, azbn.booknest.enums.AuthorRole role) {
        BookAuthor bookAuthor = new BookAuthor(book, this, role);
        bookAuthors.add(bookAuthor);
        book.getBookAuthors().add(bookAuthor);
    }
}