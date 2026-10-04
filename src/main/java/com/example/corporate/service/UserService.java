package com.example.corporate.service;
import com.example.corporate.dto.*; import com.example.corporate.entity.*; import com.example.corporate.pattern.*; import com.example.corporate.repository.UserRepository; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service @Transactional public class UserService {
 private final UserRepository repo; private final UserFactory factory; private final PasswordEncoder encoder; private final List<AuditObserver> observers;
 public UserService(UserRepository r,UserFactory f,PasswordEncoder e,List<AuditObserver> o){repo=r;factory=f;encoder=e;observers=o;}
 public List<UserResponse> all(){return repo.findAll().stream().map(this::map).toList();}
 public UserResponse get(Long id){return map(repo.findById(id).orElseThrow(()->new NoSuchElementException("User not found")));}
 public UserResponse create(UserRequest r){if(repo.existsByUsername(r.username()))throw new IllegalArgumentException("Username already exists"); AppUser u=repo.save(factory.create(r)); audit("created user "+u.getUsername()); return map(u);}
 public UserResponse update(Long id,UserRequest r){AppUser u=repo.findById(id).orElseThrow(); if(r.fullName()!=null)u.setFullName(r.fullName()); if(r.roles()!=null)u.setRoles(r.roles()); if(r.enabled()!=null)u.setEnabled(r.enabled()); if(r.password()!=null&&!r.password().isBlank())u.setPassword(encoder.encode(r.password())); audit("updated user "+u.getUsername()); return map(repo.save(u));}
 public void delete(Long id){repo.deleteById(id);audit("deleted user id="+id);} private void audit(String e){observers.forEach(o->o.onEvent(e));}
 private UserResponse map(AppUser u){return new UserResponse(u.getId(),u.getUsername(),u.getFullName(),Set.copyOf(u.getRoles()),u.isEnabled());}
}
