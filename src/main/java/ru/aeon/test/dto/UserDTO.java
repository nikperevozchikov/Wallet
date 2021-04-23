package ru.aeon.test.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


/** Client Facing Model of Transaction **/
public class UserDTO {

    @ApiModelProperty(required = false, hidden = true)
    private Long id;

    private String userName;
    private String email;
    private Date dateCreated;
    private String password;
    @ApiModelProperty(required = false, hidden = true)
    private BigDecimal balance;

    public UserDTO() {
    }


    public UserDTO(UserDTOBuilder builder) {
        id = builder.id;
        userName = builder.userName;
        email = builder.email;
        dateCreated = builder.dateCreated;
        password = builder.password;
        balance = builder.balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated(LocalDate now) {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class UserDTOBuilder {

        private Long id;
        private String userName;
        private String email;
        private Date dateCreated;
        private BigDecimal balance;
        private String password;
        public UserDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserDTOBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public UserDTOBuilder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public UserDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }

    }

}

