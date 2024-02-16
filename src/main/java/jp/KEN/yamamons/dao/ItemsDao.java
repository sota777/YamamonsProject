package jp.KEN.yamamons.dao;

import java.util.ArrayList;
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

@Component
public class ItemsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private RowMapper<Items> itemsMapper = new BeanPropertyRowMapper<Items>(Items.class);

	//t_itemの一覧を返すためのメソッド
	public List<Items> getItemsList() {
		String sql = "SELECT * FROM t_item";
		List<Items> itemsList = jdbcTemplate.query(sql, itemsMapper);
		return itemsList;
	}

	//商品情報を登録するためのメソッド
	public int insertItem(Items items) {
		String sql = "INSERT INTO t_item(itemName,itemQuantity,genreNo,director,typeNo,itemPicture) VALUES(?,?,?,?,?,?);";

		Object[] parameters = { items.getItemName(), items.getItemQuantity(), items.getGenreNo(), items.getDirector(),
				items.getTypeNo(), items.getItemPicture() };
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

	//商品番号から商品情報を取り出すメソッド
	public Items getItemsByNo(Integer itemNo) {
		String sql = "SELECT * FROM t_item WHERE itemNo=?";
		//INパラメータに使用する値の配列を生成
		Object[] parameters = { itemNo };
		try {
			//第二引数はIDでとってきた配列を入れる
			//queryForObjectは検索結果が一件の時
			Items items = jdbcTemplate.queryForObject(sql, parameters, itemsMapper);
			return items;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Items> getItemsExceptCart(ArrayList<String> cart) {
		String sql = "SELECT * FROM t_item WHERE itemNo NOT IN(select itemNo from t_item2)";
		//Object[] parameters = {cart};
		List<Items> items = jdbcTemplate.query(sql, itemsMapper);

		return items;
	}

	//商品番号の在庫数を取り出すメソッド
	public int toGetItemQuantity(String itemNo) {
		String sql = "SELECT itemQuantity FROM t_item WHERE itemNo=?";
		Object[] parameters = { itemNo };
		try {
			int quantity = jdbcTemplate.queryForInt(sql, parameters);
			return quantity;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//商品番号の在庫を1つ減らすためのメソッド
	public int reduceItemQuantity(String itemNo) {
		int numberRow = 0;
		String sql = "UPDATE t_item SET itemQuantity = itemQuantity - 1 WHERE itemNo=?";
		Object[] parameters = { itemNo };

		TransactionStatus transactionStatus = null;
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

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

	public int insertItem2(Items items) {
		String sql = "INSERT INTO t_item2(itemNo, itemName,itemQuantity,genreNo,director,typeNo,itemPicture) VALUES(?,?,?,?,?,?,?);";

		Object[] parameters = { items.getItemNo(), items.getItemName(), items.getItemQuantity(), items.getGenreNo(),
				items.getDirector(),
				items.getTypeNo(), items.getItemPicture() };
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

	public List<Items> stockCheck() {
		String sql = "SELECT * FROM t_item2 WHERE itemQuantity =< 0";
		try {
			List<Items> itemsList = jdbcTemplate.query(sql, itemsMapper);
			return itemsList;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteItem2() {
		String sql = "DELETE FROM t_item2";
		TransactionStatus transactionStatus = null;
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		try {
			transactionStatus = transactionManager.getTransaction(transactionDefinition);
			jdbcTemplate.update(sql);
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
		return;
	}

	public List<Items> getListByName(String itemName) {
		String sql = "SELECT * FROM t_item WHERE itemName LIKE ?";
		//ワイルドカード文字をただの文字として認識するため
		itemName = itemName.replace("%", "\\%").replace("_", "\\_");
		itemName = "%" + itemName + "%";
		Object[] parameters = { itemName };
		//queryメソッドは検索結果の件数が決まっていないとき
		//第二引数にINパラメータにセットする配列、あとは上と一緒
		List<Items> itemsList = jdbcTemplate.query(sql, parameters, itemsMapper);
		return itemsList;
	}




	public void deleteFromItem2(int itemNo) {
	String sql = "DELETE FROM t_item2 WHERE itemNo =?";
	Object[] parameters = { itemNo };
	TransactionStatus transactionStatus = null;
	DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	try {
		transactionStatus = transactionManager.getTransaction(transactionDefinition);
		jdbcTemplate.update(sql,parameters);
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
	return;
	}

}
