package com.example.content.job

import com.example.content.config.AbstractJobTestConfiguration
import com.example.content.config.MainDataSourceTestConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig


@SpringJUnitConfig(
    classes = [
        RunningJob4Configuration::class,
        MainDataSourceTestConfiguration::class
    ]
)
class RunningJob4ConfigurationTest : AbstractJobTestConfiguration() {

    @Test
    fun `Step 테스트 케이스`() {

        val exec = jobLauncherTestUtils.launchStep("runningStep4")

        assertEquals(BatchStatus.COMPLETED, exec.status)
    }

}
