package com.netvirta.toothpickoptimize

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.netvirta.toothpickoptimize.model.Car
import com.netvirta.toothpickoptimize.model.Vehicle
import com.netvirta.toothpickoptimize.model.VehicleFactory
import toothpick.ktp.KTP
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.*

class MainActivity : AppCompatActivity() {
    private val car: Vehicle by lazy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        KTP.openRootScope()
            .installModules(module {
                bind(Context::class.java).toInstance(this@MainActivity)
                bind(Vehicle::class.java).to(Car::class.java).singleton()
            })
            .inject(this)
        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            Toast.makeText(this, "CAR: ${car.hashCode()}", Toast.LENGTH_SHORT).show()
        }


        car.startEngine()
    }
}




