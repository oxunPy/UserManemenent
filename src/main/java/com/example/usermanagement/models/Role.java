package com.example.usermanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_NAME ")
    private String name;        // this should be unique

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "permission_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();


    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof User)){
            return false;
        }
        else if(obj == this){
            return true;
        }

        Role that = (Role) obj;
        return that.id == this.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
