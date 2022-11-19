package telran.java2022.security.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.model.UserAccount;
import telran.java2022.post.dao.PostRepository;
import telran.java2022.post.model.Post;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {

	private final PostRepository postRepository;
	private final UserAccountRepository userAccountRepository;

	public boolean checkPassword(String userName) {
		UserAccount userAccount = userAccountRepository.findById(userName).orElse(null);
		return userAccount.getLocalDatePassword().plusDays(60).isAfter(LocalDate.now());
	}

	public boolean checkPostAuthor(String postId, String userName) {
		Post post = postRepository.findById(postId).orElse(null);
		return post != null && post.getAuthor().equalsIgnoreCase(userName);
	}
}