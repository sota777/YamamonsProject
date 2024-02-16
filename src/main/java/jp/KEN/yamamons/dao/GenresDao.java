package jp.KEN.yamamons.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import jp.KEN.yamamons.entity.Genres;
import jp.KEN.yamamons.entity.Items;

@Component
public class GenresDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private RowMapper<Items> itemsMapper = new BeanPropertyRowMapper<Items>(Items.class);
	private RowMapper<Genres> genresMapper = new BeanPropertyRowMapper<Genres>(Genres.class);

	public List<Items> getByGenre(Integer genre) {
		String sql = "SELECT * FROM t_item WHERE genreNo=?";
		//INパラメータに使用する値の配列を生成
		Object[] parameters = {genre};
		try {
			//第二引数はIDでとってきた配列を入れる
			//queryForObjectは検索結果が一件の時
			List<Items> quantity = jdbcTemplate.query(sql, parameters, itemsMapper);
			return quantity;
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Genres> getGenresList() {
		String sql = "SELECT * FROM t_genre";
		List<Genres> genresList = jdbcTemplate.query(sql, genresMapper);
		return genresList;
	}
	
	public List<Items> getListByNameAndNo(String itemName, Integer genreNo){
		String sql = "SELECT * FROM t_item WHERE itemName LIKE ? AND genreNo=?";
		//ワイルドカード文字をただの文字として認識するため
		itemName = itemName.replace("%", "\\%").replace("_", "\\_");
		itemName = "%" + itemName + "%";
		Object[] parameters = {itemName, genreNo};
		//queryメソッドは検索結果の件数が決まっていないとき
		//第二引数にINパラメータにセットする配列、あとは上と一緒
		List<Items> itemsList = jdbcTemplate.query(sql, parameters, itemsMapper);
		return itemsList;
	}
}
