package Main;

import Spring.Dao.Transaksi;
import SpringConfig.Konfigurasi;
import SpringModel.TransaksiRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Konfigurasi.class);
        ctx.refresh();

        Transaksi transaksi = ctx.getBean(Transaksi.class);

        TransaksiRepository repository = new TransaksiRepository();
        
//INSERT TABEL TRANSAKSI
        repository.setWaktu(new Timestamp(System.currentTimeMillis()));

//INSERT TABEL DETAIL TRANSAKSI
        int idTrans = transaksi.simpanTransaksi(repository.getWaktu()).intValue();
        repository.setIdTransaksi(idTrans);
        repository.setItem("Sepatu");
        repository.setQty(2);
        repository.setPrice(new BigDecimal(2500000));
        transaksi.simpanDetails(repository.getIdTransaksi(), repository.getItem(), repository.getQty(), repository.getPrice());
         

//SELECT TABEL TRANSAKSI
        List<TransaksiRepository> listTransaksi = transaksi.findAll();
        for (TransaksiRepository tampil : listTransaksi) {
            System.out.println(tampil.getId()+ " " + tampil.getWaktu());
        }
         
/*
//UPDATE TABEL DETAILS TRANSAKSI
        TransaksiRepository detailUpdate = transaksi.findById(4);
        detailUpdate.setIdTransaksi(1);
        detailUpdate.setItem("Laptop");
        detailUpdate.setQty(1);
        detailUpdate.setPrice(new BigDecimal(19000000));
        System.out.println(transaksi.upDateDetails(detailUpdate));
         
//DELETE TABEL TRANSAKSI DETAIL
        TransaksiRepository delete = transaksi.findById(2);
        delete.getId();
        System.out.println(transaksi.delete(delete));
//DELETE TABEL TRANSAKSI
        TransaksiRepository deleteTransaksi = transaksi.findByIdTransaksi(2);
        deleteTransaksi.getId();
        System.out.println(transaksi.deleteTransaksi(deleteTransaksi));
*/
    }

}
