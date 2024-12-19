package com.example.srodenas.examplewithnavigationdrawer.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.srodenas.examplewithnavigationdrawer.R
import com.example.srodenas.examplewithnavigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
      /*  ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
*/
        val toolbar = binding.appBarLayoutDrawer.toolbar

        /*
        1.- supportFragmentManager es el encargado de gestionar todos los fragmentos de nuestra App.
        2.- Devuelve un objeto del tipo Fragment, por tanto un NavHostFragment es una subclase de Fragment y
        por ello hacemos el cast.
        3.- Con el navHostFragment, buscamos el navController porque todo navHostFragment tiene asociado un
        navController. El navController es el encargado de realizar la navegación. Es el Dios de la navegación entre fragmentos.
         */
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController  //forma más directa a través del mismo componente
       // navController = navHost.findNavController()


        //recuperamos el navView (componente de navegación lateral izquierda)
        val navView = binding.myNavView

        /*
        configuramos los top-level. Lo que indicamos, es que no aparezca el botón de retroceso
        y en su lugar que aparezca la hamburguesa.
         */
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentHome, R.id.fragmentConf, R.id.fragmentPpal), // Destinos principales
            binding.main // DrawerLayout
        )

        //insertamos la toolbar
        setSupportActionBar(toolbar)

        /*
        vincula el navController con el toolbar (titulo + hambuerguesa/retroceso)
        De esa forma, se puede navegar con la toolbar.
         */
        setupActionBarWithNavController(navController, appBarConfiguration)

        /*
        vincula el menú lateral del drawer con el navController según. el nav_graph
        De esa forma, se puede navegar con la barra lateral izquierda.
         */
        navView.setupWithNavController(navController)
    }

    /*
Con este método, hacemos que funcione correctamente el botón de navegación hacia arriba. 1.- Esto hace que que responda a los eventos de navegación. Controlará la apertura de abrir la barra lateral del drawer y su cierre.
2.- Sin esto, no se abre/cierra el Drawer.
*/
    override fun onSupportNavigateUp(): Boolean{
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_op, menu)
        return true
    }


    //Navegación del menú de opciones.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fragmentHome -> {
                navController.navigate(R.id.fragmentHome)
                true
            }
            R.id.fragmentConf -> {
                navController.navigate(R.id.fragmentConf)
                true
            }
            R.id.fragmentPpal -> {
                navController.navigate(R.id.fragmentPpal)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}