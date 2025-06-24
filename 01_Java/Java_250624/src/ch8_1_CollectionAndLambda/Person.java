package ch8_1_CollectionAndLambda;

import java.util.ArrayList;
import java.util.List;
enum Gender {
   MALE, FEMALE
}
public class Person {
   private String givenName;   private String surName;
   private int age;   private Gender gender;
   private String eMail;   private String phone;
   private String address;   private String city;
   private String state;   private String code;
   private Person(Builder builder) {
       this.givenName = builder.givenName;       this.surName = builder.surName;
       this.age = builder.age;       this.gender = builder.gender;
       this.eMail = builder.eMail;       this.phone = builder.phone;
       this.address = builder.address;       this.city = builder.city;
       this.state = builder.state;       this.code = builder.code;
   }
   public String getGivenName() {       return givenName;   }
   public String getSurName() {       return surName;   }
   public int getAge() {       return age;   }
   public Gender getGender() {       return gender;   }
   public String geteMail() {       return eMail;   }
   public String getPhone() {       return phone;   }
   public static class Builder {
       private String givenName;       private String surName;
       private int age;       private Gender gender;
       private String eMail;       private String phone;
       private String address;       private String city;
       private String state;       private String code;
       public Builder givenName(String givenName) {
           this.givenName = givenName;
           return this;
       }
       public Builder surName(String surName) {
           this.surName = surName;           return this;
       }
       public Builder age(int age) {
           this.age = age;           return this;
       }
       public Builder gender(Gender gender) {
           this.gender = gender;           return this;
       }
       public Builder email(String eMail) {
           this.eMail = eMail;           return this;
       }
       public Builder phoneNumber(String phone) {
           this.phone = phone;           return this;
       }
       public Builder address(String address) {
           this.address = address;           return this;
       }
       public Builder city(String city) {
           this.city = city;           return this;
       }
       public Builder state(String state) {
           this.state = state;           return this;
       }
       public Builder code(String code) {
           this.code = code;           return this;
       }
       public Person build() {
           return new Person(this);
       }
   }
   public static List<Person> createShortList() {
       List<Person> people = new ArrayList<>();
       people.add(new Person.Builder()
               .givenName("Betty")
               .surName("Jones")
               .age(85)
               .gender(Gender.FEMALE)
               .email("betty.jones@example.com")
               .phoneNumber("211-33-1234")
               .build()
       );
       people.add(new Person.Builder()
               .givenName("John")
               .surName("Doe")
               .age(20)
               .gender(Gender.MALE)
               .email("john.doe@example.com")
               .phoneNumber("123-45-6789")
               .build()
       );
       people.add(new Person.Builder()
               .givenName("Alice")
               .surName("Smith")
               .age(25)
               .gender(Gender.FEMALE)
               .email("alice.smith@example.com")
               .phoneNumber("987-65-4321")
               .build()
       );
       return people;
   }
   @Override
   public String toString() {
       return "Person{" +
               "Name='" + givenName + " " + surName + '\'' +
               ", Age=" + age +
               ", Gender=" + gender +
               ", Email='" + eMail + '\'' +
               ", Phone='" + phone + '\'' +
               '}';
   }
}
