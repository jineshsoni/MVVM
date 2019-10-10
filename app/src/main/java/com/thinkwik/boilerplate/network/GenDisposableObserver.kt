package com.thinkwik.boilerplate.network

import com.google.gson.JsonObject
import com.thinkwik.boilerplate.rx.RxBus
import com.thinkwik.boilerplate.rx.RxEvent
import com.thinkwik.boilerplate.utils.loge
import com.thinkwik.boilerplate.utils.logv
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

fun <T>genObserver(serviceClass: Class<T>, success: (T) -> Unit, error: (String, Int) -> Unit) = object : DisposableObserver<Response<T>>() {
    override fun onError(e: Throwable) {
        e.printStackTrace()
        RxBus.publish(RxEvent.EventErrorData(999, e.message!!))
        loge("onError  @999 " + e.message)
        error(e.message!!, 999)
    }

    override fun onNext(response: Response<T>) {
        DataDecoder().decodeResponse(response, success = { obj->
//            logv(" Success == $obj")
            success(obj)
        }, error = {msg, code->
            logv(" Error  == $msg && Code == $code")
            error(msg, code)
            RxBus.publish(RxEvent.EventErrorData(code, msg))
        })
    }

    override fun onComplete() {
        logv("onComplete ${serviceClass.name}")
    }

}