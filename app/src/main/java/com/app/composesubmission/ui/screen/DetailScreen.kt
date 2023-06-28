package com.app.composesubmission.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.composesubmission.R
import com.app.composesubmission.model.Injection
import com.app.composesubmission.ui.component.DetailItem
import com.app.composesubmission.ui.theme.ComposeSubmissionTheme
import com.app.composesubmission.viewmodel.DetailViewModel
import com.app.composesubmission.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
    navigateToFav: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value

    when (uiState) {
        is UiState.Loading -> {
            viewModel.getBookById(id)
        }
        is UiState.Success -> {
            val data = uiState.data
            DetailItem(
                judul = data.book.judul,
                tanggalTerbit = data.book.tanggalTerbit,
                deskripsi = data.book.deskripsi,
                sampul = data.book.sampul,
                statusFav = data.status,
                onBackClick = navigateBack,
                onClickToFav = {
                    if (!data.status) viewModel.addFav(data.book, false)
                    else viewModel.addFav(data.book, true)
                    navigateToFav()
                }
            )
        }
        is UiState.Error -> {
            // Handle error state
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    ComposeSubmissionTheme {
        DetailItem(
            judul = "Radikus Makan Kakus",
            tanggalTerbit = "14 September 2005",
            deskripsi = "Sebuah cerita humor tentang seorang pria yang memiliki obsesi dengan kamar mandi.",
            sampul = R.drawable.radikus,
            false,
            onBackClick = {},
            onClickToFav = {}
        )
    }
}
