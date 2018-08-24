package id.co.ionsoft.randomnumberanimation

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * @author hendrawd on 24 Aug 2018
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun createBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}