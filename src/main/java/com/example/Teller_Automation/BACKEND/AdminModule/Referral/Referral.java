package com.example.Teller_Automation.BACKEND.AdminModule.Referral;

import com.example.Teller_Automation.BACKEND.AdminModule.Teller.Teller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referral_id")
    private Long referralId;



    private Long amount;

    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="referral")
    private Teller teller;





}
