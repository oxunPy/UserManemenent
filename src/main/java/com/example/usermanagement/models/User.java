package com.example.usermanagement.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "_user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
    private Long id;

//    @Column(name = "LOGIN", nullable = false)         // this should be unique
    private String login;

//    @Column(name = "PASSWORD", nullable = false)
    private String password;

//    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
//    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();          // users which have many roles.


    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof User)){
            return false;
        }
        else if(obj == this){
            return true;
        }

        User that = (User) obj;
        return that.id == this.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
