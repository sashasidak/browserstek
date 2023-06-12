package constants

class Constants {

    object RunVariables {
        var PLATFORM = Platform.AOS_LOCAL
    }

    enum class Platform {
        IOS, AOS, IOS_LOCAL, AOS_LOCAL
    }

    object VRT {
        const val URL = "http://localhost:4200"
        const val API_KEY = "5GYYT429F0MEC2MNZ7R0BR7QJFHB"
        const val AOS_BRANCH_NAME = "master"
        const val AOS_PROJECT = "Default project"
        const val IOS_BRANCH_NAME = "master"
        const val IOS_PROJECT = "Default project"
    }


}
