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
        repository.setItem("Motor");
        repository.setQty(1);
        repository.setPrice(new BigDecimal(21000000));
        transaksi.simpanDetails(repository.getIdTransaksi(), repository.getItem(), repository.getQty(), repository.getPrice());
        /*
//SELECT TABEL TRANSAKSI
        List<TransaksiRepository> listTransaksi = transaksi.findAll();
        for (TransaksiRepository tampil : listTransaksi) {
            System.out.println(repository.getIdTransaksi() + " " + repository.getWaktu());
        }
         */
//UPDATE TABEL DETAILS TRANSAKSI
        TransaksiRepository detailUpdate = transaksi.findById(3);
        detailUpdate.setIdTransaksi(1);
        detailUpdate.setItem("Laptop");
        detailUpdate.setQty(1);
        detailUpdate.setPrice(new BigDecimal(19000000));
        System.out.println(transaksi.upDateDetails(detailUpdate));
//DELETE TABEL TRANSAKSI DETAIL

    }

}
