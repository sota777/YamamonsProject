package jp.KEN.yamamons.dao;

import org.springframework.stereotype.Component;

import jp.KEN.yamamons.entity.Members;

@Component
public class LoginDao {

	public int insert(Members members) {
		String spl ="INSERT INTO members(customerName,address,tel,mail,creditNo,planNo,password) VALUES(?,?,?,?,?,?,?);";

	}


}
