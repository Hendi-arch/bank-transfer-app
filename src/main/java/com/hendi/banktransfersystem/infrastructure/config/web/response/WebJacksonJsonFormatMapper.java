package com.hendi.banktransfersystem.infrastructure.config.web.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.format.FormatMapper;
import org.hibernate.type.format.jackson.JacksonJsonFormatMapper;

/**
 * A custom implementation of the FormatMapper interface using Jackson library
 * for JSON serialization and deserialization.
 * 
 * Refer to this article for more details: https://howtodoinjava.com/jackson/java-8-date-time-type-not-supported-by-default/
 */
public class WebJacksonJsonFormatMapper implements FormatMapper {

	private final FormatMapper delegate;

	/**
	 * Constructs a new WebJacksonJsonFormatMapper object.
	 */
	public WebJacksonJsonFormatMapper() {
		ObjectMapper objectMapper = createObjectMapper();
		delegate = new JacksonJsonFormatMapper(objectMapper);
	}

	/**
	 * Creates and configures an ObjectMapper instance with JavaTimeModule and
	 * serialization feature settings.
	 *
	 * @return The configured ObjectMapper instance.
	 */
	private static ObjectMapper createObjectMapper() {
		return new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Override
	public <T> T fromString(CharSequence charSequence, JavaType<T> javaType, WrapperOptions wrapperOptions) {
		return delegate.fromString(charSequence, javaType, wrapperOptions);
	}

	@Override
	public <T> String toString(T t, JavaType<T> javaType, WrapperOptions wrapperOptions) {
		return delegate.toString(t, javaType, wrapperOptions);
	}

}
