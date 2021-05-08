package com.netvirta.toothpickoptimize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.netvirta.toothpickoptimize.annotations.NotSingletonCarClass
import com.netvirta.toothpickoptimize.model.CarFactory
import com.netvirta.toothpickoptimize.model.Vehicle
import com.netvirta.toothpickoptimize.model.VehicleFactory
import toothpick.InjectConstructor
import toothpick.ktp.KTP
import toothpick.Lazy
import toothpick.ktp.binding.module
import javax.inject.Named

class SecondActivity : AppCompatActivity() {
    private val carFactory: VehicleFactory by toothpick.ktp.delegate.lazy()
    private val carSingleton: Vehicle by toothpick.ktp.delegate.lazy()
    private val carNotSingleton: Vehicle by toothpick.ktp.delegate.lazy(NotSingletonCarClass::class)
    private val carHolder: CarHolder by toothpick.ktp.delegate.lazy()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        KTP.openRootScope()
            .openSubScope(this)
            .installModules(module {
                bind(VehicleFactory::class.java).to(CarFactory::class.java)
                bind(CarHolder::class.java).to(CarHolder::class.java)
            })
            .inject(this)
        carFactory.testCarEngine()
        findViewById<Button>(R.id.button3).setOnClickListener {
            Toast.makeText(this, "CAR SINGLETON ${carSingleton.hashCode()}", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            Toast.makeText(this, "CAR NOT SINGLETON ${carNotSingleton.hashCode()}", Toast.LENGTH_SHORT).show()

        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            Toast.makeText(this, "CAR NULLABLE ${carHolder.getCar()?.hashCode()}", Toast.LENGTH_SHORT).show()

        }
    }
}

@InjectConstructor
class CarHolder(@Named("nullable") val emptyCar: Lazy<Vehicle?>) {
    fun getCar(): Vehicle? {
        return emptyCar.get()
    }
}