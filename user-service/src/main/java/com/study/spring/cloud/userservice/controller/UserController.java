package com.study.spring.cloud.userservice.controller;

import com.study.spring.cloud.userservice.dto.UserSaveDto;
import com.study.spring.cloud.userservice.dto.UserDto;
import com.study.spring.cloud.userservice.entity.User;
import com.study.spring.cloud.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RequestMapping(value = "/user-service")
@RestController
public class UserController {

    private final UserService userService;

    private final Environment environment;

    /***** CHECK *****/
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", environment.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }
    /***** CHECK *****/

    /**
     * 일괄 조회
     */
    @GetMapping
    public ResponseEntity<?> find() {
        return ResponseEntity.ok().body(UserDto.of(userService.find()));
    }

    /**
     * 단일 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> find(@PathVariable String userId) {
        return ResponseEntity.ok().body(UserDto.of(userService.find(userId)));
    }

    /**
     * 저장
     */
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody UserSaveDto userSaveDto) {
        User user = userService.saveUser(UserDto.of(userSaveDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUserId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
