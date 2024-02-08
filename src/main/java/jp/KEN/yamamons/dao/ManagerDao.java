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

import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Order;

@Component
public class ManagerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private RowMapper<Order> ordersMapper = new BeanPropertyRowMapper<Order>(Order.class);

	//管理者が商品を追加するDao
	public int insertItem(Items items) {
		String sql = "INSERT INTO t_item(itemName,itemQuantity,genreNo,director,typeNo) VALUES(?,?,?,?,?);";
		Object[] parameters = { items.getItemName(),items.getItemQuantity(),items.getGenreNo(),items.getDirector(),
				items.getTypeNo() };

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

	//管理者が返却処理対応したとき、貸出し状況の変更処理
	public int updataStatusDao(Order order) {
		String sql = "UPDATE t_order SET rentalStatusNo=? WHERE orderNo=?;";
		Object[] parameters = { order.getRentalStatusNo(),order.getOrderNo()};

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

	//返却処理対応したときの在庫状況の変更処理
	public int updataItemQuaDao(Items items) {
		String sql = "UPDATE t_item SET itemQuantity=? WHERE itemNo=?;";
		Object[] parameters = { items.getItemQuantity(),items.getItemNo()};

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

	//商品ごと貸出し中のレコードの抽出
	public List<Order> getOrderItemNo(int i) {
		String sql = "SELECT * FROM t_order WHERE itemNo=? AND rentalStatusNo=1;";
		Object[] parameters = {i};
		System.out.println("Dao" + i);
		List<Order> orderList = jdbcTemplate.query(sql,parameters,ordersMapper);
		return orderList;
	}

}
