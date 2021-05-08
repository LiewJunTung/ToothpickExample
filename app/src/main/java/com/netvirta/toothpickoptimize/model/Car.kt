package com.netvirta.toothpickoptimize.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import toothpick.InjectConstructor
import toothpick.Scope
import toothpick.Lazy
import toothpick.ktp.extension.getLazy

@InjectConstructor
class Car(private var scope: Scope) : Vehicle {

    override fun startEngine() {
        val context: Lazy<Context?> = scope.getLazy()
//        val context = context.get()

        if (context.get() != null){
            Toast.makeText(context.get(), "START ENGINE ${hashCode()}", Toast.LENGTH_LONG).show()
        } else {
             Log.d(this::class.java.simpleName, "NO ENGINE")
        }


    }
}