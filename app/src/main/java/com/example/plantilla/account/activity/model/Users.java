package com.example.plantilla.account.activity.model;

import java.util.Date;

public class Users {

    //<editor-fold desc="Atributos">
    //    Atributos
    private String Id;
    private String CliendId;
    private String IdentificationType;
    private String IdentificationNumber;
    private String FirstName;
    private String LastName;
    private String Email;
    private Boolean EmailConfirmed;
    private String PasswordHash;
    private String SecurityStamp;
    private String PhoneNumber;
    private Boolean PhoneNumberConfirmed;
    private Boolean TwoFactorEnabled;
    private Date LockoutEndDateUtc;
    private Boolean LockoutEnabled;
    private int AccessFailedCount;
    private String UserName;
    private Boolean IsActive;
    private int idCuentaBancaria;
    private int idTipoCuenta;
    private String NumeroCuenta;
    private int idBanco;
    private String grant_type;
    private String token;
    //</editor-fold>

    public  Users (){}

    public Users(String grant_type, String userName, String password,  String token) {
        this.grant_type=  grant_type;
        this.UserName  = userName;
        this.PasswordHash=password;
        this.token = token;
    }

    public String getGrantType() {
        return grant_type;
    }

    public void setGrantType(String grant_type) {
        this.grant_type = grant_type;
    }

    public Users(String id, String cliendId, String identificationType, String identificationNumber, String firstName, String lastName, String email, Boolean emailConfirmed, String PasswordHash, String securityStamp, String phoneNumber, Boolean phoneNumberConfirmed, Boolean twoFactorEnabled, Date lockoutEndDateUtc, Boolean lockoutEnabled, int accessFailedCount, String UserName, Boolean isActive, int idCuentaBancaria, int idTipoCuenta, String numeroCuenta, int idBanco, String grant_type) {
        Id = id;
        CliendId = cliendId;
        IdentificationType = identificationType;
        IdentificationNumber = identificationNumber;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        EmailConfirmed = emailConfirmed;
        PasswordHash = PasswordHash;
        SecurityStamp = securityStamp;
        PhoneNumber = phoneNumber;
        PhoneNumberConfirmed = phoneNumberConfirmed;
        TwoFactorEnabled = twoFactorEnabled;
        LockoutEndDateUtc = lockoutEndDateUtc;
        LockoutEnabled = lockoutEnabled;
        AccessFailedCount = accessFailedCount;
        UserName  = UserName;
        IsActive = isActive;
        this.idCuentaBancaria = idCuentaBancaria;
        this.idTipoCuenta = idTipoCuenta;
        NumeroCuenta = numeroCuenta;
        this.idBanco = idBanco;
        this.grant_type = grant_type;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCliendId() {
        return CliendId;
    }

    public void setCliendId(String cliendId) {
        CliendId = cliendId;
    }

    public String getIdentificationType() {
        return IdentificationType;
    }

    public void setIdentificationType(String identificationType) {
        IdentificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        IdentificationNumber = identificationNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String PasswordHash) {
        PasswordHash = PasswordHash;
    }

    public String getSecurityStamp() {
        return SecurityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        SecurityStamp = securityStamp;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Boolean getPhoneNumberConfirmed() {
        return PhoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
        PhoneNumberConfirmed = phoneNumberConfirmed;
    }

    public Boolean getTwoFactorEnabled() {
        return TwoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        TwoFactorEnabled = twoFactorEnabled;
    }

    public Date getLockoutEndDateUtc() {
        return LockoutEndDateUtc;
    }

    public void setLockoutEndDateUtc(Date lockoutEndDateUtc) {
        LockoutEndDateUtc = lockoutEndDateUtc;
    }

    public Boolean getLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(Boolean lockoutEnabled) {
        LockoutEnabled = lockoutEnabled;
    }

    public int getAccessFailedCount() {
        return AccessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        AccessFailedCount = accessFailedCount;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        UserName = UserName;
    }

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        NumeroCuenta = numeroCuenta;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.grant_type = token;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

}


