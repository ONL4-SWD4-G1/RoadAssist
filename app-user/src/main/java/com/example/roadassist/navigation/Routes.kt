package com.example.roadassist.navigation

object Routes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val SIGNUP = "signup"
    const val LOGIN = "login"
    const val OTP_SIGNUP = "otp_signup"
    const val RESET_PASSWORD = "reset_password"
    const val OTP_RESET = "otp_reset"
    const val CREATE_NEW_PASSWORD = "create_new_password"
    const val HOME = "home"
    const val SERVICES = "services"
    const val OTHER_SERVICE = "other_service"
    const val NOTIFICATIONS = "notifications"

    // Service Types
    const val SERVICE_TOWING = "service_towing"
    const val SERVICE_FLAT = "service_flat_tyre"
    const val SERVICE_FUEL = "service_fuel"
    const val SERVICE_BATTERY = "service_battery"
    const val SERVICE_BRAKE = "service_brake"
    const val SERVICE_ENGINE = "service_engine"
    const val SERVICE_KEY = "service_key"

    // Booking flow
    const val LOC_CONFIRM = "location_confirmation"
    const val TECHNICIANS = "available_technicians"
    const val TECH_PROFILE = "technician_profile/{technicianId}"
    fun techProfile(id: String) = "technician_profile/$id"
    const val MAP_PICKER = "map_picker"


    // Post-booking
    const val TRACK = "track"
    const val PAYMENT = "payment"
    const val FEEDBACK = "feedback"

    // Profile section
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val SERVICE_HISTORY = "service_history"
    const val SUBSCRIPTION = "subscription"
    const val SUBSCRIPTION_PLANS = "subscription_plans"
    const val ABOUT_US = "about_us"
    const val CONTACT = "contact"
    const val SETTINGS = "settings"
}