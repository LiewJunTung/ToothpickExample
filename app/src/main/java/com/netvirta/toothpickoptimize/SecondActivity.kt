package com.netvirta.toothpickoptimize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.netvirta.toothpickoptimize.model.CarFactory
import com.netvirta.toothpickoptimize.model.VehicleFactory
import toothpick.ktp.KTP
import toothpick.ktp.binding.module

class SecondActivity : AppCompatActivity() {
    private val carFactory: VehicleFactory by toothpick.ktp.delegate.lazy()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        KTP.openRootScope()
            .openSubScope(this)
            .installModules(module {
                bind(VehicleFactory::class.java).to(CarFactory::class.java)
            })
            .inject(this)
        carFactory.testCarEngine()
    }
}