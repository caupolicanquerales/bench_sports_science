package com.capo.bench_sports_science;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.capo.bench_sports_science.models.FileRegisterModel;
import com.capo.bench_sports_science.models.RawGarminRegisterModel;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@SpringBootTest(properties =
		"spring.autoconfigure.exclude=org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration")
class BenchSportsScienceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void shouldMapOneToOneRelationAndCreatedAtColumn() throws NoSuchFieldException {
		Field fileCreatedAtField = FileRegisterModel.class.getDeclaredField("createdAt");
		assertNotNull(fileCreatedAtField.getAnnotation(jakarta.persistence.Column.class));

		Field rawFileRegisterField = RawGarminRegisterModel.class.getDeclaredField("fileRegister");
		OneToOne oneToOne = rawFileRegisterField.getAnnotation(OneToOne.class);
		assertNotNull(oneToOne);

		JoinColumn joinColumn = rawFileRegisterField.getAnnotation(JoinColumn.class);
		assertNotNull(joinColumn);
		assertEquals("FILE_REGISTER_ID", joinColumn.name());
	}

}
