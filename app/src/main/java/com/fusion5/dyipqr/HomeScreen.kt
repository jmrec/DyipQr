package com.fusion5.dyipqr

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.fusion5.dyipqr.ui.theme.DyipQrTheme

data class MenuItemData(
	val icon: ImageVector,
	val contentDescription: String? = "",
	val text: String,
	val onClick: () -> Unit,
)

@Composable
fun DyipQrHomeScreen(
    onNavigateToQuickScan: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {},
    onNavigateToTerminals: () -> Unit = {},
    onNavigateToSaved: () -> Unit = {}
) {
	val menuItems = listOf(
		MenuItemData(
			icon = Icons.Outlined.Search,
			text = "Quick Scan",
			onClick = onNavigateToQuickScan
        ),
		MenuItemData(
			icon = Icons.Outlined.Person,
			text = "Account",
			onClick = onNavigateToAccount
        ),
		MenuItemData(
			icon = Icons.Outlined.DirectionsBus,
			text = "Detect Nearby Terminals",
			onClick = onNavigateToTerminals
        ),
		MenuItemData(
			icon = Icons.Outlined.Save,
			text = "Saved",
			onClick = onNavigateToSaved
        )
	)

	Box(
		modifier = Modifier
			.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = "DyipQR",
				fontSize = 75.sp,
			)
			Spacer(modifier = Modifier.height(20.dp))
			Text(
				text = "DyipQR is an application that helps commuters to find jeepney routes and stops in Baguio City.",
				fontSize = 15.sp,
				modifier = Modifier.width(240.dp),
				textAlign = TextAlign.Center
			)
			Spacer(modifier = Modifier.height(60.dp))
			Surface(
				modifier = Modifier
					.width(300.dp)
					.wrapContentHeight(),
				color = Color.Transparent,
			) {
				Column {
					menuItems.forEachIndexed { index, item ->
						HomeMenuItem(
							icon = item.icon,
							text = item.text,
							onClick = item.onClick
						)
						if (index < menuItems.size - 1) {
							HorizontalDivider(
								thickness = 1.dp,
								color = Color(0xFFD9D9D9)
							)
						}
					}
				}
			}
		}
		Text(
			text = "Made with ♡ by TSC",
			fontSize = 15.sp,
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.padding(bottom = 20.dp)
		)
	}
}

@Composable
fun HomeMenuItem(
	icon: ImageVector,
	contentDescription: String? = "",
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
		.fillMaxWidth()
		.height(70.dp)
		.clickable { onClick() }
		.padding(horizontal = 10.dp),
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			imageVector = icon,
			contentDescription = contentDescription,
			modifier = Modifier.size(24.dp)
		)
		Spacer(modifier = Modifier.width(16.dp))
		Text(
			text = text,
			fontSize = 20.sp,
		)
	}
}

@Preview(showBackground = true)
@Composable
fun DyipQrHomeScreenPreview() {
	DyipQrTheme {
		DyipQrHomeScreen(
            onNavigateToQuickScan = {},
            onNavigateToAccount = {},
            onNavigateToTerminals = {},
            onNavigateToSaved = {}
        )
	}
}
