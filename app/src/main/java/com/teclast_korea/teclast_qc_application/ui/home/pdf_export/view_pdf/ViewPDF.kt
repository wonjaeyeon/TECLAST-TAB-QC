package com.teclast_korea.teclast_qc_application.ui.home.pdf_export.view_pdf

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teclast_korea.teclast_qc_application.ui.home.pdf_export.getDirectory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*

enum class PdfListDirection {
    HORIZONTAL, VERTICAL
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComposePDFViewer(navController: NavController, context: Context) {
    var isLoading by remember { mutableStateOf(false) }
    var currentLoadingPage by remember { mutableStateOf<Int?>(null) }
    var pageCount by remember { mutableStateOf<Int?>(null) }

    val directory = getDirectory(context)
    val pdfFile = File(directory, "Test_Report.pdf")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "PDF View Screen") },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()){
        PdfViewer(
            file =  pdfFile,
            modifier = Modifier.fillMaxSize(),
            arrangement = Arrangement.spacedBy(0.dp),
            loadingListener = { loading, currentPage, maxPage ->
                isLoading = loading
                if (currentPage != null) currentLoadingPage = currentPage
                if (maxPage != null) pageCount = maxPage
            }
        )
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary),
                verticalArrangement = Arrangement.Center,

            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    color = MaterialTheme.colors.primaryVariant,
                    progress = if (currentLoadingPage == null || pageCount == null) 0f
                    else currentLoadingPage!!.toFloat() / pageCount!!.toFloat()
                )
                Text(
                    color = Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 5.dp)
                        .padding(horizontal = 30.dp)

                    ,

                    text = "${currentLoadingPage ?: "-"} pages loaded/${pageCount ?: "-"} total pages"
                )
            }
        }}
    }

}


@ExperimentalFoundationApi
@Composable
fun PdfViewer(
    file: File,
    modifier: Modifier = Modifier,
    listDirection: PdfListDirection = PdfListDirection.VERTICAL,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(16.dp),
    loadingListener: (isLoading: Boolean, currentPage: Int?, maxPage: Int?) -> Unit = { _, _, _ -> }
) {
    PdfViewer_2(
        pdfFile = file,
        modifier = modifier,
        listDirection = listDirection,
        arrangement = arrangement,
        loadingListener = loadingListener,
    )
}

