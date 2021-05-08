
package com.netvirta.toothpickoptimize.model

import android.content.Context
import android.widget.Toast
import toothpick.InjectConstructor
import toothpick.Lazy

@InjectConstructor
class CarFactory(private val car: Lazy<Vehicle?>, private val context: Lazy<Context>): VehicleFactory {

    override fun testCarEngine(){
        Toast.makeText(context.get(), "CAR: ${car.get()?.hashCode()} --> Factory: ${hashCode()}", Toast.LENGTH_SHORT).show()
    }
}
