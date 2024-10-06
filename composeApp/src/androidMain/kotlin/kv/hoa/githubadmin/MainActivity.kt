package kv.hoa.githubadmin

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import kv.hoa.githubadmin.db.appDatabaseModule
import kv.hoa.githubadmin.di.App
import kv.hoa.githubadmin.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                appDatabaseModule,
                appModule,
                module {
                    single<Activity> { this@MainActivity }
                }
            )
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App()
        }
    }
}