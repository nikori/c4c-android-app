package mhealth.c4c;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mhealth.c4c.Tables.UserTable;

/**
 * Created by KENWEEZY on 2016-10-31.
 */

public class CreateUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nameE,lnameE,idnoE,ageE,mflE,munameE,mpassE,mcpassE;
    CheckBox mchkb;

    boolean correctMfl;

    public static final String REGISTER_URL = "http://everest.co.ke/mlabphp/checkfacility.php";


    public static final String KEY_MFLCODE = "facility_code";

    String[] genders={"Please Select Gender","Male","Female","Transgender"};
    String[] cadres={"Please Select Cadre","Student","Doctor","Nurse","Clinical officer","Laboratory technologist","Cleaner","Waste Handlers","Vct Counsellor"};
    String[] hepa={"Have you been vaccinated against Hepatitis B?","Partial","Yes","No"};


    List<UserTable> user_list = new ArrayList<>();
    DatePickerDialog datePickerDialog;
    int selectedYear;
    Spinner myspinner;
    Spinner myspinner2;
    Spinner myspinner3;
    String selected_item="";
    String myselected="";
    String selected_item2="";
    String myselected2="";
    String myselected3="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createuser);
        requestPerms();

        initialise();
        CheckToperiodListener();

        populateSpinner();
        populateSpinner2();
        populateSpinner3();

        myspinner.setOnItemSelectedListener(this);
        myspinner2.setOnItemSelectedListener(this);
        myspinner3.setOnItemSelectedListener(this);


    }


    public void CheckToperiodListener(){

        try{

            ageE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    datePickerDialog = new DatePickerDialog(CreateUser.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // set day of month , month and year value in the edit text
                                    ageE.setText(dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    selectedYear=year;

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            });
        }
        catch(Exception e){


        }
    }

    public void initialise(){

        try{

            nameE=(EditText) findViewById(R.id.name);
            lnameE=(EditText) findViewById(R.id.lname);
            idnoE=(EditText) findViewById(R.id.idno);
            ageE=(EditText) findViewById(R.id.age);
            mflE=(EditText) findViewById(R.id.mfl);
            munameE=(EditText) findViewById(R.id.muname);
            mpassE=(EditText) findViewById(R.id.mpass);
            mcpassE=(EditText) findViewById(R.id.mcpass);
            myspinner=(Spinner) findViewById(R.id.spinner);
            myspinner2=(Spinner) findViewById(R.id.spinner2);
            myspinner3=(Spinner) findViewById(R.id.spinner3);
            correctMfl=false;

        }
        catch(Exception e){


        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        Spinner spin=(Spinner) parent;

        if(spin.getId()==R.id.spinner){


//            selected_item=parent.getItemAtPosition(position).toString();
            myselected=Integer.toString(position);
            actOnSelected();

        }
        else if (spin.getId()==R.id.spinner2){

//            selected_item2=parent.getItemAtPosition(position).toString();
            myselected2=Integer.toString(position);
            actOnSelected();

        }
        else if (spin.getId()==R.id.spinner3){

//            selected_item2=parent.getItemAtPosition(position).toString();
            myselected3=Integer.toString(position);
            actOnSelected();

        }


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }




    public void actOnSelected(){

//        Toast.makeText(this, "you selected "+selected_item+"the behind scene value is "+myselected, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "you selected "+selected_item2+"the behind scene value is "+myselected2, Toast.LENGTH_SHORT).show();
    }

    public void populateSpinner(){

        try{

            SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext(),genders);



            myspinner.setAdapter(customAdapter);


        }

        catch(Exception e){


        }
    }

    public void populateSpinner2(){

        try{

            SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext(),cadres);

            myspinner2.setAdapter(customAdapter);


        }

        catch(Exception e){


        }
    }

    public void populateSpinner3(){

        try{

            SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext(),hepa);

            myspinner3.setAdapter(customAdapter);


        }

        catch(Exception e){


        }
    }


    public void CreatingUser(View v){

        try{

            String myname=nameE.getText().toString();
            String mylname=lnameE.getText().toString();
            String myidno=idnoE.getText().toString();
            String myage=ageE.getText().toString();
            String mymfl=mflE.getText().toString();
            String myuname=munameE.getText().toString();
            String mympass=mpassE.getText().toString();
            String mymcpass=mcpassE.getText().toString();
            int curyear = Calendar.getInstance().get(Calendar.YEAR);

            if(myname.trim().isEmpty()){
                nameE.setError("First Name is Required");

            }
            if(mylname.trim().isEmpty()){
                lnameE.setError("Last Name is Required");

            }
            else if(myidno.trim().isEmpty()){
                idnoE.setError("ID NUMBER is Required");

            }
            else if(myidno.length()<4){

                idnoE.setError("ID NUMBER should contain more than 3 values");

            }
            else if(myage.isEmpty()){
                ageE.setError("Age is Required");

            }

            else if(mymfl.trim().isEmpty()){
                mflE.setError("MFL Number is Required");

            }

            else if(mymfl.length()!=5){

                mflE.setError("MFL Number should have a length of Five");

            }
            else if(mympass.trim().isEmpty()){
                mpassE.setError("Password is Required");

            }
            else if(mymcpass.trim().isEmpty()){
                mcpassE.setError("Confirm Password is Required");

            }
            else if(!mymcpass.contentEquals(mympass)){
                mcpassE.setError("Passwords Do not match");

            }
            else if((curyear-selectedYear)<18){

                Toast.makeText(this, "age should be greater than 18, try again", Toast.LENGTH_LONG).show();
            }
            else if(myselected.contentEquals("0")){
                Toast.makeText(this, "Please Select Gender", Toast.LENGTH_LONG).show();


            }


            else if(myselected2.contentEquals("0")){
                Toast.makeText(this, "Please select Cadre", Toast.LENGTH_LONG).show();


            }


            else if(myselected3.contentEquals("0")){
                Toast.makeText(this, "Specify your vaccination", Toast.LENGTH_LONG).show();


            }

            else{

                checkFacilityCode(myname,mylname,myidno,myage,mymfl,myuname,mympass);


            }

        }
        catch(Exception e){
            SignupdisplayDialog("Error occured "+e);


        }
    }




    public void SignupdisplayDialog(String message){

        try{

            AlertDialog.Builder adb=new AlertDialog.Builder(this);
            adb.setTitle("SIGNUP ERROR");
            adb.setIcon(R.mipmap.error);
            adb.setMessage(message.toUpperCase());
            adb.setCancelable(false);

            adb.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Toast.makeText(Login.this,message,Toast.LENGTH_LONG).show();

                }
            });





            AlertDialog mydialog=adb.create();
            mydialog.show();
        }
        catch(Exception e){


        }

    }


    public void SignupsuccessDialog(String message){

        try{

            AlertDialog.Builder adb=new AlertDialog.Builder(this);
            adb.setTitle("SIGNUP SUCCESS");
            adb.setIcon(R.mipmap.success);
            adb.setMessage(message.toUpperCase());
            adb.setCancelable(false);

            adb.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent myint=new Intent(getApplicationContext(),Mylogin.class);
                    startActivity(myint);

                }
            });
            adb.setNegativeButton("EXIT APP", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });





            AlertDialog mydialog=adb.create();
            mydialog.show();
        }
        catch(Exception e){


        }

    }


    public void clearFields(){

        try{

//            mpassf.setText("");
//            mcpassf.setText("");
//            munamef.setText("");


        }
        catch(Exception e){



        }
    }



    public void checkFacilityCode(final String myname, final String mylname, final String myidno, final String myage, final String mymfl, final String myuname, final String mympass){


        final ProgressDialog pdialog= mydialog("loading...","Signing Up");

//        Toast.makeText(this, "checking facility", Toast.LENGTH_SHORT).show();

        final String mymflcode=mflE.getText().toString();
//        pr.progressing(getApplicationContext(),"getting facility","loading....");


        try{

            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.contentEquals("empty code")) {

                                pdialog.cancel();
                                Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                                correctMfl=false;

                            }

                            else if(response.contentEquals("code does not exist")){

                                pdialog.cancel();
                                Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                                correctMfl=false;


                            }
                            else if(response.contentEquals("error occured")){

                                pdialog.cancel();
                                Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                                correctMfl=false;


                            }
                            else {
                                pdialog.cancel();
                                correctMfl=true;

                                String mflName=response;

                                if(correctMfl){
                                    RegistrationTable rt=new RegistrationTable(myname,mylname,myselected,myselected2,myidno,myage,mymfl,myselected3,myuname,mympass);
                                    rt.save();

                                    String mymess="Reg*"+myname+"*"+mylname+"*"+myidno+"*"+myage+"*"+myselected+"*"+myselected2+"*"+mymfl+"*"+myselected3+"*"+myuname+"*"+mympass;


                                        SmsManager smsM=SmsManager.getDefault();
                                        smsM.sendTextMessage("40145",null,mymess,null,null);
                                        SignupsuccessDialog("Success in Registration");


                                }
                                else{
//                    SignupdisplayDialog("WRONG MFLCODE, TRY AGAIN");


                                }




                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pdialog.cancel();
                            correctMfl=false;

                            Toast.makeText(getApplicationContext(), "error occured "+error, Toast.LENGTH_SHORT).show();

                        }

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_MFLCODE, mymflcode);
//                    params.put(KEY_EMAIL, email);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }

        catch(Exception e){


        }


    }

    public ProgressDialog mydialog(String message,String title){
        ProgressDialog progress = new ProgressDialog(this);

        try{


            progress.setMessage(message);
            progress.setTitle(title);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();

        }
        catch(Exception e){
            Toast.makeText(this, "error displaying progress", Toast.LENGTH_SHORT).show();

        }

        return progress;
    }

    public void requestPerms(){

        try{

            int permissionCheck = ContextCompat.checkSelfPermission(CreateUser.this, Manifest.permission.SEND_SMS);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        CreateUser.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        1235);
            } else {

            }
        }
        catch(Exception e){
            Toast.makeText(this, "error in granting permissions "+e, Toast.LENGTH_SHORT).show();


        }
    }
}