@ExperimentalFoundationApi
@Composable
fun PdfViewer_2(
    pdfFile: File,
    modifier: Modifier = Modifier,
    listDirection: PdfListDirection = PdfListDirection.VERTICAL,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(16.dp),
    loadingListener: (isLoading: Boolean, currentPage: Int?, maxPage: Int?) -> Unit = { _, _, _ -> }
) {
    PdfViewer_3(
        pdfFile = pdfFile,
        modifier = modifier,
        listDirection = listDirection,
        arrangement = arrangement,
        loadingListener = loadingListener
    ) { lazyState, imagem ->
        PaginaPDF(
            imagem = imagem,
            lazyState = lazyState
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PdfViewer_3(
    pdfFile: File,
    modifier: Modifier = Modifier,
    listDirection: PdfListDirection = PdfListDirection.VERTICAL,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(16.dp),
    loadingListener: (isLoading: Boolean, currentPage: Int?, maxPage: Int?) -> Unit = { _, _, _ -> },
    page: @Composable (LazyListState, ImageBitmap) -> Unit
) {
    val context = LocalContext.current
    val pagePaths = remember {
        mutableStateListOf<String>()
    }
    LaunchedEffect(true) {
        if (pagePaths.isEmpty()) {
            val paths = context.loadPdf(pdfFile, loadingListener)
            pagePaths.addAll(paths)
        }
    }
    val lazyState = rememberLazyListState()
    when (listDirection) {
        PdfListDirection.HORIZONTAL ->
            LazyRow(
                modifier = modifier.background(Color.White),
                state = lazyState,
                horizontalArrangement = arrangement
            ) {
                items(pagePaths) { path ->
                    var imageBitmap by remember {
                        mutableStateOf<ImageBitmap?>(null)
                    }
                    LaunchedEffect(path) {
                        imageBitmap = BitmapFactory.decodeFile(path).asImageBitmap()
                    }
                    imageBitmap?.let { page(lazyState, it) }
                }
            }

        PdfListDirection.VERTICAL ->
            LazyColumn(
                modifier = modifier.background(Color.White),
                state = lazyState,
                verticalArrangement = arrangement
            ) {
                items(pagePaths) { path ->
                    var imageBitmap by remember {
                        mutableStateOf<ImageBitmap?>(null)
                    }
                    LaunchedEffect(path) {
                        imageBitmap = BitmapFactory.decodeFile(path).asImageBitmap()
                    }
                    imageBitmap?.let { page(lazyState, it) }
                }
            }
    }
}

@ExperimentalFoundationApi
@Composable
private fun PaginaPDF(
    imagem: ImageBitmap,
    lazyState: LazyListState
) {
    Column {
        ZoomableImage(
            painter = BitmapPainter(imagem),
            scrollState = lazyState
        )
        Divider(color = Color.Gray, thickness = 10.dp)
    }

}

suspend fun Context.loadPdf(
    pdfFile: File, // 수정: InputStream 대신 File 객체를 사용
    loadingListener: (
        isLoading: Boolean,
        currentPage: Int?,
        maxPage: Int?
    ) -> Unit = { _, _, _ -> }
): List<String> = withContext(Dispatchers.Default) {
    loadingListener(true, null, null)
    val input = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)
    val renderer = PdfRenderer(input)
    (0 until renderer.pageCount).map { pageNumber ->
        loadingListener(true, pageNumber, renderer.pageCount)
        val outputDir = cacheDir // 임시 이미지 파일을 저장할 캐시 디렉터리
        val imageFile = File.createTempFile("PDFpage$pageNumber", "png", outputDir).apply {
            deleteOnExit()
        }
        val page = renderer.openPage(pageNumber)
        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        FileOutputStream(imageFile).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        imageFile.absolutePath // 페이지 이미지 파일의 경로를 반환
    }.also {
        loadingListener(false, null, renderer.pageCount)
        renderer.close()
        input.close()
    }
}

@Throws(IOException::class)
private fun copy(source: InputStream, target: OutputStream) {
    val buf = ByteArray(8192)
    var length: Int
    while (source.read(buf).also { length = it } > 0) {
        target.write(buf, 0, length)
    }
}



//suspend fun Context.loadPdf(
//    inputStream: InputStream,
//    loadingListener: (
//        isLoading: Boolean,
//        currentPage: Int?,
//        maxPage: Int?
//    ) -> Unit = { _, _, _ -> }
//): List<String> = withContext(Dispatchers.Default) {
//    loadingListener(true, null, null)
//    val outputDir = cacheDir
//    val tempFile = File.createTempFile("temp", "pdf", outputDir)
//    tempFile.mkdirs()
//    tempFile.deleteOnExit()
//    val outputStream = FileOutputStream(tempFile)
//    copy(inputStream, outputStream)
//    val input = ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY)
//    val renderer = PdfRenderer(input)
//    (0 until renderer.pageCount).map { pageNumber ->
//        loadingListener(true, pageNumber, renderer.pageCount)
//        val file = File.createTempFile("PDFpage$pageNumber", "png", outputDir)
//        file.mkdirs()
//        file.deleteOnExit()
//        val page = renderer.openPage(pageNumber)
//        val bitmap = Bitmap.createBitmap(1240, 1754, Bitmap.Config.ARGB_8888)
//        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
//        page.close()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(file))
//        Log.i("PDF_VIEWER", "Loaded page $pageNumber")
//        file.absolutePath.also { Log.d("TESTE", it) }
//    }.also {
//        loadingListener(false, null, renderer.pageCount)
//        renderer.close()
//    }
//}
