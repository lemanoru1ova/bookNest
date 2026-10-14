package azbn.booknest.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 20, unique = true, nullable = false)
    private String isbn;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private BookDetail detail;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookAuthor> bookAuthors = new HashSet<>();

    protected Book() {
    }

    public Book(String title, String isbn, Integer publishedYear, Integer availableCopies) {
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public Category getCategory() {
        return category;
    }

    public BookDetail getDetail() {
        return detail;
    }

    public Set<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDetail(BookDetail detail) {
        this.detail = detail;
        if (detail != null) {
            detail.setBook(this);
        }
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}