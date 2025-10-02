package com.fusion5.dyipqr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.fusion5.dyipqr.ui.theme.DyipQrTheme

@Composable
fun DyipQrHomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DyipQR",
                fontSize = 75.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "DyipQR is an application that helps commuters to find jeepney routes and stops in Baguio City.",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier.width(244.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(196.dp),
                color = Color.Transparent,
                shadowElevation = 0.dp
            ) {
                Column {
                    HomeMenuItem(
                        iconRes = R.drawable.ic_search,
                        text = "Quick Scan",
                        onClick = { /* TODO */ }
                    )
                    Divider(color = Color(0xFFD9D9D9), thickness = 1.dp)
                    HomeMenuItem(
                        iconRes = R.drawable.ic_user,
                        text = "Account",
                        onClick = { /* TODO */ }
                    )
                    Divider(color = Color(0xFFD9D9D9), thickness = 1.dp)
                    HomeMenuItem(
                        iconRes = R.drawable.ic_detector,
                        text = "Detect Nearby Terminals",
                        onClick = { /* TODO */ }
                    )
                    Divider(color = Color(0xFFD9D9D9), thickness = 1.dp)
                    HomeMenuItem(
                        iconRes = R.drawable.ic_save,
                        text = "Saved",
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
        Text(
            text = "Made with ♡ by TSC",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )
    }
}

@Composable
fun HomeMenuItem(iconRes: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
            .clickable { onClick() }
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xFF131313)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DyipQrHomeScreenPreview() {
    DyipQrTheme {
        DyipQrHomeScreen()
    }
}
