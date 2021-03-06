package SampleDaoModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "BOOK".
 */
public class Book {

    private Long bookId;
    private Long personId;
    private String bookName;

    public Book() {
    }

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Book(Long bookId, Long personId, String bookName) {
        this.bookId = bookId;
        this.personId = personId;
        this.bookName = bookName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

}
