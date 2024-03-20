package com.example.Teller_Automation.BACKEND.AdminModule.Referral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepo extends JpaRepository<Referral, Long> {
}
