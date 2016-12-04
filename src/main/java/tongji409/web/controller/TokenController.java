package tongji409.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import tongji409.authorization.annotation.Authorization;
import tongji409.authorization.annotation.CurrentUser;
import tongji409.authorization.manager.TokenManager;
import tongji409.authorization.model.Token;
import tongji409.config.ResultStatus;
import tongji409.domain.User;
import tongji409.model.ResultModel;
import tongji409.web.repository.UserRepository;

/**
 * @author lijiechu
 * @create on 16/12/3
 * @description
 */

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value = "登录",consumes="application/x-www-form-urlencoded")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value="用户名", paramType = "body",required = true ,dataType = "String"),
            @ApiImplicitParam(name = "password", value="用户密码", paramType = "body", required = true, dataType = "String")
    })
    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<ResultModel> login(@ApiParam(value = "用户名", required = true) @RequestParam String username, @ApiParam(value = "用户密码", required = true) @RequestParam String password) {
    public ResponseEntity<ResultModel> login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userRepository.findByUsername(username);
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        Token model = tokenManager.createToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
    }

    @ApiOperation(value = "用户登出", notes="根据token清除用户登录记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}
