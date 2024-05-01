package com.example.program_transfer_management

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class programtransfer : AppCompatActivity() {
    lateinit var btnDatePicker:ImageButton
    lateinit var bottom:BottomNavigationView
    lateinit var auth: FirebaseAuth
    private var mYear:Int=0
    private var mMonth:Int=0
    private var mHour:Int=0
    private var mDay:Int=0
    private var mDate:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programtransfer)
        bottom=findViewById(R.id.btm)
        btnDatePicker=findViewById(R.id.btn4)
        val editName = findViewById<EditText>(R.id.editName)
        val editPass = findViewById<EditText>(R.id.editPass)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editDate = findViewById<EditText>(R.id.editDate)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val btn = findViewById<Button>(R.id.btn_submit)
        val res = findViewById<TextView>(R.id.res)
        val spinner = findViewById<Spinner>(R.id.dropdown)

        btnDatePicker.setOnClickListener{
            val c= Calendar.getInstance()
            mYear=c[Calendar.YEAR]
            mMonth=c[Calendar.MONTH]
            mDay=c[Calendar.DAY_OF_MONTH]
            val DatePicker = DatePickerDialog(this,
                {view,year,monthOfYear, dateOfMonth -> editDate.setText(dateOfMonth.toString() + "-" + (monthOfYear+1)+"-"+year)},
                mYear,mMonth,mDate)
            DatePicker.show()
        }


        var course = arrayOf("Class 5", "Class 6", "Class 7", "Class 8", "Class 9")
        var option = " "

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, course
            )
            spinner.adapter = adapter

        }
        spinner.onItemSelectedListener=
            object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    option = course[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        btn.setOnClickListener()
        {
            val t1 = editName.text.toString()
            val t2 = editPass.text.toString()
            val t3 = editEmail.text.toString()
            val t4 = editDate.text.toString()
            val t5 = editPhone.text.toString()
            if (t1.isEmpty() || t2.isEmpty() ||
                t3.isEmpty() || t4.isEmpty() || t5.isEmpty()
            ) {
                res.text = "Enter All The Values"
            } else {
                val intent = Intent(this, programtransfer2::class.java)
                intent.putExtra("name", t1)
                intent.putExtra("pass", t2)
                intent.putExtra("email", t3)
                intent.putExtra("date", t4)
                intent.putExtra("phone", t5)
                intent.putExtra("cou", option)
                startActivity(intent)
            }
        }
        bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.firstcgpa->{
                    val intent=Intent(this,MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.secondcgpa->{
                    val intent=Intent(this,MainActivity3_f::class.java)
                    startActivity(intent)
                    true
                }
                R.id.fourthcgpa->{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("LogOut Alert")
                        .setMessage("Are you sure, you want to Log Out ")
                        .setCancelable(true)
                        .setPositiveButton("Yes"){dialogInterface,which->
                            auth = FirebaseAuth.getInstance()
                            auth.signOut()
                            val intent=Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No"){dialogInterface,which->
                            Toast.makeText(this,"You Have Clicked No",Toast.LENGTH_SHORT).show()
                        }
                        .show()
                    true
                }
                else->true
            }
        }
    }
}