package com.joenjogu.nexflix.utilities

import androidx.fragment.app.Fragment
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import org.junit.rules.TestRule

abstract class FragmentTestRule<F: Fragment> : ActivityScenarioRule<Fragment>()

fun <F: Fragment> createRule() = object : TestRule {

}