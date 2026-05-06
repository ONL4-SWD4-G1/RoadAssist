package com.example.app_admin

import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.complaints.model.ComplaintStatus
import com.example.app_admin.finance.model.TransactionData
import com.example.app_admin.technicians.model.Technician
import com.example.app_admin.technicians.model.TechnicianStatus

val sampleTechnicians = listOf(
    Technician(
        id = 1,
        name = "أحمد محمد",
        specialty = "فني كهرباء سيارات",
        status = TechnicianStatus.ACTIVE,
        rating = 4.9,
        completedJobs = 150,
        city = "الرياض",
        phone = "+966501234567",
        experience = 7
    ),
    Technician(
        id = 2,
        name = "ياسين عبدالله",
        specialty = "ميكانيكي محركات ديزل",
        status = TechnicianStatus.WAITING,
        rating = 4.7,
        completedJobs = 88,
        city = "جدة",
        phone = "+966502234567",
        experience = 5
    ),
    Technician(
        id = 3,
        name = "خالد عمر",
        specialty = "فني إطارات وبطاريات",
        status = TechnicianStatus.SUSPENDED,
        rating = 3.2,
        completedJobs = 12,
        city = "الدمام",
        phone = "+966503234567",
        experience = 2
    ),
    Technician(
        id = 4,
        name = "مازن العتيبي",
        specialty = "فني تبريد وتكييف سيارات",
        status = TechnicianStatus.NEW_REQUEST,
        rating = 0.0,
        completedJobs = 0,
        city = "الدمام",
        phone = "+966504234567",
        experience = 8,
        hasCar = true
    )
)
val sampleComplaints = listOf(
    Complaint(
        id = 1,
        clientName = "سارة أحمد",
        technicianName = "أحمد محمد",
        issueType = "بطارية / كهرباء",
        status = ComplaintStatus.OPEN,
        timeAgo = "منذ ساعتين"
    ),
    Complaint(
        id = 2,
        clientName = "محمد القحطاني",
        technicianName = "ياسين عبدالله",
        issueType = "نظام المحرك",
        status = ComplaintStatus.UNDER_REVIEW,
        timeAgo = "منذ 5 ساعات"
    ),
    Complaint(
        id = 3,
        clientName = "عبدالله منصور",
        technicianName = "فهد العتيبي",
        issueType = "إطارات / بنشر",
        status = ComplaintStatus.RESOLVED,
        timeAgo = "أمس"
    )
)
val transactions = listOf(
    TransactionData(
        title = "إيراد خدمة سحب سيارة",
        amount = "150",
        date = "22 فبراير، 10:30 م",
        status = "مكتمل",
        isPositive = true
    ),
    TransactionData(
        title = "عمولة المنصة",
        amount = "15-",
        date = "21 فبراير، 04:15 م",
        status = "مخصوم",
        isPositive = false
    ),
    TransactionData(
        title = "تحويل بنكي - سحب",
        amount = "500",
        date = "20 فبراير، 11:00 ص",
        status = "قيد المعالجة",
        isPositive = false
    ),
    TransactionData(
        title = "خدمة إصلاح إطار",
        amount = "80",
        date = "19 فبراير، 09:45 م",
        status = "مكتمل",
        isPositive = true
    )
)