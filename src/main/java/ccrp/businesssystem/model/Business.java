package ccrp.businesssystem.model;

import java.util.ArrayList;

public abstract class Business {

    public String uuid;
    public String name;
    public String description;
    public String ownerUUID;
    public ArrayList<Employee> employees;
    //public BankAccount bankAccount;
    public ArrayList<Work> work;



}
