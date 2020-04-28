package com.tripshots.gateway.controller.proxy;


import com.tripshots.gateway.controller.ApiRequest;
import com.tripshots.gateway.entity.User;
import com.tripshots.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/s2/post")
public class PostsService {


    @Value("${microservice.posts-service.ip}")
    private String postsServiceIp;

    @Autowired
    private UserService userService;

    @GetMapping("/{postId}")
    public ResponseEntity getPost(@PathVariable String postId) {
        Map<String, String> params = new HashMap<>();
        params.put("postId", postId);
        return ApiRequest.get(postsServiceIp , params);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllPost(@RequestParam("username") String username) {
        User user = userService.findUserByUsername(username);
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(user.getId()));
        return ApiRequest.get(postsServiceIp+"getAll" , params);
    }

    @GetMapping(path = "/place")
    public ResponseEntity getPostsByPlaceId(@RequestParam("placeId") String placeId) {
        Map<String, String> params = new HashMap<>();
        params.put("placeId", placeId);
        return ApiRequest.get(postsServiceIp+"place", params);
    }

    @GetMapping(path = "/rating")
    public ResponseEntity getPostsByRating(@RequestParam("rating") String rating)  {
        Map<String, String> params = new HashMap<>();
        params.put("rating", rating);
        return ApiRequest.get(postsServiceIp+"rating", params);
    }

    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity createPost(@RequestBody Object requestBody)  {
        return ApiRequest.post(postsServiceIp, requestBody);
    }

    @DeleteMapping(path = "", consumes = "application/json")
    public ResponseEntity deletePost(@RequestBody Object requestBody)  {
        return ApiRequest.delete(postsServiceIp, requestBody);
    }

    @PutMapping(path = "", consumes = "application/json")
    public ResponseEntity updatePost(@RequestBody Object requestBody)  {
        return ApiRequest.put(postsServiceIp, requestBody);
    }
    
}
