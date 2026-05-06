package com.example.app_admin.technicians.model

data class TransactionData(val title: String,
                           val amount: String,
                           val date: String,
                           val status: String,
                           val isPositive: Boolean)

val transactions = listOf(
    TransactionData("إيراد خدمة سحب سيارة",
        "150"
        , "22 فبراير، 10:30 م",
        "مكتمل", true),
    TransactionData("عمولة المنصة", "15-",
        "21 فبراير، 04:15 م",
        "مخصوم",
        false),
    TransactionData("تحويل بنكي - سحب",
        "500", "20 فبراير، 11:00 ص",
        "قيد المعالجة", false),
    TransactionData("خدمة إصلاح إطار",
        "80", "19 فبراير، 09:45 م",
        "مكتمل", true)
)