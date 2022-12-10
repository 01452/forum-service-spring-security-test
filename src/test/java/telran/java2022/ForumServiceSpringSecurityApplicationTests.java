package telran.java2022;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import telran.java2022.accounting.controller.UserAccountController;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.dto.UserAccountResponseDto;
import telran.java2022.accounting.dto.UserRegisterDto;
import telran.java2022.accounting.model.UserAccount;
import telran.java2022.accounting.service.UserAccountService;
import telran.java2022.accounting.service.UserAccountServiceImpl;

import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ForumServiceSpringSecurityApplicationTests {

	@Mock
	private UserAccountRepository repository;

	@InjectMocks
	private UserAccountServiceImpl service;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private UserAccountController controller;

//	@BeforeEach
//	public void setUp() throws Exception {
//		MockitoAnnotations.openMocks(this);
//		controller = new UserAccountController(service);
//	}

//	@Test
//	public void contextLoads() throws Exception {
////		assertThat(controller).isNotNull();
//	}

//	@Test
//	public void shouldReturnFullNameOfAPerson() throws Exception {
////		UserAccount peter = new UserAccount("test1", "1234", "firstName", "lastName");
//		Set<String> roles = new HashSet<>();
//		roles.add("USER");
////		BDDMyOngoingStubbing<UserAccountResponseDto> us = 
//		BDDMyOngoingStubbing<UserAccountResponseDto> ggs = given(service.addUser(new UserRegisterDto("test1", "1234", "firstName", "lastName"))).willReturn(new UserAccountResponseDto("test1", "firstName", "lastName", roles));
////		 when(mockedProductVerifier.isCurrentlyInStockOfCompetitor("AirPods"))
////	      .thenReturn(true); //Specify what boolean value to return
////	 
////	    PricingService cut = new PricingService(mockedProductVerifier);
////	 	
////	    assertEquals(new BigDecimal("99.99"), cut.calculatePrice("AirPods"));
//		UserAccountResponseDto ppAccountResponseDto = controller.register(new UserRegisterDto("test23", "1234", "firstName", "lastName"));
////				service.addUser(new UserRegisterDto("test1", "1234", "firstName", "lastName"));
//		UserAccountResponseDto gg = (UserAccountResponseDto) when(service.addUser(new UserRegisterDto("test1", "1234", "firstName", "lastName"))).thenReturn(new UserAccountResponseDto("test1", "firstName", "lastName", roles));
////		service = new UserAccountServiceImpl(service);
////	    UserAccountServiceImpl cut = new UserAccountServiceImpl(repository);
//		 
////		UserAccountResponseDto us = service.addUser(new UserRegisterDto("test1", "1234", "firstName", "lastName"));
//		UserAccountResponseDto userAccountResponseDto = new UserAccountResponseDto("test12", "firstName", "lastName", roles);
//
//		assertEquals(userAccountResponseDto, service.addUser(new UserRegisterDto("test1", "1234", "firstName", "lastName")));
////		assertEquals(us, userAccountResponseDto);
////		assertThat(us.equals(userAccountResponseDto));
//	}

//	@Test
	public void UserAccountingAddUser() {
		Set<String> roles = new HashSet<>();
		roles.add("USER");
		UserAccount user = new UserAccount("test23", "1234", "firstName", "lastName");

//		UserAccountResponseDto userAccountResponseDto = UserAccountResponseDto.builder().login("test1").firstName("firstName")
//				.lastName("lastName").roles(roles).build();
		UserRegisterDto userRegisterDto = UserRegisterDto.builder().login("test1").password("1234")
				.firstName("firstName").lastName("lastName").build();

		when(repository.save(Mockito.any(UserAccount.class))).thenReturn(user);

		UserAccountResponseDto savedPokemon = service.addUser(userRegisterDto);

		Assertions.assertThat(savedPokemon).isNotNull();
	}

	@Test
	public void UserAccountingGetUser() {
		String login = "login";
		
		UserAccount user = new UserAccount("login", "1234", "firstName", "lastName"); 
//		repository.save(user);
		Optional<UserAccount> i = repository.findById(login);
//		UserAccountResponseDto userAccountResponseDto = UserAccountResponseDto.builder().login("test1").firstName("firstName")
//				.lastName("lastName").roles(roles).build();
//		UserRegisterDto userRegisterDto = UserRegisterDto.builder().login("test1").password("1234").firstName("firstName").lastName("lastName").build();

		when(repository.findById(login)).thenReturn(Optional.ofNullable(user));

		UserAccountResponseDto savedPokemon = service.getUser(login);

		Assertions.assertThat(savedPokemon).isNotNull();
	}
}
