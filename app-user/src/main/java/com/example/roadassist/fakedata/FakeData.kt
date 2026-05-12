package com.example.roadassist.fakedata

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import com.example.roadassist.R
import com.example.roadassist.navigation.Routes

// ── User ─────────────────────────────────────────────────────────────────────

object User {
    const val NAME = "Alex Johnson"
    const val PHONE = "+91 00000 00000"
    val AVATAR = Icons.Outlined.Person
}

// ── Services ──────────────────────────────────────────────────────────────────

data class ServiceItem(
    val id: String,
    val label: String,
    val route: String,
    val suggestedPriceMin: Int,
    val suggestedPriceMax: Int,
    val priceNote: String,
)

val services = listOf(
    ServiceItem(
        Routes.ServiceIds.TOWING,
        "Towing",
        Routes.SERVICE_TOWING,
        80,
        150,
        "Varies by distance"
    ),
    ServiceItem(
        Routes.ServiceIds.FLAT_TYRE,
        "Flat tyre",
        Routes.SERVICE_FLAT,
        30,
        60,
        "Includes labour"
    ),
    ServiceItem(Routes.ServiceIds.FUEL, "Fuel", Routes.SERVICE_FUEL, 20, 50, "Fuel cost excluded"),
    ServiceItem(
        Routes.ServiceIds.BATTERY,
        "Battery",
        Routes.SERVICE_BATTERY,
        60,
        120,
        "Battery cost excluded"
    ),
    ServiceItem(
        Routes.ServiceIds.BRAKE,
        "Brake",
        Routes.SERVICE_BRAKE,
        50,
        100,
        "Parts cost excluded"
    ),
    ServiceItem(
        Routes.ServiceIds.ENGINE,
        "Engine",
        Routes.SERVICE_ENGINE,
        70,
        200,
        "Diagnosis + labour"
    ),
    ServiceItem(
        Routes.ServiceIds.KEY,
        "Key retrieval",
        Routes.SERVICE_KEY,
        40,
        90,
        "Includes locksmith fee"
    ),
    ServiceItem(
        Routes.ServiceIds.OTHERS,
        "Others",
        Routes.OTHER_SERVICE,
        30,
        100,
        "Final price set on-site"
    ),
)

// ── Nearby Garages ────────────────────────────────────────────────────────────

data class NearbyGarage(
    val id: String,
    val name: String,
    val location: String,
    val rating: Double,
    val distanceKm: Int,

    )

val nearbyGarages = listOf(
    NearbyGarage("g1", "SP Mechanics", "Perur, Cbe", 4.5, 6),
    NearbyGarage("g2", "City Auto Works", "RS Puram, Cbe", 4.2, 3),
    NearbyGarage("g3", "Quick Fix Garage", "Gandhipuram, Cbe", 4.7, 8),
)

// ── Notifications ─────────────────────────────────────────────────────────────

data class NotificationItem(
    val id: String,
    val message: String,
    val time: String,
)

val notifications = listOf(
    NotificationItem("n1", "Your service provider is on the way. Arriving in 5 mins.", "Now"),
    NotificationItem("n2", "5% discount on annual subscription", "30 min ago"),
    NotificationItem("n3", "10% discount on first service", "1 day ago"),
)

// ── Service History ───────────────────────────────────────────────────────────

data class ServiceHistoryItem(
    val id: String,
    val date: String,
    val service: String,
)

val serviceHistory = listOf(
    ServiceHistoryItem("h1", "23/12/2024", "Engine oil and filter replacement"),
    ServiceHistoryItem("h2", "2/10/2024", "Battery replacement"),
    ServiceHistoryItem("h3", "20/7/2024", "Fluid service"),
    ServiceHistoryItem("h4", "5/4/2024", "Flat tyre repair"),
)

// ── Technicians ───────────────────────────────────────────────────────────────

data class Technician(
    val id: String,
    val name: String,
    val role: String,
    val rating: Double,
    val distanceKm: Double,
    val etaMinutes: Int,
    val jobsDone: Int,
    val responseTime: String,
    val expertise: List<String>,
    val serviceArea: String,
    val experienceYears: Int,
    val about: String,
    val image: Int,
)

val technicians = listOf(
    Technician(
        id = "t1",
        name = "Marco Valeri",
        role = "Senior Mechanical Engineer",
        rating = 4.9,
        distanceKm = 1.2,
        etaMinutes = 10,
        jobsDone = 2840,
        responseTime = "< 5m",
        expertise = listOf(
            "Engine Repair",
            "Brake Specialist",
            "EV Certified",
            "Diagnostic Expert"
        ),
        serviceArea = "Downtown, Central City",
        experienceYears = 12,
        about = "Specializes in complex engine diagnostics, performance tuning, and electric vehicle powertrain maintenance. Certified Master Technician with specialized training in European luxury models and precision brake systems.",
        image = R.drawable.tech_1,
    ),
    Technician(
        id = "t2",
        name = "Sarah Jenkins",
        role = "Brake Specialist",
        rating = 4.8,
        distanceKm = 2.5,
        etaMinutes = 15,
        jobsDone = 1530,
        responseTime = "< 8m",
        expertise = listOf("Brake Specialist", "Suspension", "ABS Systems"),
        serviceArea = "North Side, Central City",
        experienceYears = 8,
        about = "Expert in brake system diagnostics, pad/rotor replacement, and ABS module repair. Trained at Bosch Automotive Academy.",
        image = R.drawable.tech_2,
    ),
    Technician(
        id = "t3",
        name = "David Chen",
        role = "Diagnostic Expert",
        rating = 5.0,
        distanceKm = 3.1,
        etaMinutes = 18,
        jobsDone = 3200,
        responseTime = "< 3m",
        expertise = listOf("Diagnostics", "Electrical Systems", "Engine Repair"),
        serviceArea = "East Side, Central City",
        experienceYears = 15,
        about = "Veteran diagnostics engineer with 15 years of experience. Specializes in electronic control systems and engine diagnostics across all major manufacturers.",
        image = R.drawable.tech_3,
    ),
)

// ── Subscription Plans ────────────────────────────────────────────────────────

data class SubscriptionPlan(
    val id: String,
    val name: String,
    val annualFee: Int,
    val features: List<String>,
    val isCurrent: Boolean = false,
)

val subscriptionPlans = listOf(
    SubscriptionPlan(
        id = "standard",
        name = "Standard plan",
        annualFee = 200,
        features = listOf(
            "Unlimited assistance calls",
            "Priority service",
            "Extended roadside assistance (fuel delivery, lockout service)",
            "24/7 customer support",
        ),
        isCurrent = true,
    ),
    SubscriptionPlan(
        id = "premium",
        name = "Premium plan",
        annualFee = 300,
        features = listOf(
            "All Standard Plan benefits",
            "VIP service",
            "Additional services (e.g., accident recovery)",
            "Exclusive discounts on partner services support",
        ),
    ),
)

// ── Contact ───────────────────────────────────────────────────────────────────

object Contact {
    const val PHONE = "91 ***** *****"
    const val EMAIL = "***********@*****"
    const val ADDRESS = "42, 2nd floor, TK Plazza, Thiru street, Metur."
}

// ── Vehicle Details ───────────────────────────────────────────────────────────

data class VehicleDetails(
    val vehicleType: String = "",
    val manufacturer: String = "",
    val model: String = "",
    val registration: String = "",
)
