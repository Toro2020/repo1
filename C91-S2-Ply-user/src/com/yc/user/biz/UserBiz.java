package com.yc.user.biz;

import java.sql.SQLException;

import com.yc.user.bean.User;
import com.yc.user.dao.UserDao;
/**
 *  用户的业务类
 * @author 24459
 *
 */
public class UserBiz {

	private UserDao udao = new UserDao();
	
	public void register(User user) throws BizException {
		if(user.getName()==null || user.getName().isEmpty()) {
			throw new BizException("请填写用户名！");
		}
		if(user.getPhone()==null || user.getPhone().isEmpty()) {
			throw new BizException("请填写电话！");
		}
		if(user.getEmail()==null || user.getEmail().isEmpty()) {
			throw new BizException("请填写邮箱！");
		}
		if(user.getPwd()==null || user.getPwd().isEmpty()) {
			throw new BizException("请填写密码！");
		}
		
		
		try {
			//判断当前的用户名是否已经被注册
			int cnt = udao.countByName(user.getName());
			if(cnt > 0 ) {
				throw new BizException("该用户名已经被注册！");
			}
			udao.insert(user);
		} catch (SQLException e) {
			//业务异常的信息是给用户查看的
			throw new BizException("系统繁忙，请稍后再试！",e);
		}
	}
}
