package bifast.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bifast.outbound.model.CreditTransfer;


@Repository
public interface CreditTransferRepository extends JpaRepository<CreditTransfer, Long> {

	public List<CreditTransfer> findByCrdtTrnRequestBizMsgIdr (String msgId);
	public Optional<CreditTransfer> findByKomiTrnsId (String komiTrnsId);

}
