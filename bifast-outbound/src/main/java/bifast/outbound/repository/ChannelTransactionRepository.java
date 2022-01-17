package bifast.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bifast.outbound.model.ChannelTransaction;

@Repository
public interface ChannelTransactionRepository extends JpaRepository<ChannelTransaction, String> {

//	Optional<ChannelTransaction> findByKomiTrnsId (String komiTrnsId);
	List<ChannelTransaction> findByChannelIdAndChannelRefId (String channelId, String refId);
	List<ChannelTransaction> findByChannelRefId (String refId);
	Optional<ChannelTransaction> findByKomiTrnsId (String komiTrnsId);
	
	@Query(value = "select nextval('kc_outboundseq')", nativeQuery = true)
	Long getKomiSequence();

}
