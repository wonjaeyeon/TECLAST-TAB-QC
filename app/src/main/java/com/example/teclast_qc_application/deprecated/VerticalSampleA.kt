//package com.example.teclast_qc_application.home.pdf_export
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.pratikk.jetpdfvue.VerticalVueReader
//import com.pratikk.jetpdfvue.state.VerticalVueReaderState
//import kotlinx.coroutines.launch
//
//
//@Composable
//fun VerticalSampleA(
//    modifier: Modifier = Modifier,
//    verticalVueReaderState: VerticalVueReaderState,
//    import:() -> Unit
//){
//    BoxWithConstraints(
//        modifier = modifier
//    ) {
//        val scope = rememberCoroutineScope()
//
//        val background = Modifier
//        val iconTint = MaterialTheme.colors.onPrimary
//
//        VerticalVueReader(
//            modifier = Modifier.fillMaxSize(),
//            contentModifier = Modifier.fillMaxSize(),
//            verticalVueReaderState = verticalVueReaderState
//        )
//        Row(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .padding(horizontal = 8.dp, vertical = 12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "${verticalVueReaderState.currentPage} of ${verticalVueReaderState.pdfPageCount}",
//                modifier = Modifier
//                    .then(background)
//                    .padding(10.dp)
//            )
//            Spacer(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//            )
//            Row {
//                val context = LocalContext.current
//                IconButton(
//                    modifier = background,
//                    onClick = import
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Add,
//                        contentDescription = "Add Page",
//                        tint = iconTint
//                    )
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                IconButton(
//                    modifier = background,
//                    onClick = { //Share
//                        verticalVueReaderState.sharePDF(context)
//                    }) {
//                    Icon(
//                        imageVector = Icons.Filled.Share,
//                        contentDescription = "Share",
//                        tint = iconTint
//                    )
//                }
//            }
//        }
//        Row(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(horizontal = 8.dp, vertical = 12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            val showPrevious by remember {
//                derivedStateOf { verticalVueReaderState.currentPage != 1 }
//            }
//            val showNext by remember {
//                derivedStateOf { verticalVueReaderState.currentPage != verticalVueReaderState.pdfPageCount }
//            }
//            if (showPrevious)
//                IconButton(
//                    modifier = background,
//                    onClick = {
//                        //Prev
//                        scope.launch {
//                            verticalVueReaderState.prevPage()
//                        }
//                    }) {
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowUp,
//                        contentDescription = "Previous",
//                        tint = iconTint
//                    )
//                }
//            else
//                Spacer(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .background(Color.Transparent)
//                )
//            Spacer(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//            )
//            IconButton(
//                modifier = background,
//                onClick = {
//                    //Rotate
//                    verticalVueReaderState.rotate(-90f)
//                }) {
//                Icon(
//                    imageVector = Icons.Filled.RotateLeft,
//                    contentDescription = "Rotate Left",
//                    tint = iconTint
//                )
//            }
//            Spacer(modifier = Modifier.width(5.dp))
//            IconButton(
//                modifier = background,
//                onClick = {
//                    //Rotate
//                    verticalVueReaderState.rotate(90f)
//                }) {
//                Icon(
//                    imageVector = Icons.Filled.RotateRight,
//                    contentDescription = "Rotate Right",
//                    tint = iconTint
//                )
//            }
//            Spacer(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//            )
//            if (showNext)
//                IconButton(
//                    modifier = background,
//                    onClick = {
//                        //Next
//                        scope.launch {
//                            verticalVueReaderState.nextPage()
//                        }
//                    }) {
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowDown,
//                        contentDescription = "Next",
//                        tint = iconTint
//                    )
//                }
//            else
//                Spacer(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .background(Color.Transparent)
//                )
//        }
//    }
//}