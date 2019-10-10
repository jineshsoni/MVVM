package com.thinkwik.boilerplate.network

import com.google.gson.*
import com.google.gson.internal.bind.TypeAdapters
import com.google.gson.internal.bind.TypeAdapters.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.json.JSONObject.NULL
import retrofit2.Response
import java.io.IOException

class DataDecoder {

    private var statusCode = 0
    private var message = ""


    private fun decodeError(input: String): String {
        try {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val json = gson.fromJson(input, ErrorData::class.java)
            message = json.message.toString()
        }catch (e:Exception){

        }
         return message
    }


    fun <T>decodeResponse(response: Response<T>, success: (T) -> Unit, error: (String, Int) -> Unit) {

        statusCode = response.code()
        when (statusCode) {
            200 -> {
                success(response.body()!!)
            }
            else -> {
                error(decodeError(response.errorBody()!!.string()), statusCode)
            }
        }
    }

}

data class ErrorData(val statusCode: Number?, val message: String?)

private val UNRELIABLE_INTEGER: TypeAdapter<Number> = object : TypeAdapter<Number>() {
    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Number? {
        val jsonToken = `in`.peek()
        when (jsonToken) {
            NUMBER, STRING, DOUBLE, JsonToken.STRING, JsonToken.NUMBER -> {
                val s = `in`.nextString()
                try {
                    return Integer.parseInt(s)
                } catch (ignored: NumberFormatException) {
                }

                try {
                    return java.lang.Double.parseDouble(s).toInt()
                } catch (ignored: NumberFormatException) {
                }

                return null
            }
            NULL -> {
                `in`.nextNull()
                return null
            }
            BOOLEAN -> {
                `in`.nextBoolean()
                return null
            }
            else -> throw JsonSyntaxException("Expecting number, got: $jsonToken")
        }
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Number) {
        out.value(value)
    }
}
private val UNRELIABLE_INTEGER_FACTORY =
    TypeAdapters.newFactory(Int::class.javaPrimitiveType, Int::class.java, UNRELIABLE_INTEGER)

fun myGson(): Gson {
    return GsonBuilder().registerTypeAdapterFactory(UNRELIABLE_INTEGER_FACTORY).create()!!
}
