package com.app.composesubmission.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.composesubmission.R

@Composable
fun BookItem(
    id: Int,
    judul: String,
    tanggalTerbit: String,
    @DrawableRes sampul: Int,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    ) {
    Card(
        modifier = Modifier.padding(14.dp)
            .fillMaxWidth()
            .clickable { navigateToDetail(id) },
        shape = RoundedCornerShape(corner = CornerSize(20.dp))
    ) {
        Row(modifier = modifier) {
            AsyncImage(
                model = sampul,
                contentDescription = stringResource(id = R.string.sampul),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)
                .align(CenterVertically)) {
                Text(
                    text = judul,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = tanggalTerbit,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}