package com.denisov.anything.authservice.user;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public User(String name, String email, String password){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(){

    }

    @Override
    public String toString(){
        return "" + id + " " + email + " " + password + " " + name;
    }

    @Override
    public int hashCode(){
        return (int)(this.id) + this.email.hashCode() + this.password.hashCode() + this.name.hashCode();
    }

    @Override
    public boolean equals(Object instance){
        boolean equals = false;
        if(instance instanceof User){
            User toCompare = (User)instance;
            if(toCompare.getId() == this.id &&
               toCompare.getEmail().compareTo(this.email) == 0 &&
               toCompare.getPassword().compareTo(this.password) == 0 &&
               toCompare.getName().compareTo(this.name) == 0){
                equals = true;
            }
        }
        return equals;
    }
}
