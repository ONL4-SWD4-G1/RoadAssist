package com.example.app_admin.complaints.viewModel

import androidx.lifecycle.ViewModel
import com.example.app_admin.complaints.model.ComplaintTechnicianUiState
import com.example.app_admin.complaints.model.TechnicianOffer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TechnicianDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        ComplaintTechnicianUiState(
            name = "محمد سامي",
            specialty = "فني كهرباء سيارات",
            experience = "خبرة ٥ سنوات",
            phone = "+٩٦٦ ٥٠ XXX XXXX",
            offers = listOf(
                TechnicianOffer(
                    1,
                    "تحديث نطاق الخدمة",
                    "منذ ٢ ساعة",
                    "يرغب الفني في توسيع نطاق العمل ليشمل منطقة 'شمال الرياض'."
                ),
                TechnicianOffer(
                    2,
                    "تحديث التسعيرة",
                    "منذ ٥ ساعات",
                    "تغيير سعر فحص الكهرباء من ١٥٠ ر.س إلى ٢٠٠ ر.س."
                )
            )
        )
    )
    val uiState: StateFlow<ComplaintTechnicianUiState> = _uiState.asStateFlow()

    fun onAcceptOffer(offerId: Int) { /* Logic */
    }

    fun onRejectOffer(offerId: Int) { /* Logic */
    }
}