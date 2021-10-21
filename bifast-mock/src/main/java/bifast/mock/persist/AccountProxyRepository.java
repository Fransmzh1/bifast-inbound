package bifast.mock.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProxyRepository extends JpaRepository<AccountProxy, Long> {
    
	@Query("SELECT accountProxy FROM AccountProxy accountProxy WHERE accountProxy.accountNumber = :accountNumber")
	AccountProxy getByAccountNumber(@Param("accountNumber") String accountNumber);
	
	@Query("SELECT accountProxy FROM AccountProxy accountProxy WHERE accountProxy.scndIdTp = :scndIdTp and accountProxy.scndIdVal = :scndIdVal")
	AccountProxy getByScndIdTpAndByScndIdVal(@Param("scndIdTp") String scndIdTp,@Param("scndIdVal") String scndIdVal);
	
	@Query("SELECT accountProxy FROM AccountProxy accountProxy WHERE accountProxy.proxyType = :proxyType and accountProxy.proxyVal = :proxyVal")
	AccountProxy getByProxyTypeAndByProxyVal(@Param("proxyType") String proxyType,@Param("proxyVal") String proxyVal);
}
