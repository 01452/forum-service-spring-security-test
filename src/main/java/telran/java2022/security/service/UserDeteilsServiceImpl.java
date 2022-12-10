package telran.java2022.security.service;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserDeteilsServiceImpl implements UserDetailsService {
	HttpServletResponse response;
	HttpServletRequest request;
	final UserAccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccount = repository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		if(userAccount.getLocalDatePassword().isAfter(LocalDate.now())){
			userAccount.addRole("timepassword");
		}
		String[] roles = userAccount.getRoles().stream().map(t -> "ROLE_" + t.toUpperCase()).toArray(String[]::new);
		Boolean enabled = true;
		Boolean accountNonExpired = true;
		Boolean credentialsNonExpired = true;
		Boolean accountNonLocked = true;
		return new User(username, userAccount.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, AuthorityUtils.createAuthorityList(roles));
	}

}
