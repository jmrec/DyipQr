package com.fusion5.dyipqr

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNav(selected: String, onNavClick: (String) -> Unit) {
	Row(
		modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 10.dp),
		horizontalArrangement = Arrangement.SpaceAround,
		verticalAlignment = Alignment.CenterVertically
	) {
		NavItem(
			icon = Icons.Outlined.Search,
			label = "Quick Scan",
			selected = selected == AppDestinations.QUICK_SCAN_ROUTE,
			onClick = { onNavClick(AppDestinations.QUICK_SCAN_ROUTE) }
		)
		NavItem(
			icon = Icons.Outlined.Person,
			label = "Account",
			selected = selected == AppDestinations.LOGIN_ROUTE,
			onClick = { onNavClick(AppDestinations.LOGIN_ROUTE) }
		)
		NavItem(
			icon = Icons.Outlined.Home,
			label = "Home",
			selected = selected == AppDestinations.HOME_ROUTE,
			onClick = { onNavClick(AppDestinations.HOME_ROUTE) }
		)
		NavItem(
			icon = Icons.Outlined.DirectionsBus,
			label = "Terminals",
			selected = selected == AppDestinations.TERMINALS_ROUTE,
			onClick = { onNavClick(AppDestinations.TERMINALS_ROUTE) }
		)
		NavItem(
			icon = Icons.Outlined.Save,
			label = "Saved",
			selected = selected == AppDestinations.SAVED_ROUTE,
			onClick = { onNavClick(AppDestinations.SAVED_ROUTE) }
		)
	}
}

@Composable
fun NavItem(
	icon: ImageVector,
	label: String,
	selected: Boolean = false,
	onClick: () -> Unit = {},
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.padding(vertical = 6.dp)
			.clickable { onClick() }
	) {
		Icon(
			imageVector = icon,
			contentDescription = label,
			tint = if (selected) Color(0xFF404040) else Color(0xFF8C8C8C),
			modifier = Modifier.size(24.dp)
		)
		Text(
			text = label,
			fontSize = 10.sp,
			color = if (selected) Color(0xFF404040) else Color(0xFF8C8C8C)
		)
	}
}
