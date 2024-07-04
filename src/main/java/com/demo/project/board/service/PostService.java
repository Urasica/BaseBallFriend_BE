package com.demo.project.board.service;

import com.demo.project.board.model.Post;
import com.demo.project.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService { // 게시글 저장과 수정, 삭제에 검증 절차 필요, 로그인 고려
    @Autowired
    private PostRepository postRepository;

    // 모든 게시글 찾기
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 id의 게시글 찾기 (변경 필요: 일부 닉네임, 제목 등으로 찾기)
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // 게시글 저장
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // 게시글 업데이트
    public Post updatePost(Long id, Post PostDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if(post != null) {
            post.setTitle(PostDetails.getTitle());
            post.setContent(PostDetails.getContent());
            return postRepository.save(post);
        }
        return null;
    }

    // 게시글 지우기
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
