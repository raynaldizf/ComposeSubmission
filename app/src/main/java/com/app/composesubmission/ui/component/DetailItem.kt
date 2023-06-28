package com.app.composesubmission.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.composesubmission.R
import com.app.composesubmission.ui.theme.ComposeSubmissionTheme


@Preview(showBackground = true)
@Composable
fun DetailItemPreview() {
    ComposeSubmissionTheme {
        DetailItem(
            judul = "Radikus Makan Kakus",
            tanggalTerbit = "14 September 2005",
            deskripsi = "Sebuah cerita humor tentang seorang pria yang memiliki obsesi dengan kamar mandi.",
            sampul = R.drawable.radikus,
            statusFav = false,
            onBackClick = { /*TODO*/ },
            onClickToFav = { /*TODO*/ }
        )
    }
}

@Composable
fun DetailItem(
    judul: String,
    tanggalTerbit: String,
    deskripsi: String,
    @DrawableRes sampul: Int,
    statusFav: Boolean,
    onBackClick: () -> Unit,
    onClickToFav: (status: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    val status by rememberSaveable { mutableStateOf(statusFav) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.kembali),
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBackClick() }
                .align(Alignment.Start)
        )
        Text(
            text = judul,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = tanggalTerbit,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
        Image(
            painter = painterResource(sampul),
            contentDescription = null,
            modifier = modifier
                .padding(14.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = deskripsi,
            style = MaterialTheme.typography.body2,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.Transparent)
        )
        FloatingActionButton(
            onClick = { onClickToFav(status) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.favorit),
                tint = if (status) Color(0xFFE57373) else Color.White
            )
        }
    }
}
