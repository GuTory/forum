package com.temalab.forum;

import com.temalab.forum.model.Category;
import com.temalab.forum.model.Comment;
import com.temalab.forum.model.Topic;
import com.temalab.forum.model.User;
import com.temalab.forum.repository.CategoryRepository;
import com.temalab.forum.repository.CommentRepository;
import com.temalab.forum.repository.TopicRepository;
import com.temalab.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TopicRepository topicRepository;

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		Category category = new Category();
		Topic topic = new Topic();
		User elon = new User();
		Comment comment = new Comment();

		try {
			userRepository.save(user);
			userRepository.save(elon);
			categoryRepository.save(category);
			commentRepository.save(comment);
			topicRepository.save(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}

		user.setUserName("Gutori");
		user.setPassword("1234");

		
		category.setName("Twitter posts");

		
		topic.addCategory(category);
		topic.setName("Why does my blue badge cost 8$?");
		topic.setIssuer(user);


		elon.setUserName("Elon Musk");
		elon.setPassword("tesla");

		
		comment.setResponse("bc I wanna be a trillionaire");
		comment.setRespondent(elon);
		comment.setTopic(topic);

		try {
			userRepository.save(user);
			userRepository.save(elon);
			categoryRepository.save(category);
			commentRepository.save(comment);
			topicRepository.save(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
