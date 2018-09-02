-printmapping mapping.txt

# Retrofit 2
-dontwarn retrofit2.Platform$Java8

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Dagger
-dontwarn com.google.errorprone.annotations.*
