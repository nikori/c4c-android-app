package mhealth.c4c;

import com.orm.SugarRecord;

/**
 * Created by ADMIN on 5/16/2017.
 */

public class RegistrationTable extends SugarRecord {
    String name,lname,idnumber,age,mflcode,username,password,gender,cadre;

    public RegistrationTable(){

    }
    public RegistrationTable(String nm,String lname,String gender,String cadre,String idnum,String age,String code,String uname,String pass){

        this.name=nm;
        this.lname=lname;
        this.idnumber=idnum;
        this.age=age;
        this.mflcode=code;
        this.username=uname;
        this.password=pass;
        this.gender=gender;
        this.cadre=cadre;
    }

    public String getAge() {
        return age;
    }

    public String getLname() {
        return lname;
    }

    public String getCadre() {
        return cadre;
    }

    public String getGender() {
        return gender;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getMflcode() {
        return mflcode;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public void setMflcode(String mflcode) {
        this.mflcode = mflcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCadre(String cadre) {
        this.cadre = cadre;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
