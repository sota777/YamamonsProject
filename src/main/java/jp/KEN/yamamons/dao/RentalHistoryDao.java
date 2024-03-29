//COMMIT用
package jp.KEN.yamamons.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.entity.Order;
import jp.KEN.yamamons.entity.OrderItems;
import jp.KEN.yamamons.entity.RentalHistory;

@Component
public class RentalHistoryDao {//レンタル履歴閲覧画面のDAO
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private RowMapper<Members> membersMapper = new BeanPropertyRowMapper<Members>(Members.class);
	private RowMapper<Order> ordersMapper = new BeanPropertyRowMapper<Order>(Order.class);
	private RowMapper<Items> itemsMapper = new BeanPropertyRowMapper<Items>(Items.class);
	private RowMapper<OrderItems> orderItemsMapper = new BeanPropertyRowMapper<OrderItems>(OrderItems.class);
	private RowMapper<RentalHistory> rentalHistoryItemsMapper = new BeanPropertyRowMapper<RentalHistory>(RentalHistory.class);

	/*削除予定
	//レンタル履歴をt_customerに追加するメソッド
	public void toAddRentalHistory(String loginMail) {
		String sql = "UPDATE t_customer SET rentalHistory = 5 WHERE mail = ?";
		Object[] parameters = { loginMail };
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

	}
	*/

	/*削除予定
		//履歴を表示するためのメソッド
		public List<Items> getHistoryByLoginMail(String loginMail) {
			String sql = "SELECT itemNo, itemName, itemPicture FROM t_item WHERE itemNo = (SELECT rentalHistory FROM t_customer WHERE mail = ?) ;";
			Object[] parameters = { loginMail };
			//TransactionStatus transactionStatus = null;
			//DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			//transactionStatus = transactionManager.getTransaction(transactionDefinition);
			List<Items> items = jdbcTemplate.query(sql, parameters, itemsMapper);
			//transactionManager.commit(transactionStatus);
			return items;

		}


		*/

	//レンタルした商品を抽出するためのメソッド
	public List<Items> getRentalItem() {
		String sql = "SELECT itemNo FROM t_item2 ";
		List<Items> items = jdbcTemplate.query(sql, itemsMapper);
		return items;
	}

	//レンタルした商品をt_orderに追加するメソッド
	public void addOrder(List<Order> orderList) {
		String sql = "INSERT INTO t_order(orderDate,itemNo,customerId,orderQuantity,rentalStatusNo) VALUES(CURDATE(),?,?,?,?);";

		for (Order order : orderList) {
			Object[] parameters = { order.getItemNo(), order.getCustomerId(), order.getOrderQuantity(),
					order.getRentalStatusNo() };

			TransactionStatus transactionStatus = null;
			DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			int numberRow = 0;
			try {
				transactionStatus = transactionManager.getTransaction(transactionDefinition);
				numberRow = jdbcTemplate.update(sql, parameters);
				System.out.println(numberRow + "おはよ");
				transactionManager.commit(transactionStatus);
			} catch (DataAccessException e) {
				e.printStackTrace();
				transactionManager.rollback(transactionStatus);
				//System.out.println(numberRow);
			} catch (TransactionException e) {
				e.printStackTrace();
				if (transactionStatus != null) {
					transactionManager.rollback(transactionStatus);
				}
			}
		}
		return;
	}

	//顧客IDと商品Idからその人のレンタル商品履歴を表示するメソッド
	public Order getHistoryByCustomerId(String cusId, String itemNo) {
		String sql = "SELECT * FROM t_order WHERE customerId=? AND itemNo=? LIMIT 1";
		Object[] parameters = { cusId, itemNo };
		try {
			Order orderHisNo = jdbcTemplate.queryForObject(sql, parameters, ordersMapper);
			return orderHisNo;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	//顧客IDからその人のレンタル商品履歴を表示するメソッド
	public List<OrderItems> getHistoryByCustomerIdOnly(String customerId) {
		String sql = "SELECT t_item.itemName, t_item.itemPicture, t_item.director, t_order.orderDate, t_order.rentalStatusNo FROM t_order JOIN t_item ON t_order.itemNo = t_item.itemNo WHERE t_order.customerId = ?;";
		Object[] parameters = { customerId };
		try {
			List<OrderItems> orderHistory = jdbcTemplate.query(sql, parameters, orderItemsMapper);
			return orderHistory;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	//未返却の商品情報をt_orderとt_itemから取り出す
	public List<RentalHistory> getOrderListNotReturn(){
		String sql = " SELECT t_item.itemName, t_item.itemPicture, t_order.orderDate,t_order.orderNo, t_order.customerId, t_customer.customerName FROM t_order JOIN t_item ON t_order.itemNo = t_item.itemNo JOIN t_customer ON t_order.customerId = t_customer.customerId WHERE t_order.rentalStatusNo = 1;";
		try {
			List<RentalHistory> itemsList = jdbcTemplate.query(sql, rentalHistoryItemsMapper);
			return itemsList;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}






}