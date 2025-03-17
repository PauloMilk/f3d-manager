package br.com.paulocosta.f3dmanager

import br.com.paulocosta.f3dmanager.config.TestContainerConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerConfig::class])
class F3dManagerApplicationTests {

    @Test
    fun contextLoads() {
    }

}
