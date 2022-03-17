//package com.example.usermanemenent.models;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Data
//public class RolePermission {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @Column(name = "ROLE_PERMISSION_VALUE", nullable = false)
//    private String name;
//
////    @Column(name = "ASSIGNED_PERMISSION")
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "permission_id", referencedColumnName = "id")
//    private Permission permission;
//
//
//    @Override
//    public boolean equals(Object obj){
//        if(obj == null || !(obj instanceof RolePermission)){
//            return false;
//        }
//        else if(obj == this){
//            return true;
//        }
//
//        RolePermission that = (RolePermission) obj;
//        return that.id == this.id;
//    }
//    @Override
//    public int hashCode(){
//        return Objects.hash(id);
//    }
//}
