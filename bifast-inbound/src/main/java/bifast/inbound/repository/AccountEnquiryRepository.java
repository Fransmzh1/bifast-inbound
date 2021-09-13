package bifast.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bifast.inbound.model.AccountEnquiry;


@Repository
public interface AccountEnquiryRepository extends JpaRepository<AccountEnquiry, Long> {

}
