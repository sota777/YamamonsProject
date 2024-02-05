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

import jp.KEN.yamamons.entity.Order;

@Component
public class OrdersDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private RowMapper<Order> orderMapper = new BeanPropertyRowMapper<Order>(Order.class);

	//t_orderの一覧を返すためのメソッド
	public List<Order> getItemsList() {
		String sql = "SELECT * FROM t_item";
		List<Order> ordersList = jdbcTemplate.query(sql, orderMapper);
		return ordersList;
	}

	//注文情報を登録するためのメソッド
	public int insertOrder(Order order) {
		String sql = "INSERT INTO t_order(orderDate,itemNo,customerId,orderQuantity,rentalStatusNo) VALUES(?,?,?,?,?);";

		Object[] parameters = { order.getOrderDate(), order.getItemNo(), order.getCustomerId(),
				order.getOrderQuantity(), order.getRentalStatusNo() };
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
