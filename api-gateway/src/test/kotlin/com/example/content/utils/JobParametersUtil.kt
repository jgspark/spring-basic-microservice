package com.example.content.utils

import com.example.content.core.incrementer.DateIncrementer
import org.springframework.batch.core.JobParameters

class JobParametersUtil {

    fun defaultJobParameters(): JobParameters {
        val dateIncrementer = DateIncrementer()
        return dateIncrementer.getNext(null)
    }
}
