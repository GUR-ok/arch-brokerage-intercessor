package ru.gur.archintercessor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = {"local", "test"})
class ArchBrokerageIntercessorApplicationTests {

	@Test
	void contextLoads() {
	}
}
