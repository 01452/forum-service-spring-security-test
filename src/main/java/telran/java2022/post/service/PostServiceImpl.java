package telran.java2022.post.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.java2022.post.dao.PostRepository;
import telran.java2022.post.dto.DatePeriodDto;
import telran.java2022.post.dto.NewCommentDto;
import telran.java2022.post.dto.NewPostDto;
import telran.java2022.post.dto.PostDto;
import telran.java2022.post.dto.exceptions.PostNotFoundException;
import telran.java2022.post.model.Comment;
import telran.java2022.post.model.Post;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

	final PostRepository postRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addNewPost(NewPostDto newPost, String author) {
		Post post = modelMapper.map(newPost, Post.class);
		post.setAuthor(author);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto getPost(String id) {
		log.info("post with id {} handled", id);
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		log.info("post with id {} handled", id);
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(NewPostDto postUpdateDto, String id) {
		log.info("post with id {} handled", id);
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		String content = postUpdateDto.getContent();
		if (content != null) {
			post.setContent(content);
		}
		String title = postUpdateDto.getTitle();
		if (title != null) {
			post.setTitle(title);
		}
		Set<String> tags = postUpdateDto.getTags();
		if (tags != null) {
			tags.forEach(post::addTag);
		}
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		log.info("post with id {} handled", id);
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
		log.info("post with id {} handled", id);
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = new Comment(author, newCommentDto.getMessage());
		post.addComment(comment);
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public Iterable<PostDto> findPostsByAuthor(String author) {
		return postRepository.findByAuthorIgnoreCase(author).map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PostDto> findPostsByTags(List<String> tags) {
		return postRepository.findByTagsInIgnoreCase(tags).map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PostDto> findPostsByDates(DatePeriodDto datePeriodDto) {
		return postRepository.findByDateCreatedBetween(datePeriodDto.getDateFrom(), datePeriodDto.getDateTo())
				.map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
	}

}
