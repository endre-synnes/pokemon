package com.endre.pokemon

import com.codahale.metrics.MetricRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.concurrent.TimeUnit
import com.codahale.metrics.MetricFilter
import com.codahale.metrics.graphite.GraphiteReporter
import java.net.InetSocketAddress
import com.codahale.metrics.graphite.Graphite
import org.springframework.context.annotation.Profile


//import org.springframework.context.annotation.Bean
//import springfox.documentation.builders.PathSelectors
//import springfox.documentation.spi.DocumentationType
//import springfox.documentation.spring.web.plugins.Docket
//import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EntityScan(basePackages = ["com.endre.pokemon.model.entity"])
@EnableJpaRepositories(basePackages = ["com.endre.pokemon.repository"])
//@EnableSwagger2
class PokemonApplication {

//@Bean
//fun swaggerApi(): Docket {
//    return Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .paths(PathSelectors.any())
//            .build()
//}

    @Bean
    fun getRegistry(): MetricRegistry {
        return MetricRegistry()
    }

    @Bean
    @Profile("!test")
    fun getReporter(registry: MetricRegistry): GraphiteReporter {
        val graphite = Graphite(InetSocketAddress(System.getenv("GRAPHITE_HOST"), 2003))
        val reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith(System.getenv("HOSTEDGRAPHITE_APIKEY"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite)
        reporter.start(1, TimeUnit.SECONDS)
        return reporter
    }
}

fun main(args: Array<String>) {
    runApplication<PokemonApplication>(*args)
}
