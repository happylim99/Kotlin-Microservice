package com.sean.auth.config

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class InitializeData(
    val dataSource: DataSource
) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadData() {
        initPopulator();
    }

    private fun initPopulator() {
        val populator = createPopulator("init.sql")
        populator.execute(dataSource)
    }

//    private fun testPopulator() {
//        val populator = createPopulator("test.sql")
//        populator.execute(dataSource)
//    }

    private fun createPopulator(path: String) =
        ResourceDatabasePopulator(
            false,
            false,
            "UTF-8",
            ClassPathResource(path))
}