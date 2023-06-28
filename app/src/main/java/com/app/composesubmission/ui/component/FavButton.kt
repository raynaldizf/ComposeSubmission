package com.app.composesubmission.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.composesubmission.R

@Composable
fun FavButton(
    status : Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        colors = if(!status) ButtonDefaults.buttonColors(MaterialTheme.colors.secondaryVariant)
        else ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Favorite Button"
            }
    ) {
        if(!status){
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = stringResource(id = R.string.tambah_favorit),
                modifier = Modifier.padding(end = 20.dp)
            )
            Text(
                text = stringResource(id = R.string.favorit),
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(id = R.string.hapus_favorit),
                modifier = Modifier.padding(end = 20.dp)
            )
            Text(
                text = stringResource(id = R.string.tidak_favorit),
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

    }
}
