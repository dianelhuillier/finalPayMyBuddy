package com.openclassroom.payMyBuddy.model.dto;

import java.util.List;

import com.openclassroom.payMyBuddy.model.BankTransaction;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {

    @NotNull
    private String email;
    
    @NotNull
    private String iban;
    
    private double soldAccount;
    
}
