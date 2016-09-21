package SampleDaoModel;

import java.util.List;
import SampleDaoModel.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PERSON".
 */
public class Person {

    private Long id;
    private String name;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient PersonDao myDao;

    private Address address;
    private Long address__resolvedKey;

    private List<Book> booksOwn;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** To-one relationship, resolved on first access. */
    public Address getAddress() {
        Long __key = this.id;
        if (address__resolvedKey == null || !address__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AddressDao targetDao = daoSession.getAddressDao();
            Address addressNew = targetDao.load(__key);
            synchronized (this) {
                address = addressNew;
            	address__resolvedKey = __key;
            }
        }
        return address;
    }

    public void setAddress(Address address) {
        synchronized (this) {
            this.address = address;
            id = address == null ? null : address.getPersonId();
            address__resolvedKey = id;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Book> getBooksOwn() {
        if (booksOwn == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BookDao targetDao = daoSession.getBookDao();
            List<Book> booksOwnNew = targetDao._queryPerson_BooksOwn(id);
            synchronized (this) {
                if(booksOwn == null) {
                    booksOwn = booksOwnNew;
                }
            }
        }
        return booksOwn;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetBooksOwn() {
        booksOwn = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}