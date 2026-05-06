class Resource<T> // Constructor privado: solo se puede crear mediante los métodos estáticos
private constructor(val status: Status?, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        // Fábrica estática para estado de ÉXITO
        fun <T> success(data: T?): Resource<T?> {
            return Resource<T?>(Status.SUCCESS, data, null)
        }

        // Fábrica estática para ERROR, normalmente con un mensaje
        fun <T> error(msg: String?): Resource<T?> {
            return Resource<T?>(Status.ERROR, null, msg)
        }

        // Fábrica estática para el estado de CARGA
        fun <T> loading(): Resource<T?> {
            return Resource<T?>(Status.LOADING, null, null)
        }
    }
}