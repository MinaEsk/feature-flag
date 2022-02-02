package com.mettle.featureflag.model;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "feature")
@NoArgsConstructor
public class Feature {
    private long id;
    private String name;
    private List<User> users;

    public Feature(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "user_feature",
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
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
        Feature feature = (Feature) o;
        return Objects.equals(id, feature.id) &&
                Objects.equals(name, feature.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
