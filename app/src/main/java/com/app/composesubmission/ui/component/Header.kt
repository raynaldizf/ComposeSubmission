package com.app.composesubmission.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.composesubmission.R
import com.app.composesubmission.ui.screen.Screen


@Composable
fun Header(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(text = stringResource(id = R.string.app_name), color = Color.Black)
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.Favorite.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.halaman_favorit),
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = Color(0xFFE57373)
                )
            }
            IconButton(onClick = {
                navController.navigate(Screen.Profile.route)
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(id = R.string.halaman_profile),
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = Color(0xFF7986CB)
                )
            }
        }
    )
}
