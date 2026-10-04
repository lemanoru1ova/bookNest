package azbn.booknest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_details")
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer pages;

    @Column(length = 50)
    private String language;

    @Column(length = 150)
    private String publisher;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false, unique = true)
    private Book book;

    protected BookDetail() {
    }

    public BookDetail(Integer pages, String language, String publisher) {
        this.pages = pages;
        this.language = language;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public Integer getPages() {
        return pages;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}