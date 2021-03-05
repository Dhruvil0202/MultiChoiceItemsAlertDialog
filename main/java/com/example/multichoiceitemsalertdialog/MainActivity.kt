package com.example.multichoiceitemsalertdialog

import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var button: TextView
    private lateinit var btnMulti: TextView
    val colors = arrayOf<CharSequence>("Pink", "Red", "Yellow", "Blue")
    var slist: ArrayList<Int> = ArrayList()
    var icount = BooleanArray(colors.size)
    var msg = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "MultiChoiceAlertDialog"
        button = findViewById(R.id.button)
        button.setOnClickListener { showAlertDialog() }
        btnMulti = findViewById(R.id.btnMulti)
        btnMulti.setOnClickListener { showMultiAlertDialog() }

    }

    private fun showMultiAlertDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Choose Colors")
            .setMultiChoiceItems(colors, icount,
                OnMultiChoiceClickListener { arg0, arg1, arg2 ->
                    if (arg2) {
                        // If user select a item then add it in selected items
                        slist.add(arg1)
                    } else if (slist.contains(arg1)) {
                        // if the item is already selected then remove it
                        slist.remove(Integer.valueOf(arg1))
                    }
                }).setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                msg = ""
                for (i in 0 until slist.size) {
                    msg = msg.toString() + "\n" + (i + 1) + " : " + colors.get(slist.get(i))
                }
                Toast.makeText(
                    applicationContext, """Total ${slist.size.toString()} Items Selected.
$msg""", Toast.LENGTH_SHORT
                ).show()
            })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(
                        this@MainActivity,
                        "No Option Selected",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        //Creating dialog box
        //Creating dialog box
        val dialog = builder.create()
        dialog.show()
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("AlertDialog")
        alertDialog.setMessage("Do you wanna close this Dialog?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->
            Toast.makeText(this@MainActivity, "Alert dialog closed.", Toast.LENGTH_LONG).show()
        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ ->
            Toast.makeText(this@MainActivity, "User click No ", Toast.LENGTH_LONG).show()
        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}