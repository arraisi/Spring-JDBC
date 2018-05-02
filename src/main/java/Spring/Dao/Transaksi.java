/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spring.Dao;

import SpringModel.TransaksiRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class Transaksi {

    @Autowired //MENCARI JBDCTEMPLATE
    JdbcTemplate jdbcTemplate;

    @Autowired //MENCARI JBDCTEMPLATE
    NamedParameterJdbcTemplate nameJdbcTemplate;

//SELECT ALL
    public List< Transaksi> findAll() {
        List< Transaksi> listTransaksi = jdbcTemplate.query("SELECT ID as id, Waktu FROM Transaksi",
                new BeanPropertyRowMapper(Transaksi.class));
        return listTransaksi;
    }
//SELECT OBJECT    

    public Transaksi findById(int id) {
        return (Transaksi) jdbcTemplate
                .queryForObject("SELECT ID as id, Waktu FROM Transaksi "
                        + "WHERE Transaksi = ?", new Integer[]{id}, new BeanPropertyRowMapper(Transaksi.class));
    }

//CARA INSERT DATA KE DATA BASE
    //TABEL TRANSAKSI
    public Number simpanTransaksi(Timestamp waktu) {
        String sql = "INSERT INTO Transaksi (Waktu) VALUES (:time)";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("time", waktu);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        nameJdbcTemplate.update(sql, paramMap, keyHolder);
        return keyHolder.getKey();
    }

    //TABEL DETAILS TRANSAKSI
    public Number simpanDetails(Integer IDTransaksi, String item, Integer qty, BigDecimal price) {
        String sql = "INSERT INTO Transaksi_Details (ID_Transaksi, Item, Quantity, Price) VALUES (:idTrans, :item, :qty, :harga)";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("idTrans", IDTransaksi);
        paramMap.addValue("item", item);
        paramMap.addValue("qty", qty);
        paramMap.addValue("harga", price);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        nameJdbcTemplate.update(sql, paramMap, keyHolder);
        return keyHolder.getKey();
    }

    /*
//CARA UPDATE
    public int simpanUpdate(TransaksiRepository transUpdate) {
        String sql = "UPDATE Customers SET FirstName = :nama1, LastName = :nama2 WHERE CustomerID = :id ";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nama1", bungaFlat.getFirstName());
        paramMap.put("nama2", bungaFlat.getLastName());
        paramMap.put("id", bungaFlat.getId());
        return nameJdbcTemplate.update(sql, paramMap);
    }*/
}


/* 
bank.setJam(new Timestamp(System.currentRimeMillis()))
*/
