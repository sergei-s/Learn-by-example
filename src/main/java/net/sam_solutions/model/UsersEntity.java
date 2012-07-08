package net.sam_solutions.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * User: Администратор
 * Date: 08.05.12
 */
@Table(name = "users", schema = "public", catalog = "")
@Entity
public class UsersEntity {
    private String login;

    @Column(name = "login")
    @Basic
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String password;

    @Column(name = "password")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private long id;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    private PrivateInfoEntity privateInfoById;

    @OneToOne(mappedBy = "usersByUserId")
    @Cascade(CascadeType.SAVE_UPDATE)
    public PrivateInfoEntity getPrivateInfoById() {
        return privateInfoById;
    }

    public void setPrivateInfoById(PrivateInfoEntity privateInfoById) {
        this.privateInfoById = privateInfoById;
    }

    private PublicInfoEntity publicInfoById;

    @OneToOne(mappedBy = "usersByUserId")
    @Cascade(CascadeType.SAVE_UPDATE)
    public PublicInfoEntity getPublicInfoById() {
        return publicInfoById;
    }

    public void setPublicInfoById(PublicInfoEntity publicInfoById) {
        this.publicInfoById = publicInfoById;
    }
}
