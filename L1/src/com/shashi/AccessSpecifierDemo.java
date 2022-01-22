package com.shashi;

import com.gfg.Address;

public class AccessSpecifierDemo {

    public static void main(String[] args) {
        Address address = new Address();

    }
}

class NewAddress extends Address{
    public void setPincode(String pincode){
        this.pincode = pincode;
    }
}
