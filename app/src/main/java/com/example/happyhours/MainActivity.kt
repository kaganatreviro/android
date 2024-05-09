package com.example.happyhoursimport android.os.Bundleimport android.util.Logimport androidx.appcompat.app.AppCompatActivityimport androidx.navigation.fragment.NavHostFragmentimport com.example.data.local.prefs.TokenPrefsimport org.koin.android.ext.android.injectclass MainActivity : AppCompatActivity(R.layout.activity_main) {    val tokenPrefs by inject<TokenPrefs>()    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        val navHostFragment =            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment        val navController = navHostFragment.navController        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_app)        if (tokenPrefs.access.isNullOrEmpty()) {            navGraph.setStartDestination(com.example.presentation.R.id.nav_graph_auth)        } else {            navGraph.setStartDestination(com.example.presentation.R.id.nav_graph_bottom_nav)        }        navController.graph = navGraph        Log.e("ololo", tokenPrefs.access!!)    }}