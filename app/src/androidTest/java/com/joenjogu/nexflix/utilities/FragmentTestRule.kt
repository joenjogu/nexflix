package com.joenjogu.nexflix.utilities

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.ui.MainActivity
import org.koin.core.module.Module

abstract class FragmentTestRule<F: Fragment> : ActivityTestRule<MainActivity>(MainActivity::class.java, true){

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        val application = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as KoinTestApp
        application.injectModule(getModule())
    }

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.apply {
            runOnUiThread {
                findNavController(R.id.nav_fragment).navigate(getDirection())
            }
        }
    }

    abstract fun getDirection(): NavDirections

    abstract fun getModule(): Module
}

fun <F: Fragment> createRule(navDirections: NavDirections, module: Module) = object : FragmentTestRule<Fragment>() {
    override fun getDirection(): NavDirections = navDirections

    override fun getModule(): Module = module

}