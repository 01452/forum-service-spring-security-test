package telran.java2022;

import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.java2022.accounting.service.UserAccountServiceImpl;

//@Profile("test")
//@Configuration
//public class UserAccountingTest {
//	@Bean
//	public ModelMapper getModelMapper() {
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE)
//				.setMatchingStrategy(MatchingStrategies.STRICT);
//		return modelMapper;
//	}
//
//	@Bean
//	public PasswordEncoder getOasPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	@Bean
//	@Primary
//	public UserAccountServiceImpl UserAccountServiceImpl() {
//		return Mockito.mock(UserAccountServiceImpl.class);
//	}
//
//}
