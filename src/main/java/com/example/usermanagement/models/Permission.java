package com.example.usermanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PERMISSION_CODE")           // accessing points.
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();



    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof User)){
            return false;
        }
        else if(obj == this){
            return true;
        }

        Permission that = (Permission) obj;
        return that.id == this.id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

}
