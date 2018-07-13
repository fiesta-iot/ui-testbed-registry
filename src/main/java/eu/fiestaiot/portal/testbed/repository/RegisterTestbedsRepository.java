package eu.fiestaiot.portal.testbed.repository;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the RegisterTestbeds entity.
 */
@SuppressWarnings("unused")
public interface RegisterTestbedsRepository extends JpaRepository<RegisterTestbeds, Long> {

    RegisterTestbeds findByRegisterID(String registerID);

    RegisterTestbeds findByIriAndName(String iri, String name);

    Page<RegisterTestbeds> findByUserID(Pageable pageable, String userID);

    List<RegisterTestbeds> findByRegisterIDIn(List<String> registerIDList);

    public List<RegisterTestbeds> findAllByUserID(String userID);

    public RegisterTestbeds findByIri(String iri);

  

}
