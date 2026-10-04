package com.example.corporate.entity;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="app_users")
public class AppUser {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true,nullable=false) private String username;
 @Column(nullable=false) private String password;
 private String fullName; private boolean enabled=true;
 @ElementCollection(fetch=FetchType.EAGER) @CollectionTable(name="user_roles",joinColumns=@JoinColumn(name="user_id")) @Enumerated(EnumType.STRING) @Column(name="role") private Set<Role> roles=new HashSet<>();
 public Long getId(){return id;} public void setId(Long id){this.id=id;} public String getUsername(){return username;} public void setUsername(String v){username=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;} public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;} public boolean isEnabled(){return enabled;} public void setEnabled(boolean v){enabled=v;} public Set<Role> getRoles(){return roles;} public void setRoles(Set<Role> v){roles=v;}
}
