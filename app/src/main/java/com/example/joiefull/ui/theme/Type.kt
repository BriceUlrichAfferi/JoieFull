package com.example.joiefull.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.example.joiefull.R
import androidx.compose.ui.text.googlefonts.Font as GoogleFont
import androidx.compose.ui.text.googlefonts.GoogleFont as GoogleGoogleFont

// Define Open Sans font family using Google Fonts
val OpenSans = FontFamily(
    GoogleFont(
        googleFont = GoogleGoogleFont("Open Sans"),
        fontProvider = GoogleGoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        ),
        weight = FontWeight.Normal
    ),
    GoogleFont(
        googleFont = GoogleGoogleFont("Open Sans"),
        fontProvider = GoogleGoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        ),
        weight = FontWeight.Bold
    ),
    GoogleFont(
        googleFont = GoogleGoogleFont("Open Sans"),
        fontProvider = GoogleGoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        ),
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    )
)

// Define default Material Typography and include both fonts
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Old default font
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = OpenSans, // Open Sans font for medium text
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    titleLarge = TextStyle(
        fontFamily = OpenSans, // Open Sans font for large titles
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
)
