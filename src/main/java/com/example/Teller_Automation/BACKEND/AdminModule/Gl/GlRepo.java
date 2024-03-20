package com.example.Teller_Automation.BACKEND.AdminModule.Gl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlRepo extends JpaRepository<Gl, Long> {
}
