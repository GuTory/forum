package com.temalab.forum.services;

import com.temalab.forum.model.Comment;
import com.temalab.forum.model.Topic;
import com.temalab.forum.model.User;
import com.temalab.forum.repository.CommentRepository;
import com.temalab.forum.repository.TopicRepository;
import com.temalab.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public void deleteAll(){
        commentRepository.deleteAll();
    }

    public void deleteEntity(Long id){
        commentRepository.deleteById(id);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public void createEntity(String response,
                             Long topicId,
                             Long responseToId,
                             Long responseComment,
                             Long respondentId) {
        Optional<User> user = userRepository.findById(respondentId);
        Optional<Comment> responseToComment = commentRepository.findById(responseToId);
        Optional<Comment> responseCom = commentRepository.findById(responseComment);
        Optional<Topic> topic = topicRepository.findById(topicId);
        if(user.isEmpty() || responseToComment.isEmpty() || topic.isEmpty()) return;

        Comment newComment = new Comment();
        newComment.setResponse(response);

        responseCom.ifPresent(newComment::setResponseComment);
        newComment.setRespondent(user.get());
        newComment.setResponseTo(responseToComment.get());
        newComment.setTopic(topic.get());
        commentRepository.save(newComment);
    }

    public void updateEntity(Long id,
                             String response,
                             Long topicId,
                             Long responseTo,
                             Long responseComment,
                             Long respondent) {
        Optional<User> user = userRepository.findById(respondent);
        Optional<Comment> responseToComment = commentRepository.findById(responseTo);
        Optional<Comment> responseCom = commentRepository.findById(responseComment);
        Optional<Topic> topic = topicRepository.findById(topicId);
        if(user.isEmpty() || responseToComment.isEmpty() || topic.isEmpty()) return;

        Optional<Comment> optComment = commentRepository.findById(id);
        if(optComment.isEmpty()) return;
        var c = optComment.get();
        c.setResponse(response);

        responseCom.ifPresent(c::setResponseComment);
        c.setRespondent(user.get());
        c.setResponseTo(responseToComment.get());
        c.setTopic(topic.get());
        commentRepository.save(c);
    }
}
