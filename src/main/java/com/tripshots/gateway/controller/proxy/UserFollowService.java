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
import java.util.Map;

@RestController
@RequestMapping(path = "/s1")
public class UserFollowService {

    @Value("${microservice.follow-service.ip}")
    private String userfollowIp;

    @Autowired
    private UserService userService;

    @GetMapping("/follow")
    public ResponseEntity followUser(@RequestParam(name = "username") String username){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Map<String, String> params = new HashMap<>();
        params.put("userId", Long.toString(user.getId()));
        params.put("followUsername", username);
        return ApiRequest.get(userfollowIp +"follow", params);
    }

    @GetMapping("/unfollow")
    public ResponseEntity unfollowUser(@RequestParam(name = "username") String username){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Map<String, String> params = new HashMap<>();
        params.put("userId", Long.toString(user.getId()));
        params.put("unfollowUsername", username);
        return ApiRequest.get(userfollowIp +"unfollow", params);
    }


    @GetMapping("/followers")
    public ResponseEntity getCurrentFollowers(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Map<String, String> params = new HashMap<>();
        params.put("userId", Long.toString(user.getId()));
        return ApiRequest.get(userfollowIp +"my-followers", params);
    }

    @GetMapping("/following")
    public ResponseEntity getCurrentFollowing(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Map<String, String> params = new HashMap<>();
        params.put("userId", Long.toString(user.getId()));
        return ApiRequest.get(userfollowIp +"my-following", params);
    }

    @GetMapping("/user-followers")
    public ResponseEntity getUserFollowers(@RequestParam(name = "username") String username){
        User user = userService.findUserByUsername(username);
        Map<String, String> params = new HashMap<>();
        params.put("userId", Long.toString(user.getId()));
        return ApiRequest.get(userfollowIp +"my-followers", params);
    }

    @GetMapping("/user-following")
    public ResponseEntity getUserFollowing(@RequestParam(name = "username") String username){
        User user = userService.findUserByUsername(username);
        Map<String, String> params = new HashMap<>();
        params.put("userId", Long.toString(user.getId()));
        return ApiRequest.get(userfollowIp +"my-following", params);
    }

}
