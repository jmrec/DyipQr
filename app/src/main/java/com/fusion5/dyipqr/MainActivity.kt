package com.fusion5.dyipqr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fusion5.dyipqr.ui.theme.DyipQrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DyipQrTheme {
                val navController = rememberNavController()
                val currentRoute = getCurrentRoute(navController)

                // Define routes where BottomNav should be visible
                val bottomNavRoutes = listOf(
                    AppDestinations.HOME_ROUTE,
                    AppDestinations.QUICK_SCAN_ROUTE,
                    AppDestinations.LOGIN_ROUTE,
                    AppDestinations.SIGNUP_ROUTE,
                    AppDestinations.TERMINALS_ROUTE,
                    AppDestinations.SAVED_ROUTE
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    bottomBar = {
                        if (currentRoute in bottomNavRoutes) {
                            BottomNav(
                                selected = currentRoute,
                                onNavClick = { route ->
                                    navController.navigate(route) {
                                        launchSingleTop = true
                                        restoreState = true
                                        // Pop up to home to avoid deep back stack from bottom nav items
//                                        if (currentRoute != AppDestinations.HOME_ROUTE) {
//                                            popUpTo(AppDestinations.HOME_ROUTE) {
//                                                saveState = true
//                                            }
//                                        }
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppDestinations.HOME_ROUTE, // App starts at Home
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(AppDestinations.LOGIN_ROUTE) {
                            LoginScreen(navController = navController)
                        }
                        composable(AppDestinations.SIGNUP_ROUTE) {
                            SignupScreen(navController = navController)
                        }
                        composable(AppDestinations.HOME_ROUTE) {
                            DyipQrHomeScreen(
                                onNavigateToQuickScan = { navController.navigate(AppDestinations.QUICK_SCAN_ROUTE) },
                                onNavigateToAccount = { navController.navigate(AppDestinations.LOGIN_ROUTE) }, // Account item in HomeScreen navigates to LoginScreen
                                onNavigateToTerminals = { navController.navigate(AppDestinations.TERMINALS_ROUTE) },
                                onNavigateToSaved = { navController.navigate(AppDestinations.SAVED_ROUTE) }
                            )
                        }
                        composable(AppDestinations.QUICK_SCAN_ROUTE) {
                            QuickScanScreen(onBack = { navController.popBackStack() })
                        }
                        // composable(AppDestinations.ACCOUNT_ROUTE) { ... } // ACCOUNT_ROUTE screen definition removed
                        composable(AppDestinations.TERMINALS_ROUTE) {
                            // Replace with actual TerminalsScreen when ready
                            // TerminalsScreen(onBack = { navController.popBackStack() })
                            Text("Terminals Screen") // Placeholder
                        }
                        composable(AppDestinations.SAVED_ROUTE) {
                            // Replace with actual SavedScreen when ready
                            // SavedScreen(onBack = { navController.popBackStack() })
                            Text("Saved Screen") // Placeholder
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getCurrentRoute(navController: NavHostController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Use the route of the back stack entry, or default to HOME_ROUTE if back stack is empty initially
    return navBackStackEntry?.destination?.route ?: AppDestinations.HOME_ROUTE 
}
