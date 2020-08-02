package kr.or.connect.production.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LogInInfoMapArgumentResolver implements HandlerMethodArgumentResolver {
	
	@Override
		public boolean supportsParameter(MethodParameter parameter) {
			
			return parameter.getParameterType() == LogInInfo.class;
		}
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		LogInInfo loginInfo = new LogInInfo();
		
		//String email = webRequest.get
		
		return null;
	}

	

}
