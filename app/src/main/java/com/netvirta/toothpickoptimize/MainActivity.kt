package com.netvirta.toothpickoptimize

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.netvirta.toothpickoptimize.annotations.NotSingletonCarClass
import com.netvirta.toothpickoptimize.model.Car
import com.netvirta.toothpickoptimize.model.Vehicle
import com.netvirta.toothpickoptimize.model.VehicleFactory
import toothpick.ktp.KTP
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.*

class MainActivity : AppCompatActivity() {
    private val carFactory: VehicleFactory by lazy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        KTP.openRootScope()
            .installModules(module {
                bind(Context::class.java).toInstance(this@MainActivity)
                bind(Vehicle::class.java).to(Car::class.java).singleton()
                bind(Vehicle::class.java).withName(NotSingletonCarClass::class.java).to(Car::class.java)
                bind(Vehicle::class.java).withName("nullable").toProviderInstance { null }
            })
            .inject(this)
        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            carFactory.testCarEngine() // Crash because the module is not init in this activity
        }


    }
}



