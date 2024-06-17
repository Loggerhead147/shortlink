package com.allin.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.allin.shortlink.admin.common.convention.exception.ClientException;
import com.allin.shortlink.admin.common.convention.result.Results;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import static com.allin.shortlink.admin.common.enums.UserErrorCodeEnum.USER_TOKEN_FAIL;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    // TODO URI 里包含 admin，包含新增用户路径吗?
    private static final List<String> IGNORE_URI = Lists.newArrayList(
            "/api/short-link/admin/v1/user/login",
            "/api/short-link/admin/v1/user/has-username"
    );

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if (!IGNORE_URI.contains(requestURI)) {
            String method = httpServletRequest.getMethod();
            // 注册用户
            if (!(Objects.equals(requestURI, "/api/short-link/admin/v1/user") && Objects.equals(method, "POST"))) {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if (!StrUtil.isAllNotBlank(username, token)) {
                    returnJson((HttpServletResponse) servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                Object userInfoJsonStr;
                try {
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                    if (userInfoJsonStr == null) {
                        throw new ClientException(USER_TOKEN_FAIL);
                    }
                } catch (Exception ex) {
                    returnJson((HttpServletResponse) servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }


}