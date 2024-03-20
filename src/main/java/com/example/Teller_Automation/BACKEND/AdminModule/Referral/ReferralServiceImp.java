package com.example.Teller_Automation.BACKEND.AdminModule.Referral;

import org.springframework.stereotype.Service;

@Service
public class ReferralServiceImp implements ReferralService{

    private final ReferralRepo referralRepo;

    public ReferralServiceImp(ReferralRepo referralRepo) {
        this.referralRepo = referralRepo;
    }
}
