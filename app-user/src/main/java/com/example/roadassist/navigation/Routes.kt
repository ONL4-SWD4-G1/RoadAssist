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
    const val LOC_CONFIRM = "location_confirmation/{serviceId}"
    fun locConfirm(serviceId: String) = "location_confirmation/$serviceId"
    const val TECHNICIANS = "available_technicians/{serviceId}"
    fun technicians(serviceId: String) = "available_technicians/$serviceId"
    const val TECH_PROFILE = "technician_profile/{serviceId}/{technicianId}"
    fun techProfile(serviceId: String, technicianId: String) =
        "technician_profile/$serviceId/$technicianId"

    const val MAP_PICKER = "map_picker/{serviceId}"
    fun mapPicker(serviceId: String) = "map_picker/$serviceId"

    // Post-booking
    const val TRACK = "track/{serviceId}/{technicianId}"
    fun track(serviceId: String, technicianId: String) = "track/$serviceId/$technicianId"
    const val PAYMENT = "payment/{serviceId}/{technicianId}"
    fun payment(serviceId: String, technicianId: String) = "payment/$serviceId/$technicianId"
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

    object ServiceIds {
        const val TOWING = "towing"
        const val FLAT_TYRE = "flat_tyre"
        const val FUEL = "fuel"
        const val BATTERY = "battery"
        const val BRAKE = "brake"
        const val ENGINE = "engine"
        const val KEY = "key"
        const val OTHERS = "others"
    }

}