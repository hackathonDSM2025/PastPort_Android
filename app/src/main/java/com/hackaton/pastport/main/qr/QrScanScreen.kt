package com.hackaton.pastport.main.qr

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.zxing.BarcodeFormat
import com.hackaton.pastport.R
import com.hackaton.pastport.ui.PastPortTopBar

@Composable
fun QrScanScreen(
    modifier: Modifier = Modifier,
    navToStory: () -> Unit
) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )
    LaunchedEffect(hasCameraPermission) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }


    var isScanSuccess by remember { mutableStateOf<Boolean?>(null) }

    if (isScanSuccess == true) {
        navToStory()
    } else if (isScanSuccess == false) {
        Toast.makeText(context, "QR코드 스캔에 실패했습니다.", Toast.LENGTH_SHORT).show()
        isScanSuccess = null
    }

    Column (
        modifier = modifier.fillMaxSize()
    ) {
        PastPortTopBar(
            text = stringResource(R.string.qr)
        )
        if (hasCameraPermission) {
            AndroidView(
                modifier = modifier.weight(1f).fillMaxWidth(),
                factory = {
                    val scannerView = CodeScannerView(it)
                    val scanner = CodeScanner(it, scannerView).apply {
                        camera = CodeScanner.CAMERA_BACK
                        formats = listOf(BarcodeFormat.QR_CODE)
                        autoFocusMode = AutoFocusMode.SAFE
                        scanMode = ScanMode.SINGLE
                        isAutoFocusEnabled = true
                        isFlashEnabled = false
                        decodeCallback = DecodeCallback { result ->
                            isScanSuccess = true
                        }
                        errorCallback = ErrorCallback { error ->
                            isScanSuccess = false
                        }
                    }
                    scanner.startPreview()
                    scannerView
                }
            )
        }
    }
}