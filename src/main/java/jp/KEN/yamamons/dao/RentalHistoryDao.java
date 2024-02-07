
 package jp.KEN.yamamons.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Order;

@Component
public class RentalHistoryDao {//レンタル履歴閲覧画面のDAO
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private RowMapper<Order> membersMapper = new BeanPropertyRowMapper<Order>(Order.class);
	private RowMapper<Items> itemsMapper = new BeanPropertyRowMapper<Items>(Items.class);


	//t_orderの一覧を返すためのメソッド
	public List<Order> getOrderList() {
		String sql = "SELECT * FROM t_order";
		List<Order> getOrderList = jdbcTemplate.query(sql, membersMapper);
		return getOrderList;
	}



	//履歴を表示するためのメソッド
	public List<Items> getHistoryByCustomerName(String customerName) {
		String sql = "SELECT itemName, itemPicture FROM t_item WHERE itemNo = (SELECT rentalHistory FROM t_customer WHERE customerName = ?) ;";
		Object[] parameters = {customerName} ;
		//TransactionStatus transactionStatus = null;
		//DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		//transactionStatus = transactionManager.getTransaction(transactionDefinition);
		List<Items> items = jdbcTemplate.query(sql, parameters, itemsMapper);
		//transactionManager.commit(transactionStatus);
		return items;

	}

}

