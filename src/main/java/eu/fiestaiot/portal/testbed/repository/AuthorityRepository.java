package eu.fiestaiot.portal.testbed.repository;

import eu.fiestaiot.portal.testbed.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
