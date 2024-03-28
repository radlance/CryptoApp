plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.javax.inject)
    implementation(project(":domain"))

}