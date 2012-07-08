package net.sam_solutions.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * User: Администратор
 * Date: 08.05.12
 */
@Table(name = "private_info", schema = "public", catalog = "")
@Entity
public class PrivateInfoEntity {
    private String email;

    @Column(name = "email")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private long userId;

    @Column(name = "user_id")
    @Id
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "usersByUserId"))
    @GeneratedValue(generator = "generator")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateInfoEntity that = (PrivateInfoEntity) o;

        if (userId != that.userId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    private UsersEntity usersByUserId;

    @OneToOne
    @PrimaryKeyJoinColumn
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
