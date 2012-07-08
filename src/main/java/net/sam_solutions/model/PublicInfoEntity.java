package net.sam_solutions.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User: Администратор
 * Date: 08.05.12
 */
@Table(name = "public_info", schema = "public", catalog = "")
@Entity
public class PublicInfoEntity {
    private long userId;

    @Column(name = "user_id")
    @Id
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "usersByUserId"))
    @GeneratedValue(generator = "generator")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private String name;

    @Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicInfoEntity that = (PublicInfoEntity) o;

        if (userId != that.userId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
