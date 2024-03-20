package com.example.Teller_Automation.BACKEND.AdminModule.Gl;

import com.example.Teller_Automation.BACKEND.AdminModule.Teller.Teller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Gl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long glAccount;

    private String glName;

    private Long glBalance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="gl")
    private Teller teller;

}
