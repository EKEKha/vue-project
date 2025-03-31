package com.example.backend.gallery.repository;

import com.example.backend.gallery.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository <Member,Integer>{

    Member findByEmailAndPassword(String email, String password);
}
