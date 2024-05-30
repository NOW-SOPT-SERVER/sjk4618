package org.sopt.springFirstSeminar.common.jwt.auth;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //요청받은 메소드의 파라미터에  @UserId 어노테이션이 붙어있는지 확인
        boolean hasUserIdAnnotation = parameter.hasParameterAnnotation(MemberId.class);

        //타입이 같은지 확인
        boolean isLongType = Long.class.isAssignableFrom(parameter.getParameterType());

        //둘 다 true면 아래 resolveArgument 메서드 실행
        return hasUserIdAnnotation && isLongType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
