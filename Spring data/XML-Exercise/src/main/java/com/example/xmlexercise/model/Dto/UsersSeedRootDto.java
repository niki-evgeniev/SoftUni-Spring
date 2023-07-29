package com.example.xmlexercise.model.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSeedRootDto {

   @XmlElement(name = "user")
   private List<UsersSeedDto> users;

   public List<UsersSeedDto> getUsers() {
      return users;
   }

   public void setUsers(List<UsersSeedDto> users) {
      this.users = users;
   }
}
