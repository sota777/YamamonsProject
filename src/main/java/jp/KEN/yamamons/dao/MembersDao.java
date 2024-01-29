package jp.KEN.yamamons.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.KEN.yamamons.entity.Members;

@Component
public class MembersDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private RowMapper<Members> membersMapper = new BeanPropertyRowMapper<Members>(Members.class);

	//t_customerの一覧を返すためのメソッド
	public List<Members> getCustomerList() {
		String sql = "SELECT * FROM t_customer";
		List<Members> membersList = jdbcTemplate.query(sql, membersMapper);
		return membersList;
	}

	//メールアドレスの完全一致検索
	//メールアドレスが一致する顧客のパスワードとメールアドレスを返す
	//Controller側で戻ってきたpasswordが、入力されたpasswordと一致するか確認する?
	public Members getCusMailByMail(String mail) {
		String sql = "SELECT mail,password FROM t_customer WHERE mail LIKE ?";
		mail = mail.replace("%","\\%").replace("_", "\\_");
		Object[] parameters = { mail };
		Members cusMail = (Members) jdbcTemplate.query(sql,parameters,membersMapper);
		return cusMail;
	}

	//顧客情報を登録するためのメソッド
	public int insertCus(Members members) {
		String sql = "INSERT INTO t_customer(customerName,address,tel,mail,creditNo,planNo,password) VALUES(?,?,?,?,?,?,?);";

		Object[] parameters = { members.getCustomerName(), members.getAddress(), members.getTel(), members.getMail(),
				members.getCreditNo(), members.getPlanNo(), members.getPassword() };
		TransactionStatus transactionStatus = null;
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		int numberRow = 0;
		try {
			transactionStatus = transactionManager.getTransaction(transactionDefinition);
			numberRow = jdbcTemplate.update(sql, parameters);
			transactionManager.commit(transactionStatus);
		} catch (DataAccessException e) {
			e.printStackTrace();
			transactionManager.rollback(transactionStatus);
		} catch (TransactionException e) {
			e.printStackTrace();
			if (transactionStatus != null) {
				transactionManager.rollback(transactionStatus);
			}
		}
		return numberRow;
	}
}
