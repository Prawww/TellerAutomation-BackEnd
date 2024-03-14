package com.example.Teller_Automation.BACKEND.AdminModule.Teller;

import com.example.Teller_Automation.BACKEND.AdminModule.Teller.Teller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerRepo extends JpaRepository<Teller, Long> {

}
