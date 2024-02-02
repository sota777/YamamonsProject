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

	public Items getItemsByNo(Integer itemNo) {
		String sql = "SELECT * FROM t_item WHERE itemNo=?";
		//INパラメータに使用する値の配列を生成
		Object[] parameters = {itemNo};
		try {
			//第二引数はIDでとってきた配列を入れる
			//queryForObjectは検索結果が一件の時
			Items items = jdbcTemplate.queryForObject(sql, parameters, itemsMapper);
			return items;
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	//注文が完了した時に、注文された商品番号の在庫を1つ減らすためのメソッド
	public int reduceItemQuantity(String itemNo) {
		int numberRow = 0;

		//まず、注文完了前の在庫数を確認する
		Items preItem = getItemsByNo(Integer.parseInt(itemNo));

		//在庫が0の場合は注文できない
		if (Integer.parseInt(preItem.getItemQuantity())<1) {
			return numberRow=2;
		}

		//在庫を1つ減らすSQL文
		String sql = "UPDATE t_item SET itemQuantity = itemQuantity - 1 WHERE itemNo=?";
		Object[] parameters = {itemNo};

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
}
