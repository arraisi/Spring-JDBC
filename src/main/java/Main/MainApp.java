
package Main;

import Spring.Dao.Transaksi;
import SpringConfig.Konfigurasi;
import SpringModel.TransaksiRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Konfigurasi.class);
        ctx.refresh();

        Transaksi transaksi = ctx.getBean(Transaksi.class);
        
        //System.out.println(transaksi.simpanTransaksi(System.currentTimeMillis()));
        
        TransaksiRepository repository = new TransaksiRepository();
        
        repository.setWaktu(new Timestamp(System.currentTimeMillis()));
        //transaksi.simpanTransaksi(repository.getWaktu());
        
        int idTrans = transaksi.simpanTransaksi(repository.getWaktu()).intValue();
        repository.setIdTransaksi(idTrans);
        repository.setItem("Sepeda");
        repository.setQty(2);
        repository.setPrice(new BigDecimal (7000000));
        transaksi.simpanDetails(repository.getIdTransaksi(), repository.getItem(), repository.getQty(), repository.getPrice());
        //transaksi.simpanDetails(Integer.BYTES, item, Integer.SIZE, BigDecimal.ONE);
    }
    
}
