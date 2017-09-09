//모든 요청파라미터 인코딩을 UTF8로 변환시켜주는 필터
//따로 요청파라미터를 변환시킬 필요가 없다
//2017.09.09 차준웅

package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		encoding = config.getInitParameter("encoding");
		if(encoding == null){
			encoding = "UTF-8";
		}
	}
	
	@Override
	public void destroy(){
	}

}
