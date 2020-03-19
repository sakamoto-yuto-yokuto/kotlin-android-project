package jp.co.kotlintemplate.definition

class ApiConfig {
    companion object {
        const val CONNECTION_TIMEOUT_SEC: Long = 30
        const val READ_TIMEOUT_SEC: Long = 30
    }
}

class DbConfig {
    companion object {
        const val DB_NAME = "TemplateDb"
        const val DB_VERSION_1 = 1
        const val DB_VERSION_NEWEST = DB_VERSION_1
    }
}