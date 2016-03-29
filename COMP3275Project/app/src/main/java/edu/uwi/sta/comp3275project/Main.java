package edu.uwi.sta.comp3275project;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

public class Main extends ActionBarActivity implements View.OnClickListener {
    TextView first,last,course,stdID;
    EditText firstN,lastN,course_code;
    Button btn;
    String userID;
    TextView hideMe;

    String FirstN,LastN,Course_Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn=(Button)findViewById(R.id.btn_scan);

        //Text Displays
        stdID=(TextView)findViewById(R.id.bcodetxt);
        first = (TextView)findViewById(R.id.FirstName);
        last=(TextView)findViewById(R.id.LastName);
        course=(TextView)findViewById(R.id.code);

        //Edit Texts
        firstN=(EditText)findViewById(R.id.f_name);
        lastN=(EditText)findViewById(R.id.l_name);
        course_code=(EditText)findViewById(R.id.courseCode);

        hideMe=(TextView)findViewById(R.id.hidden);
        hideMe.setVisibility(View.GONE);


        btn.setOnClickListener(this);
    }
    public void onClick (View v){
        if(v.getId()==R.id.btn_scan){
            IntentIntegrator intIntegrator = new IntentIntegrator(this);
            intIntegrator.initiateScan();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult intResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(intResult!=null){
            //Get Texts
            FirstN = firstN.getText().toString();
            LastN = lastN.getText().toString();
            Course_Code = course_code.getText().toString();

            hideMe.setVisibility(View.VISIBLE);
            userID = intResult.getContents();

            //set Texts
            stdID.setText("First Name: " + FirstN);
            first.setText("Last Name: " + LastN);
            last.setText("Course Code: " + Course_Code);
            course.setText("Student ID: "+  userID);
        }else{
            Toast.makeText(getApplicationContext(),"No data recieved",Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
